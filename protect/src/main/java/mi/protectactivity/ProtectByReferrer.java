package mi.protectactivity;

import android.os.Binder;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;

public class ProtectByReferrer extends AppCompatActivity {

    public  final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect_by_referrer);
        TextView textView = (TextView) findViewById(R.id.textView);
        StringBuilder sb = new StringBuilder();

        String callingPackage = getCallingPackage();
        Log.e(TAG,"getCallingPackage = " + callingPackage);

        String getReferrer = null;
        String mReferrer = null;
        String sign = null;

        //platform >= android 5.1 (api 22 | LOLLIPOP_MR1)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            try {
                //mReferrer may be null
                mReferrer = reflectReferrerField();
                sign = Utils.getPackageSignHex(mReferrer,this);
                Log.e(TAG,"mReferrer = " + mReferrer + " | sign(SHA-256) = "+ sign);
                //getReferrer有被伪造风险.
                getReferrer = getReferrer().toString();
                Log.e(TAG,"getReferrer = " +getReferrer);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (Utils.permissionCheck(mReferrer,sign)){
                Log.e(TAG,"permissionCheck success");
            }else {

                Log.e(TAG,"permissionCheck failed");

//                onDestroy();
//                finish();
            }
        }
        sb.append("This Activty is exported and protect by mReferrer when platform >= android 5.1 \n\n")
                .append("mReferrer = " + mReferrer+ "\nSign(SHA-256) = " + sign+"\n\n")
                .append("getCallingPackage = " + callingPackage+"\n\n")
                .append("getReferrer = " +getReferrer+"\n\n");
                //getCallingUid should invoke invoke in service expose method.
//                .append("Binder = "+ getPackageManager().getNameForUid(Binder.getCallingUid()));

        textView.setText(sb);
    }


    private String reflectReferrerField() {
        String referrer = new String();
        try {
            Class activityClass = Class.forName("android.app.Activity");
            Field refererField = activityClass.getDeclaredField("mReferrer");
            refererField.setAccessible(true);
            referrer = refererField.get(this).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return referrer;

    }
}
