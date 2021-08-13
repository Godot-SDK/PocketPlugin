package com.hh128;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.qq.e.comm.constants.Sig;
import com.zh.pocket.ads.LEADSdk;
import com.zh.pocket.ads.banner.BannerAD;
import com.zh.pocket.ads.banner.BannerADListener;
import com.zh.pocket.ads.fullscreen_video.FullscreenVideoAD;
import com.zh.pocket.ads.fullscreen_video.FullscreenVideoADListener;
import com.zh.pocket.ads.interstitial.InterstitialAD;
import com.zh.pocket.ads.interstitial.InterstitialADListener;
import com.zh.pocket.ads.reward_video.RewardVideoADListener;
import com.zh.pocket.base.LESdk;
import com.zh.pocket.base.bean.LEError;
import com.zh.pocket.base.common.config.LEConfig;

import androidx.annotation.NonNull;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StrFromTest //extends GodotPlugin
{}/*
    public SignalInfo adReady = new SignalInfo("adReady");
    public SignalInfo adFailed =new SignalInfo("adFailed");
    public SignalInfo adSuccess =new SignalInfo("adSuccess");
    public SignalInfo nativeAdSuccess =new SignalInfo("nativeAdSuccess");
    public SignalInfo bannerAdFailed = new SignalInfo("bannerAdFailed");
    public FrameLayout layout;
    public Activity activity;
    public String Tag;
    public StrFromTest(Godot godot)
    {
        super(godot);
        activity=getActivity();
        Tag=StrFromTest.class.toString();
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
        ArrayList<String> a=new ArrayList<String>();
        a.add("getStr");
        a.add("init");
        a.add("showInterAd");
        a.add("showNativeAd");
        a.add("showBanner");
        a.add("showFullAd");
        return a;
    }
    //信号
    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals()
    {

        HashSet<SignalInfo> signals=new HashSet<SignalInfo>();
        signals.add(adReady);
        return signals;
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
    public void showInterAd()
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
                emitSignal(adFailed.getName());
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
    public void showNativeAd()
    {
        FullscreenVideoAD ad =new FullscreenVideoAD(getActivity(),new FullscreenVideoADListener(){
            @Override
            public void onADLoad()
            {

            }

            @Override
            public void onVideoCached() {

            }

            @Override
            public void onADShow() {

            }

            @Override
            public void onADExposure() {

            }

            @Override
            public void onADClicked() {

            }

            @Override
            public void onVideoComplete()
            {

            }

            @Override
            public void onADClosed()
            {

            }

            @Override
            public void onSuccess()
            {
                emitSignal(nativeAdSuccess.getName());
            }

            @Override
            public void onError(LEError leError)
            {
                Log.e(StrFromTest.class.toString(),leError.toString());
            }
            @Override
            public void onSkippedVideo()
            {
            }
            @Override
            public void onPreload()
            {
            }
        });
    }
    public void showBanner()
    {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
               //Intent i=new Intent(activity,pocked_plugin_banner.class);
              // activity.startActivity(i);
            }
        });
    }
    public void showFullAd()
    {
        final FullscreenVideoAD fullscreenVideoAD=new FullscreenVideoAD(activity, new FullscreenVideoADListener()
        {
            @Override
            public void onADLoad()
            {

            }

            @Override
            public void onVideoCached()
            {

            }

            @Override
            public void onADShow()
            {

            }

            @Override
            public void onADExposure()
            {

            }

            @Override
            public void onADClicked()
            {

            }

            @Override
            public void onVideoComplete()
            {

            }

            @Override
            public void onADClosed() { }

            @Override
            public void onSuccess() { }

            @Override
            public void onError(LEError leError) {
                Log.e(StrFromTest.class.toString(),leError.getMessage());
            }
            @Override
            public void onSkippedVideo() {
            }
            @Override
            public void onPreload() {
            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fullscreenVideoAD.loadAD();
            }
        });

    }
    @NonNull
    @Override
    public String getPluginName()
    {
        return "StrFromTest";
    }
}*/
