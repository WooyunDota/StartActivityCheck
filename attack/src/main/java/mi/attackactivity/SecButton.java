package mi.attackactivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

/**
 * Created by magic on 2017/8/17.
 */

public class SecButton extends android.support.v7.widget.AppCompatButton {
    public SecButton(Context context) {
        super(context);
    }

    public SecButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SecButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean performAccessibilityAction(int action, Bundle arguments) {
//
//        //忽略AccessibilityService传过来的点击事件以达到防止模拟点击的目的
////        if (action == AccessibilityNodeInfo.ACTION_CLICK
////                || action == AccessibilityNodeInfo.ACTION_LONG_CLICK) {
////            return true;
////        }
////
////        return super.performAccessibilityAction(action, arguments);
//        //忽略所有AccessibilityService事件
//        return true;
//    }
}
