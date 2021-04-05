package com.hh128;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.zh.pocket.ads.banner.BannerAD;
import com.zh.pocket.ads.banner.BannerADListener;
import com.zh.pocket.base.bean.LEError;

public class pocked_plugin_banner extends Activity
{
    @Override
    protected void onCreate(@Nullable @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocked_plugin_banner);
        ViewGroup banner=findViewById(R.id.my_banner);
        BannerAD bannerAD = new BannerAD(this, new BannerADListener() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onFailed(LEError leError)
            {
                Log.e(this.toString(),leError.getMessage());
            }
            @Override
            public void onADExposure()
            {
            }
            @Override
            public void onADClicked() {

            }
            @Override
            public void onADClosed() {
            }
        });
        bannerAD.loadAD(banner);
    }
}
