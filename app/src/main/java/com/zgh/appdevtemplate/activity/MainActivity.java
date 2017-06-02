package com.zgh.appdevtemplate.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.blankj.utilcode.util.KeyboardUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zgh.appdevtemplate.R;
import com.zgh.appdevtemplate.base.BaseActivity;
import com.zgh.appdevtemplate.constant.Urls;
import com.zgh.appdevtemplate.event.EventCenter;
import com.zgh.appdevtemplate.fragment.ListFragment;
import com.zgh.appdevtemplate.model.TabEntity;
import com.zgh.appdevtemplate.update.UpdataService;
import com.zgh.appdevtemplate.util.AppUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities     = new ArrayList<>();
    private String[]                   mTitles          = {"首页", "消息", "联系人", "更多"};
    private int[]                      mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[]                      mIconSelectIds   = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        //使用 ViewPager 及 Fragment 实现项目整体架构,与下面 BottomBar 的方式二选一
        viewPagerWithFragment();
        //使用 BottomBar 及 Fragment 实现项目整体架构
//        if (savedInstanceState == null) {
//            loadRootFragment(R.id.fl_mainActivity, MainFragment.newInstance());
//        }

        checkOption();
        checkUpdate();
    }

    private void viewPagerWithFragment() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
            mFragments.add(ListFragment.newInstance());
        }
        final CommonTabLayout tabLayout = (CommonTabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabData(mTabEntities);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main);
        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initView() {
        KeyboardUtils.clickBlankArea2HideSoftInput();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter event) {

    }

    private void checkUpdate() {
        int localVer = AppUtils.getAppVersionCode(this);
        // TODO: 2017/5/18  换成线上的版本号
        int remoteVer = 0;
        if (remoteVer > localVer) {
            update();
        }
    }

    private void update() {
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

    private class MyPageAdapter extends FragmentPagerAdapter {
        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
