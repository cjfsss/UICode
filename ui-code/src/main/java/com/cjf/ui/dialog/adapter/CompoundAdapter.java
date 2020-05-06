package com.cjf.ui.dialog.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;

import com.cjf.ui.base.CompoundAction;
import com.cjf.ui.entities.CompoundItemBean;
import com.cjf.ui.entities.CompoundSubtitleItemBean;
import com.cjf.ui.item.ItemGroupView;
import com.cjf.ui.list.adapter.CommonAdapter;
import com.cjf.ui.list.adapter.ViewHolder;

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
public abstract class CompoundAdapter<T extends CompoundItemBean, Item extends ItemGroupView> extends CommonAdapter<T> implements CompoundAction.OnCheckedChangeListener<Item> {

    protected OnCheckedChangeListener<T, Item> mOnCheckedChangeListener;

    public CompoundAdapter(Context context, List<T> data) {
        super(context, data);
    }

    @Override
    protected abstract View getView();

    @Override
    protected void convert(ViewHolder viewHolder, T item, int position) {
        View contentView = viewHolder.getContentView();
        if (contentView instanceof com.cjf.ui.item.CompoundItem) {
            com.cjf.ui.item.CompoundItem compoundItem = (com.cjf.ui.item.CompoundItem) contentView;
            compoundItem.setOnCheckedChangeListener(null);
            compoundItem.setChecked(item.isChecked());
            compoundItem.setOnCheckedChangeListener(this);

            compoundItem.setLeftIcon(item.getIconRes());
            compoundItem.setLeftIcon(item.getIcon());
            compoundItem.setLeftText(item.getLeft());
            compoundItem.setMustFull(false);
            compoundItem.setPosition(position);
        } else if (contentView instanceof com.cjf.ui.item.CompoundSubtitleItem) {
            com.cjf.ui.item.CompoundSubtitleItem compoundItem = (com.cjf.ui.item.CompoundSubtitleItem) contentView;
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
    public void onCheckedChanged(Item compoundItem, CompoundButton buttonView, boolean isChecked) {
        T item = getItem(compoundItem.getPosition());
        if (item != null) {
            item.setCheck(isChecked);
        }
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(compoundItem,
                    item, buttonView, isChecked);
        }
    }

    public interface OnCheckedChangeListener<T extends CompoundItemBean, Item extends ItemGroupView> {

        void onCheckedChanged(Item compoundItem, T checkItem, CompoundButton buttonView, boolean isChecked);
    }
}
