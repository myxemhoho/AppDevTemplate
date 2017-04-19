package com.zgh.appdevtemplate.base;

import cn.jpush.android.api.JPushInterface;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by ZGH on 2017/4/18.
 */

public class BaseActivity extends SupportActivity {

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);//极光推送统计分析 API
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);//极光推送统计分析 API
    }
}
