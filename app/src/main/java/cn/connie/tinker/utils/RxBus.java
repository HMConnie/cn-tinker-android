package cn.connie.tinker.utils;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {

    private static RxBus rxBus;
    private final Subject _bus;

    private RxBus() {
        _bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getInstance() {
        if (rxBus == null) {
            synchronized (RxBus.class) {
                if (rxBus == null) {
                    rxBus = new RxBus();
                }
            }
        }
        return rxBus;
    }

    public void send(Object o) {
        _bus.onNext(o);
    }


    public Observable<Object> toObservable() {
        return _bus;
    }


    public static SubscriberBuilder with(LifecycleProvider provider) {
        return new SubscriberBuilder(provider);
    }


    public static class SubscriberBuilder {

        private LifecycleProvider mLifecycleProvider;
        private Action1<Object> onNext;
        private Action1<Throwable> onError;

        public SubscriberBuilder(LifecycleProvider provider) {
            this.mLifecycleProvider = provider;
        }


        public SubscriberBuilder onNext(Action1<Object> action) {
            this.onNext = action;
            return this;
        }

        public SubscriberBuilder onError(Action1<Throwable> action) {
            this.onError = action;
            return this;
        }


        public void create() {
            _create();
        }


        private Subscription _create() {
            if (mLifecycleProvider != null) {
                if (mLifecycleProvider instanceof RxFragment) {
                    Observable.Transformer<Object, Object> objectFrgtObjectTransformer = mLifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
                    return RxBus.getInstance().toObservable()
                            .compose(objectFrgtObjectTransformer) // 绑定生命周期
                            .subscribe(new Action1<Object>() {

                                @Override
                                public void call(Object o) {
                                    onNext.call(o);
                                }
                            }, onError == null ? new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            } : onError);
                } else if (mLifecycleProvider instanceof RxAppCompatActivity) {
                    Observable.Transformer<Object, Object> objectActObjectTransformer = mLifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
                    return RxBus.getInstance().toObservable()
                            .compose(objectActObjectTransformer)
                            .subscribe(new Action1<Object>() {

                                @Override
                                public void call(Object o) {
                                    onNext.call(o);
                                }
                            }, onError == null ? new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            } : onError);
                }

            }
            return null;
        }
    }
}