package com.and.framework;



import com.and.framework.common.BaseResponseBody;
import com.and.framework.common.RetrofitHelper;
import com.and.framework.common.RxUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public class HttpClient {
    private String baseUrl = "";

    private HttpService httpService;

    private HttpClient() {
        RetrofitHelper.getInstance().init(getClient(), baseUrl);
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

    /**
     * 自定义client,https，自定义的header在这里
     *
     * @return
     */
    private OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request = originalRequest.newBuilder()
                        .method(originalRequest.method(), originalRequest.body())
                        .header("token", "custom get token")
                        .build();

                return chain.proceed(request);
            }
        };

        builder.addNetworkInterceptor(interceptor);
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        setHttpsConfig(builder);



        return builder.build();
    }

    //配置https相关
    private void setHttpsConfig(OkHttpClient.Builder builder){
        try {
            //打开证书文件,例如srca.cer
            InputStream inputStream = MyApplication.getInstance().getAssets().open("");

           CertificateFactory certificateFactory =  CertificateFactory.getInstance("x.509");
           Certificate certificate = certificateFactory.generateCertificate(inputStream);

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            keyStore.setCertificateEntry("ca",certificate);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)){
                throw new IllegalStateException("Unexpected default trust managers:"
                        + Arrays.toString(trustManagers));
            }

            X509TrustManager x509TrustManager = (X509TrustManager) trustManagers[0];
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,new TrustManager[]{x509TrustManager},new SecureRandom());
            builder.sslSocketFactory(sslContext.getSocketFactory(),x509TrustManager);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Flowable<User> getUserInfo(){
        return transformer(httpService.getUserInfo());
    }


}
