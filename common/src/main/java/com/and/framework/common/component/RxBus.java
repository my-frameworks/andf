package com.and.framework.common.component;



import com.and.framework.common.RxUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by zengzhigang on 17-5-26.
 */
public class RxBus {
    // 主题
    private final Subject<Object> bus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    public static RxBus getDefault() {
        return RxBusHolder.sInstance;
    }

    private static class RxBusHolder {
        private static final RxBus sInstance = new RxBus();
    }

    // 提供了一个新的事件
    public void post(Object o) {
        bus.onNext(o);
    }

    public void postDelay(Object o, int delay) {
        bus.delay(delay, TimeUnit.MILLISECONDS);
        bus.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    //不推荐使用，可以toFlowable替代
    @Deprecated
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return toObservable(eventType)
                .toFlowable(BackpressureStrategy.BUFFER)
                .compose(RxUtils.<T>rxSchedulerHelperForFlowable());
    }

    // 封装默认订阅
    public <T> Disposable toDefaultObservable(Object mBaseView, final Class<T> eventType, Consumer<T> act) {
        if (mBaseView == null)
            return bus.ofType(eventType)
                    .compose(RxUtils.<T>applySchedulers())
                    .subscribe(act);
        return bus.ofType(eventType)
                .compose(RxUtils.<T>applySchedulers())
                .compose(RxUtils.<T>bindToLifecycle(mBaseView))
                .subscribe(act);
    }
}
