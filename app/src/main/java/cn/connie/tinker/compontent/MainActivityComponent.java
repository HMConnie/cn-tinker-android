package cn.connie.tinker.compontent;


import cn.connie.tinker.activitys.MainActivity;
import cn.connie.tinker.anno.ActivityScope;
import dagger.Component;

/**
 * Created by hinge on 18/6/26.
 */
@ActivityScope
@Component(modules = {MainActivityModule.class}, dependencies = AppComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
