package com.cjf.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.cjf.ui.R;
import com.cjf.ui.base.ContextAction;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

/**
 * <p>Title: EmptyLayout </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/12 21:44
 */
public class HintLayout extends FrameLayout implements ContextAction {

    public static HintLayout newEmptyLayout(Context context) {
        return newHintLayout(context, 0, R.string.hint_layout_no_data);
    }

    public static HintLayout newErrorRequestLayout(Context context) {
        return newHintLayout(context, 0, R.string.hint_layout_error_request);
    }

    public static HintLayout newErrorNetworkLayout(Context context) {
        return newHintLayout(context, 0, R.string.hint_layout_error_network);
    }

    public static HintLayout newEmptyLayout(Context context, @DrawableRes int drawable) {
        return newHintLayout(context, drawable, R.string.hint_layout_no_data);
    }

    public static HintLayout newErrorRequestLayout(Context context, @DrawableRes int drawable) {
        return newHintLayout(context, drawable, R.string.hint_layout_error_request);
    }

    public static HintLayout newErrorNetworkLayout(Context context, @DrawableRes int drawable) {
        return newHintLayout(context, drawable, R.string.hint_layout_error_network);
    }

    public static HintLayout newHintLayout(Context context, int text) {
        return newHintLayout(context, 0, text);
    }

    public static HintLayout newHintLayout(Context context, @DrawableRes int drawable, int text) {
        if (drawable == 0) {
            return newHintLayout(context, null, context.getString(text));
        }
        return newHintLayout(context, ContextCompat.getDrawable(context, drawable), context.getString(text));
    }

    public static HintLayout newHintLayout(Context context, Drawable drawable, String text) {
        HintLayout hintLayout = new HintLayout(context);
        hintLayout.setIcon(drawable);
        hintLayout.setText(text);
        return hintLayout;
    }

    private LinearLayoutCompat mLinearLayoutCompat;
    private AppCompatImageView mAppCompatImageView;
    private AppCompatTextView mAppCompatTextView;

    public HintLayout(@NonNull Context context) {
        this(context, null);
    }

    public HintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLinearLayoutCompat = new LinearLayoutCompat(context);
        mAppCompatImageView = new AppCompatImageView(context);
        mAppCompatTextView = new AppCompatTextView(context);
        mLinearLayoutCompat.setGravity(Gravity.CENTER);
        mLinearLayoutCompat.setOrientation(LinearLayoutCompat.VERTICAL);
        int padding = dp2px(16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mLinearLayoutCompat.setPaddingRelative(padding, padding, padding, padding);
        } else {
            mLinearLayoutCompat.setPadding(padding, padding, padding, padding);
        }

        setCommonPadding(mAppCompatImageView);
        LinearLayoutCompat.LayoutParams imageParams = new LinearLayoutCompat.LayoutParams(dp2px(80),
                dp2px(80));
        imageParams.gravity = Gravity.CENTER_HORIZONTAL;
        mLinearLayoutCompat.addView(mAppCompatImageView, imageParams);

        mAppCompatTextView.setTextSize(sp2px(16));
        mAppCompatTextView.setTextColor(getColor(R.color.design_txt_gray));
        setCommonPadding(mAppCompatTextView);
        LinearLayoutCompat.LayoutParams textParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        textParams.gravity = Gravity.CENTER_HORIZONTAL;
        mLinearLayoutCompat.addView(mAppCompatTextView, textParams);

        LayoutParams linearParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        linearParams.gravity = Gravity.CENTER;
        addView(mLinearLayoutCompat, linearParams);


    }

    public void setIcon(Drawable drawable) {
        mAppCompatImageView.setImageDrawable(drawable);
    }

    public void setIconRes(@DrawableRes int drawable) {
        mAppCompatImageView.setImageResource(drawable);
    }

    public void setText(String text) {
        mAppCompatTextView.setText(text);
    }

    public void setText(@StringRes int text) {
        mAppCompatTextView.setText(text);
    }

    public void setTextColorRes(@ColorRes int id) {
        mAppCompatTextView.setTextColor(getColor(id));
    }

    public void setTextColor(@ColorInt int id) {
        mAppCompatTextView.setTextColor(id);
    }

    public void setTextSize(float size) {
        mAppCompatTextView.setTextSize(size);
    }

}
