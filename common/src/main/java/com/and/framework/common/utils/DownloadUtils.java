package com.and.framework.common.utils;

import android.support.annotation.Nullable;

import com.and.framework.common.BaseResponseBody;
import com.and.framework.common.listener.FileDownloadService;
import com.and.framework.common.component.RetrofitHelper;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


public class DownloadUtils {

    private static volatile DownloadUtils sInstance;
    private FileDownloadService downloadService;

    private DownloadUtils() {
        downloadService = RetrofitHelper.getInstance().create(FileDownloadService.class);
    }

    public static DownloadUtils getInstance() {
        if (sInstance == null) {
            synchronized (DownloadUtils.class) {
                if (sInstance == null) {
                    sInstance = new DownloadUtils();
                }
            }
        }
        return sInstance;
    }


    public void downloadFile(String fileUrl, @Nullable final File savedFile) {

        downloadService.downloadFile(fileUrl)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        FileUtils.writeToFile(responseBody.byteStream(), savedFile);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {

                        disposable = d;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        disposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void uploadFile(String url,File uploadFile) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), uploadFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFile", uploadFile.getName(), requestBody);

        downloadService.uploadSingleFile(url,body)
                .compose(RxUtils.<BaseResponseBody<String>>applySchedulers())
                .compose(RxUtils.<String>extractResult())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
