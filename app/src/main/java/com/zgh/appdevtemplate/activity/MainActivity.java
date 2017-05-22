package com.zgh.appdevtemplate.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.zgh.appdevtemplate.R;
import com.zgh.appdevtemplate.base.BaseActivity;
import com.zgh.appdevtemplate.constant.Urls;
import com.zgh.appdevtemplate.event.EventCenter;
import com.zgh.appdevtemplate.updata.UpdataService;
import com.zgh.appdevtemplate.utils.AppUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
//        if (savedInstanceState == null) {
//            loadRootFragment(R.id.fl_mainActivity, MainFragment.newInstance());
//        }
        checkOption();
        checkUpdata();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter event) {

    }

    private void checkUpdata() {
        int localVer = AppUtils.getAppVersionCode(this);
        // TODO: 2017/5/18  换成线上的版本号
        int remoteVer = 2;
        if (remoteVer > localVer) {
            updata();
        }
    }

    private void updata() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("发现新版本，是否更新");
        builder.setCancelable(false);
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(Urls.UPDATE_URL)) {
                    UpdataService.startService(MainActivity.this, Urls.UPDATE_URL);
                }
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    //检查是否开启了 不保留活动 选项
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void checkOption() {
        int alwaysFinish = Settings.Global.getInt(getContentResolver(),
                                                  Settings.Global.ALWAYS_FINISH_ACTIVITIES, 0);
        if (alwaysFinish == 1) {
            Dialog dialog = null;
            dialog = new AlertDialog.Builder(this)
                    .setMessage("由于您已开启'不保留活动',导致部分功能无法正常使用.我们建议您点击左下方'设置'按钮,在'开发者选项'中关闭'不保留活动'功能.")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
                            startActivity(intent);
                        }
                    })
                    .create();
            dialog.show();
        }
    }
}
