package com.cjf.ui.chip;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.cjf.ui.base.ContextAction;
import com.cjf.ui.base.OnChipGroupCheckedChangeListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ChipGroupLayout </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/3 20:53
 */
public class ChipGroupLayout extends ChipGroup implements ContextAction {

    private final List<String> mChipAllList = new ArrayList<>();
    private final List<String> mCheckList = new ArrayList<>();
    private int mChipCheckPosition;

    private final List<OnChipGroupCheckedChangeListener> mOnChipGroupCheckedChangeListenerList = new ArrayList<>();
    private ChipFunction mChipFunction;
    private Chip mChipCheck;

    public ChipGroupLayout(Context context) {
        super(context);
    }

    public ChipGroupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChipGroupLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        return super.addViewInLayout(child, index, params);
    }

    public void addViewInLayout(View... childList) {
        for (View child : childList) {
            int index = this.getChildCount();
            addViewInLayout(child, index);
        }
    }

    public void addViewInLayout(View child, int index) {
        addViewInLayout(child, index, this.getLayoutParams());
    }

    /**
     * 添加需要选择的chip
     *
     * @param chipArrays 选项
     * @return
     */
    public void addChip(List<String> chipArrays) {
        addChip(true, null, chipArrays);
    }

    /**
     * 添加需要选择的chip
     *
     * @param checkChipText 选中的文字
     * @param chipArrays    选项
     * @return
     */
    public void addChip(String checkChipText, List<String> chipArrays) {
        addChip(true, checkChipText, chipArrays);
    }

    /**
     * 添加需要选择的chip
     *
     * @param clickable  是否可以点击(选择)
     * @param chipArrays 选项
     * @return
     */
    public void addChip(boolean clickable, List<String> chipArrays) {
        addChip(clickable, null, chipArrays);
    }

    /**
     * 添加需要选择的chip
     *
     * @param clickable     是否可以选择
     * @param checkChipText 选中的文字
     * @param chipArrays    选项
     * @return
     */
    public void addChip(boolean clickable, String checkChipText, List<String> chipArrays) {
        if (chipArrays == null || chipArrays.size() == 0) {
            return;
        }
        if (getChildCount() > 0) {
            removeAllViews();
        }
        mChipAllList.clear();
        mChipAllList.addAll(chipArrays);
        super.setClickable(clickable);
        if (!TextUtils.isEmpty(checkChipText)) {
            mChipCheckPosition = mChipAllList.indexOf(checkChipText);
        } else {
            if (!mChipAllList.isEmpty()) {
                mChipCheckPosition = 0;
            }
        }
        int checkId = 0;
        for (String chipText : chipArrays) {
            Chip chip;
            if (mChipFunction != null) {
                chip = mChipFunction.createChip();
            } else {
                chip = ChipUtils.createTagTextView(getContext(), chipText);
            }
            if (chip.getId() == 0){
                chip.setId(generateViewId());
            }
            // 设置是否可以点击选择
            if (clickable) {
                chip.setOnClickListener(v -> {
                    mChipCheck = (Chip) v;
                    String checkText = castToString(mChipCheck.getText());
                    mChipCheckPosition = mChipAllList.indexOf(checkText);
                    if (mChipCheck.isChecked()) {
                        // 添加
                        if (!mCheckList.contains(checkText)) {
                            mCheckList.add(checkText);
                        }
                    } else {
                        // 删除
                        mCheckList.remove(checkText);
                    }
                    for (OnChipGroupCheckedChangeListener listener : mOnChipGroupCheckedChangeListenerList) {
                        listener.onCheckedChanged(this, mChipCheck.getId(), checkText, mChipCheck.isChecked());
                    }
                });
            } else {
                chip.setOnClickListener(null);
                chip.setClickable(false);
            }
            if (isSingleSelection()) {
                // 单选
                if (!TextUtils.isEmpty(checkChipText) && TextUtils.equals(chipText, checkChipText)) {
                    checkId = chip.getId();
                    mChipCheck = chip;
                }
            }
            addView(chip);
        }
        if (isSingleSelection()) {
            if (checkId != 0) {
                check(checkId);
            } else {
                mChipCheck = (Chip) getChildAt(0);
                check(mChipCheck.getId());
            }
        }
    }

    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int checkId = 0;
        for (int i = 0; i < getChildCount(); i++) {
            Chip childAt = (Chip) getChildAt(i);
            // 设置是否可以点击选择
            if (clickable) {
                childAt.setOnClickListener(v -> {
                    mChipCheck = (Chip) v;
                    String checkText = castToString(mChipCheck.getText());
                    mChipCheckPosition = mChipAllList.indexOf(checkText);
                    if (childAt.isChecked()) {
                        // 添加
                        if (!mCheckList.contains(checkText)) {
                            mCheckList.add(checkText);
                        }
                    } else {
                        // 删除
                        mCheckList.remove(checkText);
                    }
                    for (OnChipGroupCheckedChangeListener listener : mOnChipGroupCheckedChangeListenerList) {
                        listener.onCheckedChanged(this, mChipCheck.getId(), checkText, mChipCheck.isChecked());
                    }
                });
            } else {
                childAt.setOnClickListener(null);
                childAt.setClickable(false);
            }
        }
        check(checkId);
    }

    public void check(String checkText) {
        if (TextUtils.isEmpty(checkText)) {
            return;
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int checkId = 0;
        for (int i = 0; i < childCount; i++) {
            Chip childAt = (Chip) getChildAt(i);
            if (isSingleSelection()) {
                // 单选
                if (!TextUtils.isEmpty(checkText) && TextUtils.equals(checkText, childAt.getText())) {
                    if (!mCheckList.contains(checkText)) {
                        mCheckList.add(checkText);
                    }
                    mChipCheckPosition = mChipAllList.indexOf(checkText);
                    checkId = childAt.getId();
                    break;
                } else {
                    mCheckList.remove(checkText);
                }
            } else {
                // 多选
                if (!TextUtils.isEmpty(checkText) && TextUtils.equals(checkText, childAt.getText())) {
                    childAt.setChecked(true);
                    if (!mCheckList.contains(checkText)) {
                        mCheckList.add(checkText);
                    }
                } else {
                    childAt.setChecked(false);
                    mCheckList.remove(checkText);
                }
            }
        }
        if (isSingleSelection()) {
            check(checkId);
        }
    }

    public void check(List<String> checkTextList) {
        if (checkTextList == null || checkTextList.size() == 0) {
            return;
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int checkId = 0;
        for (int i = 0; i < childCount; i++) {
            Chip childAt = (Chip) getChildAt(i);
            for (String checkText : checkTextList) {
                if (!TextUtils.isEmpty(checkText) && TextUtils.equals(checkText, childAt.getText())) {
                    childAt.setChecked(true);
                    if (!mCheckList.contains(checkText)) {
                        mCheckList.add(checkText);
                    }
                } else {
                    childAt.setChecked(false);
                    mCheckList.remove(checkText);
                }
            }
        }
        if (isSingleSelection()) {
            check(checkId);
        }
    }

    public void checkAll(boolean isChecked) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            Chip childAt = (Chip) getChildAt(i);
            childAt.setChecked(isChecked);
        }
    }

    public void setChipAction() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            Chip childAt = (Chip) getChildAt(i);
            ChipUtils.setChipAction(childAt);
        }
    }

    public void setChipFilter() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            Chip childAt = (Chip) getChildAt(i);
            ChipUtils.setChipFilter(childAt);
        }
    }

    public void setChipChoice() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            Chip childAt = (Chip) getChildAt(i);
            ChipUtils.setChipChoice(childAt);
        }
    }

    public void setChipEntry() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            Chip childAt = (Chip) getChildAt(i);
            ChipUtils.setChipEntry(childAt);
        }
    }

    public void addOnChipGroupCheckedChangeListener(OnChipGroupCheckedChangeListener listener) {
        mOnChipGroupCheckedChangeListenerList.add(listener);
    }

    public List<OnChipGroupCheckedChangeListener> getOnChipGroupCheckedChangeListenerList() {
        return mOnChipGroupCheckedChangeListenerList;
    }

    public List<String> getCheckList() {
        return mCheckList;
    }

    public List<String> getChipAllList() {
        return mChipAllList;
    }

    public int getChipCheckPosition() {
        return mChipCheckPosition;
    }

    public String getChipCheckText() {
        if (getChipCheck() == null || !getChipCheck().isChecked()) {
            return null;
        }
        if (mChipAllList.size() == 0 || mChipAllList.size() < getChipCheckPosition()) {
            return null;
        }
        return mChipAllList.get(getChipCheckPosition());
    }

    public Chip getChipCheck() {
        return mChipCheck;
    }

    public void createChip(ChipFunction chipFunction) {
        mChipFunction = chipFunction;
    }

    public interface ChipFunction {
        Chip createChip();
    }
}
