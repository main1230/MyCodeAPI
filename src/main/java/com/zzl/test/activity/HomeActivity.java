package com.zzl.test.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zzl.test.R;
import com.zzl.test.adapter.HomeAdapter;

/**
 * Description:
 * Created by zhangzl
 * Date: 15/7/21
 */
public class HomeActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    private String[] data_ = {"RecylerView", "Java", "PHP", "Python"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initUI() {
        setContentView(R.layout.activity_homt);

        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        homeAdapter = new HomeAdapter();
        homeAdapter.setData(data_);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnClickListener(new HomeAdapter.OnClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                // 点击事件处理
                showToastMsg(data_[position]);
                dealClickListener(position);
            }
        });
    }

    private void dealClickListener(int position) {
        switch (position) {
            default:
                jumpToPage(RecylerViewActivity.class);
                break;
        }
    }
}
