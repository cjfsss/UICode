package com.cjf.ui.item;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

import com.cjf.ui.base.LeftSubtitleAction;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * <p>Title: ItemTextSubtitleView </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/4/1 18:44
 */
@SuppressWarnings("unchecked")
public abstract class SubtitleGroupItem<T extends ItemGroupView<T>> extends ItemGroupView<T> implements LeftSubtitleAction<T> {

    private LinearLayoutCompat mSubtitleLinearLayout;
    private AppCompatTextView mSubtitleView;
    private AppCompatImageView mLeftIcon;

    public SubtitleGroupItem(Context context) {
        this(context, null);
    }

    public SubtitleGroupItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubtitleGroupItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView(Context context, AttributeSet attrs, int defStyleAttr) {
        mSubtitleLinearLayout = new LinearLayoutCompat(context);
        mSubtitleView = new AppCompatTextView(context);
        mLeftIcon = new AppCompatImageView(context);
        loadFromSubtitleAttributes(context, attrs, defStyleAttr);
    }

    @Override
    protected void addTitleView() {
        removeMainViews();
        removeAllViews();
        Drawable leftIcon = super.getLeftIcon();
        super.setLeftIcon(null);
        setCommonPadding(mLeftIcon);
        mLeftIcon.setImageDrawable(leftIcon);
        mLeftIcon.setClickable(false);
        mLeftIcon.setVisibility(leftIcon == null ? GONE : VISIBLE);
        LinearLayoutCompat.LayoutParams imageParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        imageParams.gravity = Gravity.CENTER_VERTICAL;
        mMainLayout.addView(mLeftIcon, imageParams);

        mSubtitleLinearLayout = new LinearLayoutCompat(getContext());
        mSubtitleLinearLayout.setOrientation(LinearLayoutCompat.VERTICAL);
        setCommonPadding(mSubtitleLinearLayout);

        LinearLayoutCompat.LayoutParams leftParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        leftParams.gravity = Gravity.CENTER_VERTICAL;
        mSubtitleLinearLayout.addView(mTitleView, leftParams);

        LinearLayoutCompat.LayoutParams leftSubtitleParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        leftSubtitleParams.gravity = Gravity.CENTER_VERTICAL;
        mSubtitleLinearLayout.addView(mSubtitleView, leftSubtitleParams);

        LinearLayoutCompat.LayoutParams subtitleParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        subtitleParams.gravity = Gravity.CENTER_VERTICAL;
        mMainLayout.addView(mSubtitleLinearLayout, subtitleParams);

        LinearLayoutCompat.LayoutParams mustFullParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        mustFullParams.gravity = Gravity.CENTER_VERTICAL;
        mMainLayout.addView(mMustFullView, mustFullParams);
    }

    @Override
    public T setLeftIcon(Drawable drawable) {
        mLeftIcon.setImageDrawable(drawable);
        return (T) this;
    }

    @Override
    public Drawable getLeftIcon() {
        return mLeftIcon.getDrawable();
    }

    @Override
    public AppCompatTextView getSubtitleView() {
        return mSubtitleView;
    }
}
