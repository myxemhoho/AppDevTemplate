package com.zgh.appdevtemplate.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zgh.appdevtemplate.R;
import com.zgh.appdevtemplate.base.BaseListFragment;
import com.zgh.appdevtemplate.event.EventCenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseListFragment<String> {


    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        Bundle args = new Bundle();
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    protected void getBundleExtras(Bundle arguments) {

    }

    @Override
    protected void onEventComing(EventCenter event) {

    }

    @Override
    protected void initHeaderView(View headerView) {

    }

    @Override
    protected void onRefreshListener() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    protected void initItemLayout() {
        setItemLayout(R.layout.item_base_recyclerview);
    }

    @Override
    protected void initRecyclerView() {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            data.add("" + i);
        }

        openRefresh(R.color.md_deep_orange_600, R.color.md_deep_orange_800,
                    R.color.md_deep_orange_A100, R.color.md_deep_orange_A400);
        setListType(GRID_LAYOUT,true,3);
        setHeaderView(getActivity(), R.layout.search_view);
        mAdapter.addData(data);
    }

    @Override
    protected void onLoadMoreLister() {

    }

    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, String s) {

    }
}
