package com.cjf.ui.recycler.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * <p>Title: BaseQuickBindAdapter </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 18:39
 */
public abstract class BaseQuickBindAdapter<T, VH extends BaseViewHolder> extends BaseQuickAdapter<T, VH> implements AdapterNotifyItem {

    public BaseQuickBindAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public BaseQuickBindAdapter(int layoutResId) {
        super(layoutResId);
    }

    public int getCount() {
        return getDefItemCount();
    }


    protected abstract View getView(ViewGroup parent, int viewType);

}
