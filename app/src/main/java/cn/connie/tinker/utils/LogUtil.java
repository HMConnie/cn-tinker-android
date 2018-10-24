package cn.connie.tinker.utils;


import cn.connie.tinker.BuildConfig;

/**
 * the logger
 *
 * @author MaTianyu
 *         2014-1-1下午4:05:39
 */
public final class LogUtil {

    /**
     * isPrint: print switch, true will print. false not print
     */
    public static boolean isPrint = BuildConfig.LOG_DEBUG;
    private static String DEFAULT_TAG = BuildConfig.APPLICATION_ID;

    private LogUtil() {
    }

    /**
     * ******************** Log **************************
     */
    public static int v(String msg) {
        return isPrint && msg != null ? android.util.Log.v(DEFAULT_TAG, msg) : -1;
    }

    public static int d(String msg) {
        return isPrint && msg != null ? android.util.Log.d(DEFAULT_TAG, msg) : -1;
    }

    public static int i(String msg) {
        return isPrint && msg != null ? android.util.Log.i(DEFAULT_TAG, msg) : -1;
    }

    public static int w(String msg) {
        return isPrint && msg != null ? android.util.Log.w(DEFAULT_TAG, msg) : -1;
    }

    public static int e(String msg) {
        return isPrint && msg != null ? android.util.Log.e(DEFAULT_TAG, msg) : -1;
    }

    /**
     * ******************** Log with object list **************************
     */
    public static int v(Object... msg) {
        return isPrint ? android.util.Log.v(DEFAULT_TAG, getLogMessage(msg)) : -1;
    }

    public static int d(Object... msg) {
        return isPrint ? android.util.Log.d(DEFAULT_TAG, getLogMessage(msg)) : -1;
    }

    public static int i(Object... msg) {
        return isPrint ? android.util.Log.i(DEFAULT_TAG, getLogMessage(msg)) : -1;
    }

    public static int w(Object... msg) {
        return isPrint ? android.util.Log.w(DEFAULT_TAG, getLogMessage(msg)) : -1;
    }

    public static int e(Object... msg) {
        return isPrint ? android.util.Log.e(DEFAULT_TAG, getLogMessage(msg)) : -1;
    }

    private static String getLogMessage(Object... msg) {
        if (msg != null && msg.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object s : msg) {
                if (msg != null && s != null) {
                    sb.append(s.toString());
                }
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * ******************** Log with Throwable **************************
     */
    public static int v(String msg, Throwable tr) {
        return isPrint && msg != null ? android.util.Log.v(DEFAULT_TAG, msg, tr) : -1;
    }

    public static int d(String msg, Throwable tr) {
        return isPrint && msg != null ? android.util.Log.d(DEFAULT_TAG, msg, tr) : -1;
    }

    public static int i(String msg, Throwable tr) {
        return isPrint && msg != null ? android.util.Log.i(DEFAULT_TAG, msg, tr) : -1;
    }

    public static int w(String msg, Throwable tr) {
        return isPrint && msg != null ? android.util.Log.w(DEFAULT_TAG, msg, tr) : -1;
    }

    public static int e(String msg, Throwable tr) {
        return isPrint && msg != null ? android.util.Log.e(DEFAULT_TAG, msg, tr) : -1;
    }

    /**
     * ******************** TAG use Object SelectedTopicModel **************************
     */
    public static int v(Object DEFAULT_TAG, String msg) {
        return isPrint ? android.util.Log.v(DEFAULT_TAG.getClass().getSimpleName(), msg) : -1;
    }

    public static int d(Object DEFAULT_TAG, String msg) {
        return isPrint ? android.util.Log.d(DEFAULT_TAG.getClass().getSimpleName(), msg) : -1;
    }

    public static int i(Object DEFAULT_TAG, String msg) {
        return isPrint ? android.util.Log.i(DEFAULT_TAG.getClass().getSimpleName(), msg) : -1;
    }

    public static int w(Object DEFAULT_TAG, String msg) {
        return isPrint ? android.util.Log.w(DEFAULT_TAG.getClass().getSimpleName(), msg) : -1;
    }

    public static int e(Object DEFAULT_TAG, String msg) {
        return isPrint ? android.util.Log.e(DEFAULT_TAG.getClass().getSimpleName(), msg) : -1;
    }
}
