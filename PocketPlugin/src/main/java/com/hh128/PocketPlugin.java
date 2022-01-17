package com.hh128;


import android.app.Activity;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.zh.pocket.ads.banner.BannerAD;
import com.zh.pocket.ads.banner.BannerADListener;
import com.zh.pocket.ads.fullscreen_video.FullscreenVideoAD;
import com.zh.pocket.ads.fullscreen_video.FullscreenVideoADListener;

import com.zh.pocket.ads.interstitial.InterstitialAD;
import com.zh.pocket.ads.interstitial.InterstitialADListener;
import com.zh.pocket.ads.nativ.NativeAD;
import com.zh.pocket.ads.reward_video.RewardVideoAD;
import com.zh.pocket.ads.reward_video.RewardVideoADListener;
import com.zh.pocket.error.ADError;

import androidx.annotation.NonNull;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

import java.util.HashSet;
import java.util.Set;

public class PocketPlugin extends GodotPlugin
{
    //定义信号名字 信号链接是在godot链接的
    public  SignalInfo adReady = new SignalInfo("adReady");

    //激励广告信号
    public  SignalInfo RewardClosed = new SignalInfo("RewardClosed");
    public  SignalInfo RewardGet = new SignalInfo("RewardGet");

    //静态插屏信号
    public SignalInfo InterAdClosed = new SignalInfo("InterAdClosed");
    public SignalInfo InterAdFailed = new SignalInfo("InterAdFailed");
    //全屏广告信号
    public  SignalInfo FullAdSuccess =new SignalInfo("FullAdSuccess");
    public SignalInfo FullAdClosed = new SignalInfo("FullAdClosed");
    public SignalInfo FullAdFailed = new SignalInfo("FullAdFailed");
    //Banner广告信号
    public SignalInfo BannerAdFailed = new SignalInfo("BannerAdFailed");

    public FrameLayout layout;
    public Activity activity;
    public String Tag;
    //插件自己的Godot
    public  Godot plugin_godot;
    public PocketPlugin(Godot godot)
    {
        super(godot);
        activity=getActivity();
        //获取到godot的根布局 并动态添加线性布局用于当做banner广告容器
        //layout = getGodot().getFragmentManager().getFragment();
        Tag= PocketPlugin.class.toString();
    }
    @NonNull
    @Override
    public String getPluginName()
    {
        return "PocketPlugin";
    }
    //添加信号
    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals()
    {
        HashSet<SignalInfo> signals=new HashSet<SignalInfo>();
        signals.add(adReady);
        signals.add(RewardClosed);
        signals.add(RewardGet);
        signals.add(InterAdFailed);
        signals.add(FullAdClosed);
        signals.add(FullAdFailed);
        return signals;
    }
    @Override
    public View onMainCreate(Activity activity)
    {
        this.layout=new FrameLayout(activity);
        return this.layout;
    }
    //获取字符串 测试用
    @UsedByGodot
    public String getStr()
    {
        return "PocketAd";
    }
    /**
     *展示激励视频广告
     */
    @UsedByGodot
    public void showRewardVideoAd(String id){
        RewardVideoAD VideoAd = new RewardVideoAD(getActivity(), id);
        VideoAd.setRewardVideoADListener(new PluginRewardVideoAdListener());
        VideoAd.loadAD();
    }
    //已完成
    /*展示插屏广告*/ //官方测试id6（需配合官方包名）
    @UsedByGodot
    public void showInterAd(String id)
    {
        InterstitialAD interAd = new InterstitialAD(getActivity(),id);
        interAd.setInterstitialADListener(new PluginInterADListener());
        interAd.load();
    }
    //todo
    /*banner广告*/
    @UsedByGodot
    public void showBannerAd(String id)
    {
      BannerAD ad= new BannerAD(getActivity(), id);
      ad.setBannerADListener(new PluginBannerLinstener());
      /* todo 实现广告容器放置
      ViewGroup vg = getActivity().findViewById(R.id.godot_fragment_container);
      vg.addView(layout);
      ad.loadAD(layout);*/
      //ad.loadAD((ViewGroup)getActivity().getWindow().getDecorView());
    }
    /**
     * 销毁激励视频广告
     */
    /*@UsedByGodot
    public void destroyRewardVideoAd()
    {
        if (VideoAd != null){
            VideoAd.destroy();
        }
    }*/
    //半完成
    //全屏视频广告
    @UsedByGodot
    public void showFullAd()
    {
        FullscreenVideoAD ad = new FullscreenVideoAD(getActivity(),"");
        ad.setFullscreenVideoADListener(new PLuginFullScreenADListener());
        ad.loadAD();
    }
    @UsedByGodot
    public void showNativeAd(String id)
    {
       /* NativeAD nativeAD = new NativeAD(getActivity(),id);
        nativeAD.loadAD();*/

    }
    class PluginBannerLinstener implements BannerADListener
    {

