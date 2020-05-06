package com.cjf.ui.dialog.adapter;

import android.content.Context;
import android.widget.CompoundButton;

import com.cjf.ui.entities.CompoundItemBean;
import com.cjf.ui.item.ItemGroupView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: MultiCompoundAdapter </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/11 18:26
 */
public abstract class MultiCompoundAdapter<T extends CompoundItemBean, Item extends ItemGroupView> extends CompoundAdapter<T, Item> {

    private final List<T> mCheckList = new ArrayList<>();

    public MultiCompoundAdapter(Context context, List<T> data) {
        super(context, data);
    }

    @Override
    public void onCheckedChanged(Item compoundItem, CompoundButton buttonView, boolean isChecked) {
        int position = compoundItem.getPosition();
        T item = getItem(position);
        if (item != null) {
            item.setCheck(isChecked);
            if (item.isChecked()) {
                // 添加
                if (!mCheckList.contains(item)) {
                    mCheckList.add(item);
                }
            } else {
                // 删除
                mCheckList.remove(item);
            }
        }
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(compoundItem,
                    item, buttonView, isChecked);
        }
    }

    public List<T> getCheckList() {
        return mCheckList;
    }
}


