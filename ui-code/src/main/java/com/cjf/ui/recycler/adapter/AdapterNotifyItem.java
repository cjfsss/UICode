package com.cjf.ui.recycler.adapter;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>Title: AdapterNotifyItem </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 4:04
 */
public interface AdapterNotifyItem {

    default void notifyItemRangeChanged(RecyclerView recyclerView, int adapterPosition) {
        notifyItemRangeChanged(recyclerView, adapterPosition, 1);
    }

    default void notifyItemRangeChanged(RecyclerView recyclerView, int adapterPosition, int size) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.isComputingLayout()) {
            // 延时递归处理。
            recyclerView.postDelayed(() -> {
                notifyItemRangeChanged(recyclerView, adapterPosition, size);
            }, 100);
        } else {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                // 刷新
                adapter.notifyItemRangeChanged(adapterPosition, size, 100);
            }
        }
    }

}
