package cn.connie.tinker.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.connie.tinker.AppContext;
import cn.connie.tinker.compontent.AppComponent;

/**
 * Created by hinge on 2018/10/9.
 */

public abstract class BaseInjectActivity extends BaseActivity {

    public abstract void inject(AppComponent appComponent);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(AppContext.getInstance().getAppComponent());
    }
}
