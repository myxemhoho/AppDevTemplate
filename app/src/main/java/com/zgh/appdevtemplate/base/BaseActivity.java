package com.zgh.appdevtemplate.base;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

import com.jaeger.library.StatusBarUtil;
import com.zgh.appdevtemplate.R;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by ZGH on 2017/4/18.
 */

public class BaseActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBarColor(getResources().getColor(R.color.colorAccent));
    }

    /**
     * 设置状态栏颜色,默认是带有 112 透明度的，
     * 也可以重写此方法，使用 StatusBarUtil 的其他方式设置状态栏
     */
    protected void setStatusBarColor(@ColorInt int color) {
        StatusBarUtil.setColor(this, color);
    }

}
