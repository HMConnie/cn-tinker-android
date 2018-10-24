package cn.connie.tinker.tinker;

import android.content.Context;

import com.dx168.patchsdk.IPatchManager;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

/**
 * Created by hinge on 2018/10/17.
 */

public class QueryAndPatchPatchManager implements IPatchManager {
    @Override
    public void patch(Context context, String path) {
        TinkerInstaller.onReceiveUpgradePatch(context, path);
    }

    @Override
    public void cleanPatch(Context context) {
        TinkerInstaller.cleanPatch(context);
    }
}
