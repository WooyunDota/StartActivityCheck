package mi.protectactivity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private final String TAG = getClass().getSimpleName();
    public MyService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() called!");
        IBinder iBinder = new IMyAidlInterface.Stub() {
            @Override
            public void startProtectActivity(String userid) throws RemoteException {
                startActivity(userid);
            }
        };
        return iBinder;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    private void startActivity(String userid) {
        int uid = Binder.getCallingUid();
        //通过uid得到packageName和packageSign,比对白名单后决定是否startActivity.
        String pName = getPackageManager().getNameForUid(uid);
        String digest = Utils.getPackageSignHex(pName,getApplicationContext());
        Log.e(TAG, "uid = " + uid + " | pName = " + pName + " | digest = " + digest);
        if (Utils.permissionCheck(pName,digest)){
            Intent intent = new Intent(getApplicationContext(), ProtetByService.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            Log.e(TAG,"permissionCheck failed");
        }
    }
}
