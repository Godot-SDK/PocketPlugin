package com.xiaofengyu.travel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.zh.pocket.ads.LEADSdk;
import com.zh.pocket.base.LESdk;
import com.zh.pocket.base.common.config.LEConfig;
import com.zh.pocket.base.imageloader.GlideImageLoader;
//这个是测试用的自定义Application
public class MyApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i("MyApp","初始化sdk");
        LEConfig.setEnableLogger(true);
        //在这里加一个runOnUiThread线程试试
        LESdk.initSDK("xiaomi", "1");
        LEADSdk.initSDK(this);
        // LESdk.setImageLoader(new GlideImageLoader());
        // LEADSdk.initSDK(this);
        Log.i("MyApp","初始化执行完毕");
        //Toast.makeText(this, "初始化完毕", Toast.LENGTH_SHORT).show();
    }

}
