package com.and.framework.common;


import android.content.Context;
import android.widget.ImageView;


public class ImageLoaderClient {
    private static volatile ImageLoaderClient sInstance;
    private ImageLoaderClient(){
    }

    public static ImageLoaderClient get(){
        if (sInstance == null){
            synchronized (ImageLoaderClient.class){
                if (sInstance == null){
                    sInstance = new ImageLoaderClient();
                }
            }
        }
        return sInstance;
    }

   public IImageLoader getLoader(){
        return new GlideImageLoader();
   }

    public void load(Context context, String url, ImageView imageView){

        getLoader().load(context,url,imageView);
    }
    public void load(Context context, Integer resourceId, ImageView imageView){

        getLoader().load(context,resourceId,imageView);
    }


}
