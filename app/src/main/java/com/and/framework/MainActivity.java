package com.and.framework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.and.framework.common.ImageLoaderClient;


public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private String url = "http://e.hiphotos.baidu.com/image/pic/item/aa18972bd40735fa324a79d792510fb30f240821.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = (ImageView) findViewById(R.id.image_view);
        ImageLoaderClient.get().load(this,url,imageView);











    }
}
