package com.cjf.ui.dialog.adapter;

import android.content.Context;
import android.widget.CompoundButton;

import com.cjf.ui.entities.CompoundItemBean;
import com.cjf.ui.item.ItemGroupView;

import java.util.List;

/**
 * <p>Title: SingleCompoundAdapter </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/11 17:08
 */
public abstract class SingleCompoundAdapter<T extends CompoundItemBean, Item extends ItemGroupView> extends CompoundAdapter<T, Item> {

    private int mCheckPosition = -1;

    public SingleCompoundAdapter(Context context, List<T> data) {
        super(context, data);
    }

    public void onCheckedChanged(Item compoundItem, CompoundButton buttonView, boolean isChecked) {
        int position = compoundItem.getPosition();
        T item = getItem(position);
        if (item != null && position != mCheckPosition) {
            int count = getCount();
            for (int i = 0; i < count; i++) {
                T itemObj = getItem(i);
                if (itemObj != null) {
                    itemObj.setCheck(!isChecked);
                }
            }
            item.setCheck(isChecked);
            notifyDataSetChanged();
        } else {
            if (item != null) {
                item.setCheck(true);
                notifyDataSetChanged();
            }
        }
        mCheckPosition = position;
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(compoundItem,
                    item, buttonView, isChecked);
        }
    }

    public T getSingleCheckItem() {
        if (mCheckPosition == -1) {
            return null;
        }
        return getItem(mCheckPosition);
    }
}
