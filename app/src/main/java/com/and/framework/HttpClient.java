package com.and.framework;



import com.and.framework.common.BaseResponseBody;
import com.and.framework.common.component.RetrofitHelper;
import com.and.framework.common.utils.RxUtils;



import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class HttpClient {

    private HttpService httpService;

    private HttpClient() {
        httpService = RetrofitHelper.getInstance().create(HttpService.class);
    }

    public static HttpClient getInstance() {
        return HttpClientHolder.sInstance;
    }
    private static class HttpClientHolder {
        private static HttpClient sInstance = new HttpClient();
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
