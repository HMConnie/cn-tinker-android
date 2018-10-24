package cn.connie.tinker.compontent;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.connie.tinker.AppContext;
import cn.connie.tinker.anno.ApplicationScope;
import cn.connie.tinker.base.NewsLifecycleHandler;
import cn.connie.tinker.network.retrofit.ServiceGenerator;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hinge on 18/6/26.
 */

@Module
public class AppModule {

    private final AppContext app;

    public AppModule(AppContext app) {
        this.app = app;
    }

    @Provides
    AppContext getApplicationContext() {
        return app;
    }


    @Provides
    @ApplicationScope
    ServiceGenerator provideServiceGenerator() {
        return ServiceGenerator.getInstance();
    }


    @Provides
    @ApplicationScope
    NewsLifecycleHandler provideLifecycleHandler() {
        return new NewsLifecycleHandler();
    }

    @Provides
    @ApplicationScope
    RefWatcher provideRefWatcher() {
        return LeakCanary.install(app);
    }
}
