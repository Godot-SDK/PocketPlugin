package com.hh128;


import android.app.Activity;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.zh.pocket.ads.banner.BannerAD;
import com.zh.pocket.ads.banner.BannerADListener;
import com.zh.pocket.ads.fullscreen_video.FullscreenVideoAD;
import com.zh.pocket.ads.fullscreen_video.FullscreenVideoADListener;

import com.zh.pocket.ads.interstitial.InterstitialAD;
import com.zh.pocket.ads.interstitial.InterstitialADListener;
import com.zh.pocket.ads.reward_video.RewardVideoAD;
import com.zh.pocket.ads.reward_video.RewardVideoADListener;
import com.zh.pocket.error.ADError;

import androidx.annotation.NonNull;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

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
    //全屏广告信号
    public SignalInfo nativeAdClosed = new SignalInfo("nativeAdClosed");
    public SignalInfo nativeAdFailed = new SignalInfo("nativeAdFailed");
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
        signals.add(nativeAdClosed);
        signals.add(nativeAdFailed);
        return signals;
    }
    @Override
    public View onMainCreate(Activity activity)
    {
        this.layout=new FrameLayout(activity);
        return this.layout;
    }
    //从依赖aar获取字符串
    @UsedByGodot
    public String getStr()
    {
        return "PocketAd";
    }
    /**
     *展示激励视频广告
     */
    @UsedByGodot
    public void showRewardVideoAd(){
        VideoAd = new RewardVideoAD(getActivity(), "");
        VideoAd.setRewardVideoADListener(new PluginRewardVideoAdListener());
        VideoAd.loadAD();
        //VideoAd.showAD();
    }
    /*展示插屏广告*/
    @UsedByGodot
    public void showInterAd()
    {
        InterstitialAD interAd = new InterstitialAD(getActivity(),"");
        interAd.setInterstitialADListener(new PluginInterADListener());
        interAd.showAD();
    }

    /*banner广告*/
    @UsedByGodot
    public void showBannerAd()
    {
      BannerAD ad= new BannerAD(getActivity(), "");
      ad.setBannerADListener(new PluginBannerLinstener());
      //ad.loadAD((ViewGroup)getActivity().getWindow().getDecorView());
    }
    /**
     * 销毁激励视频广告
     */
    @UsedByGodot
    public void destroyRewardVideoAd()
    {
        if (VideoAd != null){
            VideoAd.destroy();
        }
    }
    //全屏视频广告
    @UsedByGodot
    public void showNativeAd()
    {
        FullscreenVideoAD ad =new FullscreenVideoAD(getActivity(),"");
        ad.setFullscreenVideoADListener(new PLuginFullScreenADListener());
          /*  public void onVideoCached()
            {
                Log.i("FullVideo","全屏视频缓存");
            }
            @Override
            public void onADClicked()
            {
                Log.i("FullVideo","全屏视频被点击");
            }
            @Override
            public void onADClosed()
            {
                Log.i("FullVideo","全屏视频被关闭");
                emitSignal(nativeAdClosed.getName());
            }

            @Override
            public void onSuccess()
            {
                emitSignal(nativeAdSuccess.getName());
            }

            @Override
            public void onFailed(ADError adError)
            {
                Log.e("广告播放失败",adError.toString());
                emitSignal(nativeAdFailed.getName());
        });*/
        ad.loadAD();
    }
    @NonNull
    @Override
    public String getPluginName()
    {
        return "PocketPlugin";
    }
    class PluginBannerLinstener implements BannerADListener
    {

        @Override
        public void onFailed(ADError adError) {

        }

        @Override
        public void onADExposure() {

        }

        @Override
        public void onADClicked() {

        }

        @Override
        public void onADClosed() {

        }

        @Override
        public void onSuccess() {

        }
    }
    //静态广告回调
    class PluginInterADListener implements InterstitialADListener
    {
        @Override
        public void onFailed(ADError adError)
        {
            Log.e(this.getClass().toString(),"静态广告播放失败");
            Log.e("静态广告播放失败",adError.toString());
            emitSignal(staticAdFailed.getName());
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
        public void onADLoaded() {

        }
    }
    //奖励广告回调
    class PluginRewardVideoAdListener implements RewardVideoADListener
    {
        //展示广告
        @Override
        public void onADShow() {

        }

        @Override
        public void onADExposure() {

        }

        //素材缓存成功
        @Override
        public void onVideoCached() {

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
        public void onFailed(ADError adError)
        {
            Log.e("RewardVideo","奖励视频播放失败");
            Log.e("RewardVideo",adError.toString());
        }

        @Override
        public void onSuccess()
        {

        }
        //跳过广告
        @Override
        public void onSkippedVideo()
        {
            Log.i("ad","广告被跳过");
        }

        @Override
        public void onADLoaded()
        {

        }
    }
    class PLuginFullScreenADListener implements FullscreenVideoADListener
    {

        @Override
        public void onADLoaded() {

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
        public void onVideoComplete() {

        }

        @Override
        public void onADClosed() {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFailed(ADError adError) {

        }

        @Override
        public void onSkippedVideo() {

        }

        @Override
        public void onPreload() {

        }
    }
}
