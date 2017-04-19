package com.zgh.appdevtemplate.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ZGH on 2017/4/18.
 */

public class BaseFragment extends SupportFragment implements View.OnTouchListener {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME  = 2000L;
    private              long TOUCH_TIME = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //监听View的焦点改变，不是edittext的话就隐藏键盘
        setOnFocusChangedListenerForChildItems(view);
    }

    //监听View的焦点改变
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

    //监听touch事件，不是edittext就隐藏键盘
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v instanceof EditText) {
            //TODO
        } else {
            hideInputKeyboard(v);
        }
        return false;
    }

    //隐藏键盘
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
}
