package com.and.framework.common.component;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideImageLoader implements IImageLoader {
    @Override
    public void load(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);

    }

    @Override
    public void load(Context context, Integer resourceId, ImageView imageView) {
    Glide.with(context).load(resourceId).into(imageView);

    }

}
