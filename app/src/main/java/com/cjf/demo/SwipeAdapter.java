package com.cjf.demo;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cjf.ui.base.CompoundAction;
import com.cjf.ui.entities.DefaultCompoundItemBean;
import com.cjf.ui.item.CheckBoxItem;

import java.util.List;

/**
 * <p>Title: SwipeAdapter </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 15:08
 */
public class SwipeAdapter extends BaseQuickAdapter<DefaultCompoundItemBean, BaseViewHolder> implements LoadMoreModule, CompoundAction.OnCheckedChangeListener<CheckBoxItem> {
    private int mCheckPosition = -1;

    public SwipeAdapter(List<DefaultCompoundItemBean> data) {
        super(R.layout.item_check_box, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, DefaultCompoundItemBean item) {
        CheckBoxItem checkBoxItem = (CheckBoxItem) holder.itemView;
//        CheckBoxItem checkBoxItem = holder.getView(R.id.checkboxItem);
        checkBoxItem.setOnCheckedChangeListener(null);
        checkBoxItem.setChecked(item.isChecked());
        checkBoxItem.setOnCheckedChangeListener(this);

        checkBoxItem.setLeftIcon(item.getIconRes());
        checkBoxItem.setLeftIcon(item.getIcon());
        checkBoxItem.setLeftText(item.getLeft());
        checkBoxItem.setMustFull(false);
        checkBoxItem.setPosition(holder.getAdapterPosition());
    }

    @Override
    public void onCheckedChanged(CheckBoxItem compoundItem, CompoundButton buttonView, boolean isChecked) {
        int position = compoundItem.getPosition();
        DefaultCompoundItemBean item = getItem(position);
        if (item != null && position != mCheckPosition) {
            int count = getDefItemCount();
            for (int i = 0; i < count; i++) {
                DefaultCompoundItemBean itemObj = getItem(i);
                if (itemObj != null) {
                    itemObj.setCheck(!isChecked);
                }
            }
            item.setCheck(isChecked);
            notifyDataSetChanged();
        } else {
            if (item != null) {
                item.setCheck(true);
                notifyItemChanged(position);
            }
        }
        mCheckPosition = position;
    }

    public DefaultCompoundItemBean getSingleCheckItem() {
        if (mCheckPosition == -1) {
            return null;
        }
        return getItem(mCheckPosition);
    }
}
