package com.hh128;


import android.app.Activity;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.qq.e.comm.constants.Sig;
import com.zh.pocket.ads.banner.BannerAD;
import com.zh.pocket.ads.fullscreen_video.FullscreenVideoAD;
import com.zh.pocket.ads.fullscreen_video.FullscreenVideoADListener;

import com.zh.pocket.ads.interstitial.InterstitialAD;
import com.zh.pocket.ads.interstitial.InterstitialADListener;
import com.zh.pocket.ads.reward_video.RewardVideoAD;
import com.zh.pocket.ads.reward_video.RewardVideoADListener;
import com.zh.pocket.base.bean.LEError;

import androidx.annotation.NonNull;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;

import java.net.InterfaceAddress;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PocketPlugin extends GodotPlugin
{
    //定义信号名字 信号链接是在godot链接的
    public  SignalInfo adReady = new SignalInfo("adReady");
    //public SignalInfo adFailed =new SignalInfo("adFailed");
    public  SignalInfo VideoClosed = new SignalInfo("VideoClosed");
    public  SignalInfo VideoReward = new SignalInfo("VideoReward");
    //public SignalInfo adSuccess =new SignalInfo("adSuccess");
    public  SignalInfo nativeAdSuccess =new SignalInfo("nativeAdSuccess");
    //静态插屏信号
    //public SignalInfo staticAdClosed = new SignalInfo("staticAdClosed");
    public SignalInfo staticAdFailed = new SignalInfo("staticAdFailed");
    //public SignalInfo bannerAdFailed = new SignalInfo("bannerAdFailed");

    public RewardVideoAD VideoAd;
    public BannerAD bannerAD;

    public FrameLayout layout;
    public Activity activity;
    public String Tag;
    //插件自己的Godot
    public  Godot plugin_godot;
    public PocketPlugin(Godot godot)
    {
        super(godot);
        activity=getActivity();
        //暂时不用
        //plugin_godot=godot;
        Tag= PocketPlugin.class.toString();
    }

    //添加信号
    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals()
    {
        HashSet<SignalInfo> signals=new HashSet<SignalInfo>();
        signals.add(adReady);
        signals.add(VideoClosed);
        signals.add(VideoReward);
        signals.add(staticAdFailed);
        return signals;
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
        ArrayList<String> plugin_methods=new ArrayList<String>();
        plugin_methods.add("getStr");
        plugin_methods.add("init");
        plugin_methods.add("showInterAd");
        plugin_methods.add("showRewardVideoAd");
        plugin_methods.add("destroyRewardVideoAd");

        //plugin_methods.add("showBannerAd");
        //plugin_methods.add("destroyBannerAd");
        return plugin_methods;
    }
    //从依赖aar获取字符串
    public String getStr()
    {
        return "PocketAd";
        //  return MyString.mystring;
    }
    //先用测试id
    public void init()
    {
        //  LEConfig.setEnableLogger(true);f
    }
    /**
     *展示激励视频广告
     */
    public void showRewardVideoAd(){
        VideoAd = new RewardVideoAD(getActivity(), new PocketRewardL2());
        VideoAd.loadAD();
        //VideoAd.showAD();
    }
    /*展示插屏广告*/
    public void showInterAd()
    {
        InterstitialAD interad = new InterstitialAD(getActivity(),new PocketStaticAdL2());
        interad.show();
    }
    /**
     * 销毁激励视频广告
     */
    public void destroyRewardVideoAd(){
        if (VideoAd != null){
            VideoAd.destroy();
        }
    }
    //全屏视频广告
    public void showNativeAd()
    {
        FullscreenVideoAD ad =new FullscreenVideoAD(getActivity(),new FullscreenVideoADListener(){
            @Override
            public void onADLoad()
            {
            Log.i("FullVideo","全屏视频加载");
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
                Log.e("FullVideo",leError.getMessage());
            }
            @Override
            public void onSkippedVideo()
            {
                Log.i("FullVieo","跳过了视频");
            }
            @Override
            public void onPreload()
            {
                Log.i("FullVideo","全屏视频预加载");
            }
        });
        ad.loadAD();
    }
    @NonNull
    @Override
    public String getPluginName()
    {
        return "PocketPlugin";
    }
    //静态广告回调
    class PocketStaticAdL2 implements InterstitialADListener
    {
        @Override
        public void onADReceive()
        {

        }

        @Override
        public void onADExposure()
        {

        }

        @Override
        public void onADClicked()
        {
            Log.i(this.getClass().toString(),"静态广告被点击");
        }
        @Override
        public void onADClosed()
        {
            Log.i(this.getClass().toString(),"静态广告被关闭");
        }

        @Override
        public void onSuccess()
        {
            Log.i(this.getClass().toString(),"静态广告打开成功");
        }

        @Override
        public void onFailed(LEError leError)
        {
            Log.e(this.getClass().toString(),"静态广告播放失败");
            emitSignal(staticAdFailed.getName());
        }
    }
    //奖励广告回调
    class PocketRewardL2 implements RewardVideoADListener
    {
        //加载成功
        @Override
        public void onADLoad() {
        }
        //展示广告
        @Override
        public void onADShow() {

        }
        //素材缓存成功
        @Override
        public void onVideoCached() {

        }
        //广告出现
        @Override
        public void onADExpose() {
        }
        //当成功获得奖励
        @Override
        public void onReward() {
            //发送信号,允许获得奖励
            //new PocketPlugin(PocketPlugin.plugin_godot).my_emitSignal(PocketPlugin.VideoReward.getName());
            emitSignal(VideoReward.getName());
        }
        //被点击
        @Override
        public void onADClicked()
        {
            Log.i("ad","广告被点击");
        }
        //当播放完毕
        @Override
        public void onVideoComplete()
        {
            Log.i("RewardVideo","奖励视频播放完毕");
        }
        //当关闭广告
        @Override
        public void onADClosed() {
            //关闭信号
           // new PocketPlugin(PocketPlugin.plugin_godot).my_emitSignal(PocketPlugin.VideoClosed.getName());
            emitSignal(VideoClosed.getName());
        }

        @Override
        public void onSuccess()
        {

        }
        //加载失败
        @Override
        public void onFailed(LEError leError)
        {
            Log.e("RewardVideo","奖励视频播放失败");
            Log.e("RewardVideo",leError.getMessage());
        }
        //跳过广告
        @Override
        public void onSkippedVideo()
        {
            Log.i("ad","广告被跳过");
        }
        //预加载
        @Override
        public void onPreload()
        {
            Log.i("RewardVideo","奖励视频预加载");
        }
    }
}