        @Override
        public void onFailed(ADError adError) {
            Log.e("静态广告播放失败",adError.toString());
        }

        @Override
        public void onADExposure() {
            Log.d("BannerAd","Banner广告被曝光");
        }

        @Override
        public void onADClicked() {
            Log.d("BannerAd","Banner广告被点击");
        }

        @Override
        public void onADClosed()
        {
            Log.d("BannerAd","Banner广告被关闭");
        }

        @Override
        public void onSuccess()
        {
            Log.d("BannerAd","Banner广告加载成功");
        }
    }
    //静态广告回调
    class PluginInterADListener implements InterstitialADListener
    {
        @Override
        public void onFailed(ADError adError)
        {
            Log.e("InterAd","静态广告播放失败");
            Log.e("InterAd",adError.toString());
            emitSignal(InterAdFailed.getName());
        }

        @Override
        public void onADExposure()
        {
            Log.d("InterAd","静态广告被曝光");
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
        public void onADLoaded()
        {
            Log.d("InterAd","静态广告被加载");
        }
    }
    //奖励广告回调
    class PluginRewardVideoAdListener implements RewardVideoADListener
    {
        //展示广告
        @Override
        public void onADShow()
        {
            Log.i("RewardVideo","奖励视频被展示");
        }

        @Override
        public void onADExposure()
        {
            Log.i("RewardVideo","奖励视频被曝光");
        }

        @Override
        public void onVideoCached()
        {
            Log.i("RewardVideo","奖励视频被缓存");
        }
        //当成功获得奖励
        @Override
        public void onReward() {
            //发送信号,允许获得奖励
            emitSignal(RewardGet.getName());
        }
        //被点击
        @Override
        public void onADClicked()
        {
            Log.i("RewardVideo","广告被点击");
        }
        //当播放完毕
        @Override
        public void onVideoComplete()
        {
            Log.i("RewardVideo","奖励视频播放完毕");
        }
        //当关闭广告
        @Override
        public void onADClosed()
        {
            Log.d("RewardVideo","奖励视频被关闭");
            emitSignal(RewardClosed.getName());
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
            Log.d("RewardVideo","激励视频加载成功");
        }
        @Override
        public void onSkippedVideo()
        {
            Log.i("RewardVideo","广告被跳过");
        }

        @Override
        public void onADLoaded()
        {
            Log.d("RewardVideo","激励视频加载了");
        }
    }
    //完成
    class PLuginFullScreenADListener implements FullscreenVideoADListener
    {

        @Override
        public void onADLoaded()
        {
            Log.d("FullVideo","全屏视频加载了");
        }

        @Override
        public void onVideoCached()
        {
            Log.i("FullVideo","全屏视频缓存了");
        }

        @Override
        public void onADShow()
        {
            Log.i("FullVideo","全屏视频展示了");
        }

        @Override
        public void onADExposure()
        {
            Log.i("FullVideo","全屏视频曝光了");
        }

        @Override
        public void onADClicked()
        {
            Log.i("FullVideo","全屏视频被点击");
        }

        @Override
        public void onVideoComplete() {
            Log.i("FullVideo","全屏广告视频素材播放完毕");
        }

        @Override
        public void onADClosed() {
            Log.i("FullVideo","全屏视频被关闭");
            emitSignal(FullAdClosed.getName());
        }

        @Override
        public void onSuccess()
        {
            emitSignal(FullAdSuccess.getName());
        }

        @Override
        public void onFailed(ADError adError)
        {
            Log.e("广告播放失败",adError.toString());
            emitSignal(FullAdFailed.getName());
        }

        @Override
        public void onSkippedVideo()
        {
            Log.d("FullVideo","全屏广告被跳过");
        }

        @Override
        public void onPreload()
        {
            Log.d("FullVideo","全屏广告预加载");
        }
    }
}
