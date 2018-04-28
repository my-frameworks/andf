package com.and.framework.common.component;

import android.content.Context;
import android.widget.ImageView;

public interface IImageLoader {
    void load(Context context, String url, ImageView imageView);
    void load(Context context, Integer resourceId, ImageView imageView);
}
