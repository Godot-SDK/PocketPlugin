package com.hh128;


import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.zh.pocket.ads.LEADSdk;
import com.zh.pocket.ads.banner.BannerAD;
import com.zh.pocket.ads.interstitial.InterstitialAD;
import com.zh.pocket.ads.interstitial.InterstitialADListener;
import com.zh.pocket.base.LESdk;
import com.zh.pocket.base.bean.LEError;
import com.zh.pocket.base.common.config.LEConfig;

import androidx.annotation.NonNull;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class StrFromTest extends GodotPlugin
{
    public FrameLayout layout;
    public Activity activity;
    public StrFromTest(Godot godot)
    {
        super(godot);
        activity=getActivity();
    }
    @Override
    public View onMainCreate(Activity activity)
    {
        this.layout=new FrameLayout(activity);
        return this.layout;
    }
    @NonNull
    @Override
    public List<String> getPluginMethods()
    {
        //return Arrays.asList(new String[]{"getStr"});
        return new ArrayList<String>()
        {
            {
                add("getStr");
                add("init");
                add("showAd");
            }
        };
    }
    //从依赖aar获取字符串
    public String getStr()
    {
       return "ADS";
     //  return MyString.mystring;
    }
    //先用测试id
    public void init()
    {
      //  LEConfig.setEnableLogger(true);
    }
    //测试视频广告
    public void showAd()
    {
        InterstitialAD ad= new InterstitialAD(getActivity(), new InterstitialADListener() {
            @Override
            public void onADReceive() {

            }
            @Override
            public void onADExposure() {
            }
            @Override
            public void onADClicked()
            {
            }
            @Override
            public void onADClosed()
            {
            }
            @Override
            public void onSuccess()
            {

            }
            @Override
            public void onFailed(LEError leError)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        Toast.makeText(getActivity(),"失败",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        ad.show();
    }
    @NonNull
    @Override
    public String getPluginName()
    {
        return "StrFromTest";
    }
}
