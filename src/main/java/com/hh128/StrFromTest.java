package com.hh128;


import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

//import com.example.mystrinng.MyString;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;

import java.util.Arrays;
import java.util.List;
public class StrFromTest extends GodotPlugin
{
    public FrameLayout layout;
    public StrFromTest(Godot godot) {
        super(godot);
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
        return Arrays.asList(new String[]{"getStr"});
    }
    //从依赖aar获取字符串
    public String getStr()
    {
       return "ADS";
       //return MyString.mystring;
    }
    @NonNull
    @Override
    public String getPluginName()
    {
        return "StrFromTest";
    }
}
