package com.and.framework.common.utils;

import android.content.Context;

import com.and.framework.common.AndFApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhangyadong on 2018/4/28.
 */

public class FileUtils {
    protected static final String PATH_DATA = AndFApplication.get().getAndFConfigInterface().getFilePath();


    public static void writeToFile(InputStream inputStream, File outFile) {

        if (outFile == null) return;
        FileOutputStream fileOutputStream = null;
        try {
            if (outFile.exists()) {
                outFile.delete();
            } else {
                outFile.createNewFile();
            }

            fileOutputStream = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int read;

            while ((read = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, read);
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * apk 文件放在应用外部缓存目录下
     * @param context
     * @param fileName，如xxx.apk
     * @return
     */
    public static File getApkPath(Context context, String fileName) {
        File cacheFileDir = context.getExternalCacheDir();
        File apkFile = new File(cacheFileDir, fileName);
        return apkFile;

    }
}
