package com.cjf.demo;

import android.graphics.Color;
import android.os.Bundle;

import com.cjf.ui.bounce.BounceRecyclerView;
import com.cjf.ui.entities.DefaultCompoundItemBean;
import com.cjf.ui.recycler.adapter.DefaultSingleCheckBoxAdapter;
import com.cjf.ui.recycler.adapter.SingleCompoundAdapter;
import com.cjf.ui.refresh.OnRefreshPagerListener;
import com.cjf.ui.refresh.RefreshPagerLayout;
import com.cjf.ui.refresh.SwipeRefreshPagerLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * <p>Title: SwipeRecyclerViewActivity </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 15:04
 */
public class SwipeRecyclerViewActivity extends AppCompatActivity implements OnRefreshPagerListener {

    private SwipeRefreshPagerLayout mSwipeRefreshPagerLayout;
    private BounceRecyclerView mRecyclerView;
    private int mCount = 10;
    private DefaultSingleCheckBoxAdapter mSwipeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recycler_view);
        initView();
    }

    private void initView() {
        mSwipeRefreshPagerLayout = (SwipeRefreshPagerLayout) findViewById(R.id.swipeRefreshPagerLayout);
        mRecyclerView = (BounceRecyclerView) findViewById(R.id.recyclerView);
//        mSwipeAdapter = new SwipeAdapter(new ArrayList<>());
        mSwipeAdapter = new DefaultSingleCheckBoxAdapter();
        mSwipeAdapter.setAnimationFirstOnly(true);
        mRecyclerView.disableBounce();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSwipeAdapter);
        mSwipeRefreshPagerLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW, Color.GREEN);
        mSwipeRefreshPagerLayout.setAdapter(mSwipeAdapter);
        mSwipeRefreshPagerLayout.disableLoadMore();
//        mSwipeRefreshPagerLayout.disableRefresh();
        mSwipeRefreshPagerLayout.setOnRefreshPagerListener(this);
        mSwipeRefreshPagerLayout.startRefresh();
    }

    @Override
    public void onPagerRefresh(RefreshPagerLayout refreshPagerLayout, boolean isRefresh, long pageIndex, long pageSize) {
        if (isRefresh) {
            mSwipeAdapter.getItemCount();
            new Thread(() -> {
                List<DefaultCompoundItemBean> stringItemList = new ArrayList<>();
                try {
                    mCount = 30;
                    for (int i = 0; i < mCount; i++) {
                        stringItemList.add(new DefaultCompoundItemBean("position=" + i));
                    }
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    updateAdapter(stringItemList);
                }
            }).start();
        } else {
            final int itemCount = mSwipeAdapter.getItemCount();
            new Thread(() -> {
                List<DefaultCompoundItemBean> stringItemList = new ArrayList<>();
                try {
                    if (itemCount > 50) {
                        mCount--;
                    }
                    for (int i = 0; i < mCount; i++) {
                        stringItemList.add(new DefaultCompoundItemBean("position=" + (itemCount + i)));
                    }
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    loadMoreAdapter(stringItemList);
                }
            }).start();
        }
    }

    private void loadMoreAdapter(List<DefaultCompoundItemBean> itemList) {
        runOnUiThread(() -> {
            mSwipeAdapter.addData(itemList);
            mSwipeRefreshPagerLayout.finishEnd(itemList.size());
        });
    }

    private void updateAdapter(List<DefaultCompoundItemBean> itemList) {
        runOnUiThread(() -> {
//            mSwipeRefreshPagerLayout.finishEnd(itemList.size());
            mSwipeRefreshPagerLayout.refreshNetwork();
            mSwipeRefreshPagerLayout.disableBoth();
            mRecyclerView.openBothVertical();
        });
    }
}
