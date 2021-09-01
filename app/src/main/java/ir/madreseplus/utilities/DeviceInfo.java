package ir.madreseplus.utilities;

import android.os.Build;

public class DeviceInfo {

    public static String deviceName(){
        return Build.MODEL;
    }
}
