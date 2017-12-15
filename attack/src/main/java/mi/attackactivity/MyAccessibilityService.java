package mi.attackactivity;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by magic on 2017/8/17.
 */

public class MyAccessibilityService extends AccessibilityService {
    private static final String TAG = MyAccessibilityService.class.getSimpleName();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int type = event.getEventType();
        Log.e(TAG, "ACC::onAccessibilityEvent: " + type);
        switch (type)
        {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                AccessibilityNodeInfo nodeInfo  = getRootInActiveWindow();
                if (nodeInfo!=null){
                    if (event.getPackageName().equals("mi.attackactivity")&&event.getClassName().equals("mi.attackactivity.MainActivity")){
                        List<AccessibilityNodeInfo> list=
//                                nodeInfo.findAccessibilityNodeInfosByText("Button");
                                nodeInfo.findAccessibilityNodeInfosByViewId("mi.attackactivity:id/button");
                        if(list!=null&&list.size()>0)
                        {
                            list.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }
                }
                break;
        }

    }


    @Override
    public void onInterrupt() {
        Log.e(TAG,"onInterrupt");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.e(TAG,"onServiceConnected");
    }
}
