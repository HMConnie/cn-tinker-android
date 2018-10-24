package cn.connie.tinker.compontent;

import com.squareup.leakcanary.RefWatcher;

import cn.connie.tinker.AppContext;
import cn.connie.tinker.anno.ApplicationScope;
import cn.connie.tinker.base.NewsLifecycleHandler;
import cn.connie.tinker.network.retrofit.ServiceGenerator;
import dagger.Component;

/**
 * Created by hinge on 18/6/26.
 */

@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(AppContext application);

    AppContext getApplicationContext();

    ServiceGenerator getServiceGenerator();

    NewsLifecycleHandler provideLifecycleHandler();

    RefWatcher provideRefWatcher();
}