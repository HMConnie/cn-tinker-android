package cn.connie.tinker.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

import java.util.Stack;

import cn.connie.tinker.AppContext;
import cn.connie.tinker.R;
import cn.connie.tinker.activitys.MainActivity;
import cn.connie.tinker.utils.LogUtil;
import cn.connie.tinker.utils.ToastUtil;

/**
 * Created by hinge on 17/5/23.
 */

public class NewsLifecycleHandler implements Application.ActivityLifecycleCallbacks {

    // I use four separate variables here. You can, of course, just use two and
    // increment/decrement them instead of using four and incrementing them all.
    private int resumed;
    private int paused;
    private int started;
    private int stopped;
    private final Stack<Activity> activityStack;
    private long mExitTime;

    public NewsLifecycleHandler() {
        this.activityStack = new Stack<>();
        this.resumed = 0;
        this.paused = 0;
        this.started = 0;
        this.stopped = 0;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtil.e("activity add = " + activity.getClass().getSimpleName());//检测Activity是否销毁
        activityStack.add(activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtil.e("activity remove = " + activity.getClass().getSimpleName());//检测Activity是否销毁
        activityStack.remove(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        MobclickAgent.onResume(activity);//友盟统计
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        MobclickAgent.onPause(activity);//友盟统计
        ++paused;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;
    }

    // If you want a static function you can use to check if your application is
    // foreground/background, you can use the following:


    public boolean isApplicationVisible() {
        return started > stopped;
    }

    public boolean isApplicationInForeground() {
        return resumed > paused;
    }

    public boolean isApplicationInBackground() {
        return started == stopped;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void finish2MainActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (null != activity && activity.getClass() != MainActivity.class) {
                activity.finish();
                activityStack.remove(activity);
                --i;
            }
        }
    }

    /**
     * 回到桌面
     *
     * @param activity
     */
    public void appBackHome(MainActivity activity) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtil.show(activity, activity.getString(R.string.str_exit_content));
            mExitTime = System.currentTimeMillis();
        } else {
            MobclickAgent.onKillProcess(activity);//保存统计信息
            Intent launcherIntent = new Intent(Intent.ACTION_MAIN);  //回到桌面
            launcherIntent.addCategory(Intent.CATEGORY_HOME);
            activity.startActivity(launcherIntent);
        }


    }

    /**
     * 关闭程序
     */
    public void appExit() {
        try {
            finishAllActivity();
            MobclickAgent.onKillProcess(AppContext.getInstance());//保存统计信息
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ActivityManager activityMgr = (ActivityManager) AppContext.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(AppContext.getInstance().getPackageName());
        }
    }


    public boolean hasMainActivity() {
        boolean hasMainActivity = false;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i) && activityStack.get(i).getClass() == MainActivity.class) {
                hasMainActivity = true;
                break;
            }
        }
        return hasMainActivity;
    }

    public Activity fromActivity() {
        int index = activityStack.size() - 2;
        if (index >= 0 && activityStack.size() > index) {
            return activityStack.get(index);
        }
        return null;
    }

    public Activity getCurrentActivity() {
        int index = activityStack.size() - 1;
        if (index >= 0 && activityStack.size() > index) {
            return activityStack.get(index);
        }
        return null;
    }

    public Stack<Activity> getActivityStack() {
        return activityStack;
    }
}