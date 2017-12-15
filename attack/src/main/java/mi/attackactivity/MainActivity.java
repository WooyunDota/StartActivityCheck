package mi.attackactivity;

import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import mi.protectactivity.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.ref);
        TextView textView1 = (TextView) findViewById(R.id.ser);


        SecButton secButton = (SecButton) findViewById(R.id.button);

//        secButton.setAccessibilityDelegate(new View.AccessibilityDelegate(){
//            @Override
//            public boolean performAccessibilityAction(View host, int action, Bundle args) {
//                return true;
//            }
//        });

//        reflectSetReferrer();

//        reflectBasePackageName();
        secButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"click!!!!!",Toast.LENGTH_SHORT).show();
            }
        });




        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent("just.export");
                Intent intent = new Intent();
                intent.setClassName("mi.protectactivity","mi.protectactivity.ProtectByReferrer");
                intent.putExtra(Intent.EXTRA_REFERRER,Uri.parse("android-app://mi.bbbbbbbb"));
                intent.putExtra(Intent.EXTRA_REFERRER_NAME, "android-app://mi.ccccccc");

                startActivity(intent);
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent();
                intent.setClassName("mi.protectactivity","mi.protectactivity.MyService");
                ServiceConnection conn = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            IMyAidlInterface.Stub.asInterface(service).startProtectActivity("0");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                };
                bindService(intent,conn, Context.BIND_AUTO_CREATE);
            }
        });
    }

    @Override
    public Uri getReferrer() {
        return super.getReferrer();
    }

    @Override
    public Uri onProvideReferrer() {
        super.onProvideReferrer();
        Uri uri = Uri.parse("android-app://mi.aaaaaaaaa");
        return uri;
    }

//    private String reflectSetReferrer() {
//        String referrer = new String();
//        try {
//            Class activityClass = Class.forName("android.app.Activity");
//
//            Field refererField = activityClass.getDeclaredField("mReferrer");
//            refererField.setAccessible(true);
//            referrer = (String) refererField.get(this);
//            Log.e("1",referrer);
//
//            refererField.set(this,"mi.xxxxxxxx");
//
//            referrer = (String) refererField.get(this);
//
//            Log.e("2",referrer);
//
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return referrer;
//
//    }

    private String reflectBasePackageName(){

        try {
            Class contextImplClass = Class.forName("android.app.ContextImpl");
            Field mBasePackageNameField = contextImplClass.getDeclaredField("mBasePackageName");

            //java.lang.IllegalArgumentException: Expected receiver of type android.app.ContextImpl, but got android.app.Application
            Context context = getBaseContext();
            //java.lang.IllegalAccessException: Cannot set private final  field java.lang.String android.app.ContextImpl.mBasePackageName of class java.lang.Class<android.app.ContextImpl>
            mBasePackageNameField.setAccessible(true);
            mBasePackageNameField.set(context,"mi.hello");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return "";
    }


}
