package cn.connie.tinker.utils;

import android.os.Build;

import java.util.UUID;

/**
 * 00000000-47fd-3878-ffff-ffff978c1a58 vivo x6
 * ffffffff-ed45-0115-ffff-ffffae788796 小米5
 * 00000000-4a40-e3f1-ffff-ffffff67092f 小米5
 * ffffffff-c089-641b-0000-000062d6d302 小米6
 * 00000000-1129-18d1-ffff-ffffcfd2b27c  华为mate 10 pro
 * 00000000-1129-18d1-ffff-ffffe0e08b8f  华为mate 10
 */

public final class DeviceUtil {

    private DeviceUtil() {

    }

    public static String getUniquePsuedoID() {
        // 35是IMEI开头的号
        String m_szDevIDShort = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 位 2531b8cd 2531b8cd
        // 使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), Build.SERIAL.hashCode()).toString();
    }
}
