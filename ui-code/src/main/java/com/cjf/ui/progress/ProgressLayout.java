package com.cjf.ui.progress;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;

import com.cjf.ui.R;
import com.cjf.ui.base.ContextAction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

/**
 * <p>Title: ProgressLayout </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/10 21:21
 */
public class ProgressLayout extends CardView implements ContextAction {

    private LinearLayoutCompat mLinearLayoutCompat;
    private ProgressView mProgressView;
    private AppCompatTextView mMessage;

    public ProgressLayout(@NonNull Context context) {
        this(context, null);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, androidx.cardview.R.attr.cardViewStyle);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
//        setBackgroundColor(ContextCompat.getColor(context, R.color.design_grey800));
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.design_grey800));
        setCardElevation(dp2px(2));
        setRadius(dp2px(15));
        mLinearLayoutCompat = new LinearLayoutCompat(context);
        mProgressView = new ProgressView(context);
        mMessage = new AppCompatTextView(context);

        mLinearLayoutCompat.setGravity(Gravity.CENTER);
        mLinearLayoutCompat.setMinimumHeight(dp2px(100));
        mLinearLayoutCompat.setMinimumWidth(dp2px(100));
        mLinearLayoutCompat.setOrientation(LinearLayoutCompat.VERTICAL);
        int padding = dp2px(16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mLinearLayoutCompat.setPaddingRelative(padding, padding, padding, padding);
        } else {
            mLinearLayoutCompat.setPadding(padding, padding, padding, padding);
        }
        mProgressView.setBarColor(Color.WHITE);
        mProgressView.setBarWidth(dp2px(2));
        mProgressView.setFillRadius(false);
        mProgressView.setLinearProgress(true);
        mProgressView.spin();
        int paddingTopBottom = dp2px(8);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mMessage.setPaddingRelative(padding, paddingTopBottom, padding, 0);
        } else {
            mMessage.setPadding(padding, paddingTopBottom, padding, 0);
        }
        mMessage.setLines(1);
        mMessage.setTextColor(Color.WHITE);
        mMessage.setTextSize(sp2px(14));
        mMessage.setText(R.string.common_loading);

        int progressHeightWidth = dp2px(60);
        LinearLayoutCompat.LayoutParams progressParams = new LinearLayoutCompat.LayoutParams(progressHeightWidth,
                progressHeightWidth);
        progressParams.gravity = Gravity.CENTER_HORIZONTAL;
        mLinearLayoutCompat.addView(mProgressView, progressParams);

        LinearLayoutCompat.LayoutParams messageParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        messageParams.gravity = Gravity.CENTER_HORIZONTAL;
        mLinearLayoutCompat.addView(mMessage, messageParams);

        LayoutParams linearParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        linearParams.gravity = Gravity.CENTER;
        addView(mLinearLayoutCompat, linearParams);
    }

    public void setMessage(CharSequence message) {
        mMessage.setText(message);
    }


    public void setMessageVisibility(int visibility) {
        mMessage.setVisibility(visibility);
    }

    public void setProgressColorPrimary() {
        setProgressColor(getColor(R.color.colorPrimary));
    }

    public void setProgressColor(int color) {
        mMessage.setTextColor(color);
        mProgressView.setBarColor(color);
    }

    public void clearBackground() {
        setCardBackgroundColor(null);
        setCardElevation(0);
    }
}
