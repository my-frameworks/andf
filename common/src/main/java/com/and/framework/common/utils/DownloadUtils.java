package com.and.framework.common.utils;

import android.support.annotation.Nullable;
import android.util.Log;

import com.and.framework.common.FileDownloadService;
import com.and.framework.common.component.RetrofitHelper;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class DownloadUtils {

    private static volatile DownloadUtils sInstance;
    private static FileDownloadService downloadService;

    private DownloadUtils() {
        downloadService = RetrofitHelper.getInstance().create(FileDownloadService.class);
    }

    public static DownloadUtils getInstance() {

        if (sInstance == null){
            synchronized (DownloadUtils.class){
                if (sInstance == null){
                    sInstance = new DownloadUtils();
                }
            }
        }
        return sInstance;

    }
    

    public void downloadFile(String fileUrl, @Nullable final File savedFile) {


        downloadService.downloadFile(fileUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        FileUtils.writeToFile(responseBody.byteStream(),savedFile);

                    }
                });



    }
}
