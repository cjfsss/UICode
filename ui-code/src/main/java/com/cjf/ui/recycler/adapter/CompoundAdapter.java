package com.cjf.ui.recycler.adapter;

import android.view.View;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cjf.ui.base.CompoundAction;
import com.cjf.ui.entities.CompoundItemBean;
import com.cjf.ui.entities.CompoundSubtitleItemBean;
import com.cjf.ui.item.CompoundItem;
import com.cjf.ui.item.CompoundSubtitleItem;
import com.cjf.ui.item.ItemGroupView;

import java.util.List;

/**
 * <p>Title: CompoundAdapter </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/11 16:11
 */
abstract class CompoundAdapter<T extends CompoundItemBean, Item extends ItemGroupView> extends BaseQuickBindAdapter<T, BaseViewHolder> implements CompoundAction.OnCheckedChangeListener<Item>,
        LoadMoreModule, AdapterNotifyItem {

    protected OnCheckedChangeListener<T, Item> mOnCheckedChangeListener;

    public CompoundAdapter(List<T> data) {
        super(0, data);
    }

    public CompoundAdapter() {
        super(0);
    }

    @Override
    protected void convert(BaseViewHolder holder, T item) {
        View contentView = holder.itemView;
        int position = holder.getAdapterPosition();
        if (contentView instanceof CompoundItem) {
            CompoundItem compoundItem = (CompoundItem) contentView;
            compoundItem.setOnCheckedChangeListener(null);
            compoundItem.setChecked(item.isChecked());
            compoundItem.setOnCheckedChangeListener(this);

            compoundItem.setLeftIcon(item.getIconRes());
            compoundItem.setLeftIcon(item.getIcon());
            compoundItem.setLeftText(item.getLeft());
            compoundItem.setMustFull(false);
            compoundItem.setPosition(position);
        } else if (contentView instanceof CompoundSubtitleItem) {
            CompoundSubtitleItem compoundItem = (CompoundSubtitleItem) contentView;
            compoundItem.setOnCheckedChangeListener(null);
            compoundItem.setChecked(item.isChecked());
            compoundItem.setOnCheckedChangeListener(this);

            compoundItem.setLeftIcon(item.getIconRes());
            compoundItem.setLeftIcon(item.getIcon());
            compoundItem.setLeftText(item.getLeft());
            compoundItem.setMustFull(false);
            compoundItem.setPosition(position);
            if (item instanceof CompoundSubtitleItemBean) {
                CompoundSubtitleItemBean subtitleItem = (CompoundSubtitleItemBean) item;
                compoundItem.setSubtitleText(subtitleItem.getSubtitle());
            }
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener<T, Item> onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    public abstract void onCheckedChanged(Item compoundItem, CompoundButton buttonView, boolean isChecked);

    public interface OnCheckedChangeListener<T extends CompoundItemBean, Item extends ItemGroupView> {

        void onCheckedChanged(Item compoundItem, T checkItem, CompoundButton buttonView, boolean isChecked);
    }

}
