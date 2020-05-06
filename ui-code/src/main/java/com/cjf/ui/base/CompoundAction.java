package com.cjf.ui.base;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;

import com.cjf.ui.R;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.switchmaterial.SwitchMaterial;


/**
 * <p>Title: CompoundAction </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/3 16:14
 */
public interface CompoundAction<SELF> extends ContextAction, CompoundButton.OnCheckedChangeListener {

    View getClickView();

    CompoundButton getCompoundButton();

    default boolean isChecked() {
        return getCompoundButton().isChecked();
    }

    default void setChecked(boolean checked) {
        if (getCompoundButton() != null) {
            getCompoundButton().setChecked(checked);
        }
    }

    default void setCompoundClickable(boolean clickable) {
        if (getCompoundButton() != null) {
            if (clickable) {
                View clickView = getClickView();
                if (clickView != null) {
                    clickView.setBackgroundResource(R.drawable.ripple_water);
                    clickView.setOnClickListener(v -> getCompoundButton().setChecked(!getCompoundButton().isChecked()));
                }
                getCompoundButton().setClickable(true);
                getCompoundButton().setOnCheckedChangeListener(this);
            } else {
                View clickView = getClickView();
                if (clickView != null) {
                    clickView.setOnClickListener(null);
                }
                getCompoundButton().setClickable(false);
                getCompoundButton().setOnCheckedChangeListener(null);
            }
        }
    }

    <Listener> void setOnCheckedChangeListener(OnCheckedChangeListener<Listener> onCheckedChangeListener);

    @Override
    void onCheckedChanged(CompoundButton buttonView, boolean isChecked);

    default void setUseMaterialThemeColors(boolean useMaterialThemeColors) {
        CompoundButton compoundButton = getCompoundButton();
        if (compoundButton != null) {
            if (compoundButton instanceof SwitchMaterial) {
                ((SwitchMaterial) compoundButton).setUseMaterialThemeColors(useMaterialThemeColors);
            } else if (compoundButton instanceof MaterialCheckBox) {
                ((MaterialCheckBox) compoundButton).setUseMaterialThemeColors(useMaterialThemeColors);
            }
        }
    }

    default void setButtonTintList(ColorStateList tint) {
        CompoundButton compoundButton = getCompoundButton();
        if (compoundButton != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                compoundButton.setButtonTintList(tint);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    compoundButton.setButtonTintBlendMode(BlendMode.SRC_ATOP);
                } else {
                    compoundButton.setButtonTintMode(PorterDuff.Mode.SRC_ATOP);
                }
            }
        }
    }

    default void setThumbTintList(ColorStateList tint) {
        CompoundButton compoundButton = getCompoundButton();
        if (compoundButton != null) {
            if (compoundButton instanceof SwitchMaterial) {
                ((SwitchMaterial) compoundButton).setThumbTintList(tint);
                ((SwitchMaterial) compoundButton).setThumbTintMode(PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    default void setTrackTintList(ColorStateList tint) {
        CompoundButton compoundButton = getCompoundButton();
        if (compoundButton != null) {
            if (compoundButton instanceof SwitchMaterial) {
                ((SwitchMaterial) compoundButton).setTrackTintList(tint);
                ((SwitchMaterial) compoundButton).setTrackTintMode(PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    default void loadFromCompoundAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CompoundAction, defStyleAttr, 0);
        try {
            boolean useMaterialThemeColors = array.getBoolean(R.styleable.CompoundAction_useMaterialThemeColors, true);
            setUseMaterialThemeColors(useMaterialThemeColors);
            boolean clickable = array.getBoolean(R.styleable.CompoundAction_item_clickable, true);
            boolean checked = array.getBoolean(R.styleable.CompoundAction_item_checked, false);
            if (array.hasValue(R.styleable.CompoundAction_item_buttonTint)) {
                ColorStateList buttonTint = array.getColorStateList(R.styleable.CompoundAction_item_buttonTint);
                setButtonTintList(buttonTint);
            }
            if (array.hasValue(R.styleable.CompoundAction_item_thumbTint)) {
                ColorStateList thumbTint = array.getColorStateList(R.styleable.CompoundAction_item_thumbTint);
                setThumbTintList(thumbTint);
            }
            if (array.hasValue(R.styleable.CompoundAction_item_trackTint)) {
                ColorStateList trackTint = array.getColorStateList(R.styleable.CompoundAction_item_trackTint);
                setTrackTintList(trackTint);
            }
            setChecked(checked);
            setCompoundClickable(clickable);
        } finally {
            array.recycle();
        }
    }

    interface OnCheckedChangeListener<T> {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        void onCheckedChanged(T compoundItem, CompoundButton buttonView, boolean isChecked);
    }
}
