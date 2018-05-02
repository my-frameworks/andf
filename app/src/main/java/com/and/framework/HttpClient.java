package com.and.framework;



import com.and.framework.common.BaseResponseBody;
import com.and.framework.common.component.RetrofitHelper;
import com.and.framework.common.utils.RxUtils;



import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class HttpClient {

    private static volatile HttpClient sInstance;
    private static HttpService httpService;

    private HttpClient() {
        httpService = RetrofitHelper.getInstance().create(HttpService.class);
    }

    public static HttpClient getInstance() {
        if (sInstance == null){
            synchronized (HttpClient.class){
                if (sInstance == null){
                    sInstance = new HttpClient();
                }
            }
        }
        return sInstance;
    }


    public <T> Flowable<T> transformer(Observable<BaseResponseBody<T>> observable){
        return observable.compose(RxUtils.<BaseResponseBody<T>>applySchedulers())
                .compose(RxUtils.<T>extractResult())
                .toFlowable(BackpressureStrategy.BUFFER);
    }


    public Flowable<User> getUserInfo(){
        return transformer(httpService.getUserInfo());
    }


}
