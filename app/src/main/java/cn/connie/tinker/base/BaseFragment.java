package cn.connie.tinker.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import cn.connie.tinker.event.DefaultEvent;
import cn.connie.tinker.utils.RxBus;
import rx.functions.Action1;


public abstract class BaseFragment extends RxFragment {

    protected BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutView(), container, false);
        initView(view);
        initEvents();
        return view;
    }

    private void initEvents() {
        RxBus.with(this).onNext(new Action1<Object>() {
            @Override
            public void call(Object events) {
                if (events instanceof DefaultEvent) {
                    DefaultEvent defaultEvent = (DefaultEvent) events;
                    BaseFragment.this.onEventMainThread(defaultEvent);
                }
            }
        }).create();
    }


    protected void onEventMainThread(DefaultEvent events) {
    }

    protected abstract int getLayoutView();

    protected abstract void initView(View view);


    public void toActivity(Class<?> clazz) {
        activity.toActivity(clazz);
    }

    public void toActivity(Class<?> clazz, Bundle bundle) {
        activity.toActivity(clazz, bundle);
    }


}
