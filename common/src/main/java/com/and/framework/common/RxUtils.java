package com.and.framework.common;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    /**
     * 统一线程控制
     * @param <T>
     * @return
     */
    public static  <T> ObservableTransformer<T,T> applySchedulers(){
        return new ObservableTransformer<T,T>(){
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 获取真正需要的result
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResponseBody<T>,T> extractResult(){
        return new ObservableTransformer<BaseResponseBody<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponseBody<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponseBody<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponseBody<T> tBaseResponseBody) throws Exception {

                        if (tBaseResponseBody.getCode() == 0){
                            return Observable.just(tBaseResponseBody.getResult());
                        }
                        return Observable.error(new Throwable(tBaseResponseBody.getMessage()));
                    }
                });
            }
        };
    }

}
