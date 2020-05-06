package com.cjf.ui.refresh;

import android.content.Context;
import android.view.View;

import com.cjf.ui.progress.ProgressLayout;
import com.cjf.ui.view.HintLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

/**
 * <p>Title: SimpleLoadView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/17 21:27
 */
public class SimpleLoadView implements LoadView {
    @DrawableRes
    private
    int mEmptyDrawable;
    @DrawableRes
    private
    int mFailDrawable;
    @DrawableRes
    private
    int mNetworkDrawable;

    @Override
    public View getEmptyView(Context context) {
        if (mEmptyDrawable == 0) {
            return HintLayout.newEmptyLayout(context);
        }
        return HintLayout.newEmptyLayout(context, mEmptyDrawable);
    }

    @Override
    public View getLoadingView(Context context) {
        ProgressLayout progressLayout = new ProgressLayout(context);
        progressLayout.clearBackground();
        progressLayout.setProgressColorPrimary();
        return progressLayout;
    }

    @Override
    public View getLoadFailView(Context context) {
        if (mFailDrawable == 0) {
            return HintLayout.newErrorRequestLayout(context);
        }
        return HintLayout.newErrorRequestLayout(context, mFailDrawable);
    }

    @Override
    public View getLoadNetworkView(Context context) {
        if (mNetworkDrawable == 0) {
            return HintLayout.newErrorNetworkLayout(context);
        }
        return HintLayout.newErrorNetworkLayout(context, mNetworkDrawable);
    }

    @Override
    public View getLoadView(Context context, @Nullable View targetView, LoadStaus loadStaus) {
        switch (loadStaus) {
            case Fail:
                if (targetView == null) {
                    targetView = getLoadFailView(context);
                }
                break;
            case Empty:
                if (targetView == null) {
                    targetView = getEmptyView(context);
                }
                break;
            case Network:
                if (targetView == null) {
                    targetView = getLoadNetworkView(context);
                }
                break;
            case Loading:
                if (targetView == null) {
                    targetView = getLoadingView(context);
                }
                break;
        }
        return targetView;
    }

    public void setEmptyDrawable(int emptyDrawable) {
        mEmptyDrawable = emptyDrawable;
    }

    public void setFailDrawable(int failDrawable) {
        mFailDrawable = failDrawable;
    }

    public void setNetworkDrawable(int networkDrawable) {
        mNetworkDrawable = networkDrawable;
    }
}
