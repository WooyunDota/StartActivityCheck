package mi.protectactivity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by magic on 2017/8/15.
 */

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();


    //mi.attackactivity
    //C8D69481E7A2722BADA5AD87D61BACBC77E80EF1896C68D6E0D6948C514C22F0
    //此时3rd app只有通过包名和签名验证才能打开受保护的Activity
    public static  boolean permissionCheck(String pName ,String sign){
        if (pName!=null&&pName.equals("mi.attackactivity")&&sign.equals("C8D69481E7A2722BADA5AD87D61BACBC77E80EF1896C68D6E0D6948C514C22F0")){
            return  true;
        }else  if (pName!=null&&pName.equals("mi.protectactivity")&&sign.equals("C8D69481E7A2722BADA5AD87D61BACBC77E80EF1896C68D6E0D6948C514C22F0")){
            return  true;
        }
        else {
            return false;
        }
    }

    public static byte[] getPackageSignDigest(String pName, Context context) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(pName, PackageManager.GET_SIGNATURES);
//            byte[] certBytes = packageInfo.signatures[0].toByteArray();
            for (Signature sig : packageInfo.signatures) {
                digest.update(sig.toByteArray());
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return digest.digest();
    }


    public static String  getPackageSignHex(String pName, Context context){

        return byte2hex(getPackageSignDigest(pName, context));

    }

    public static String byte2hex(byte[] b) {

        if (b == null){
            return "input is null";
        }
        // 转成16进制字符串
        String hs = "";
        String tmp = "";
        for (int n = 0; n < b.length; n++) {
            //整数转成十六进制表示
            tmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
                hs = hs + "0" + tmp;
            } else {
                hs = hs + tmp;
            }
        }
        tmp = null;
        return hs.toUpperCase();

    }
}
