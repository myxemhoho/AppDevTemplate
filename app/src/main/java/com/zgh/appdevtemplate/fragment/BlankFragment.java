package com.zgh.appdevtemplate.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zgh.appdevtemplate.R;
import com.zgh.appdevtemplate.base.BaseFragment;
import com.zgh.appdevtemplate.event.EventCenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment {


    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance() {
        Bundle args = new Bundle();
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    protected void getBundleExtras(Bundle arguments) {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void onEventComing(EventCenter event) {

    }

}
