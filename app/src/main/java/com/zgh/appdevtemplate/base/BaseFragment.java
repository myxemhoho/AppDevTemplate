package com.zgh.appdevtemplate.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.zgh.appdevtemplate.R;
import com.zgh.appdevtemplate.event.EventCenter;
import com.zgh.appdevtemplate.view.TitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ZGH on 2017/4/18.
 */

public abstract class BaseFragment extends SupportFragment implements View.OnTouchListener {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME  = 2000L;
    private              long TOUCH_TIME = 0;

    public View mView;
    public TitleView mTitleView;

    public BaseFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            getBundleExtras(getArguments());
        }
        EventBus.getDefault().register(this);
        mView = initContentView(inflater, container, savedInstanceState);
        //监听View的焦点改变，不是edittext的话就隐藏键盘
        setOnFocusChangedListenerForChildItems(mView);
        return mView;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mTitleView = (TitleView) mView.findViewById(R.id.titleView);
        if (mTitleView != null) {
            titleLeftBtnEvent();
        }

        initView(mView);
    }

    /**
     * 初始化 UI
     */
    protected abstract View initContentView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 处理传入的数据
     */
    protected abstract void getBundleExtras(Bundle arguments);

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

    /**
     * EventBus 接收消息
     */
    @Subscribe
    public void onEventAsync(EventCenter event) {
        if (event != null) {
            onEventComing(event);
        }
    }

    /**
     * 接收消息后的处理逻辑
     */
    protected abstract void onEventComing(EventCenter event);

    /**
     * titleView 左边按钮逻辑，一般为返回按钮，所以作为公共逻辑，
     * 如果不是回退操作，则重写此方法
     */
    protected void titleLeftBtnEvent() {
        Drawable drawable = mTitleView.getLeft_ibtn().getDrawable();
        if (drawable != null) {
            mTitleView.getLeft_ibtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop();
                }
            });
        }
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//    }

    /**
     * 监听View的焦点改变
     */
    private void setOnFocusChangedListenerForChildItems(View view) {
        if (view != null) {
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    setOnFocusChangedListenerForChildItems(((ViewGroup) view).getChildAt(i));
                }
            }
            view.setOnTouchListener(this);
        }
    }

    /**
     * 监听touch事件，不是edittext就隐藏键盘
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v instanceof EditText) {
            //TODO
        } else {
            hideInputKeyboard(v);
        }
        return false;
    }

    /**
     * 隐藏键盘
     */
    private void hideInputKeyboard(View v) {
        InputMethodManager imm =
                (InputMethodManager) _mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * 处理回退事件
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
