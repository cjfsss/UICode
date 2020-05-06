package com.cjf.ui.refresh;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.loadmore.LoadMoreStatus;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import androidx.annotation.Nullable;

/**
 * <p>Title: LoadView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/17 20:57
 */
public interface LoadView {

    /**
     * 布局中的 加载空视图
     *
     * @return View
     */
    View getEmptyView(Context context);

    /**
     * 布局中的 加载更多视图
     *
     * @return View
     */
    View getLoadingView(Context context);

    /**
     * 布局中的 加载失败布局
     *
     * @return View
     */
    View getLoadFailView(Context context);

    /**
     * 布局中的 加载失败布局
     *
     * @return View
     */
    View getLoadNetworkView(Context context);

    /**
     * 可重写此方式，实行自定义逻辑
     */
    View getLoadView(Context context, @Nullable View targetView, LoadStaus loadStaus);
}
