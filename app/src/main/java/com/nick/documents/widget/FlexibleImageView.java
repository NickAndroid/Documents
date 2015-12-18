/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nick.documents.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.nick.documents.R;


public class FlexibleImageView extends ImageView {

    public static final int DEFAULT_TEXT_SIZE = 9;
    private static final int SHADOW_ELEVATION = 4;
    private static final long ANIMATE_START_DELAY = 888;
    private static final int DEFAULT_CIRCLE_BG_LIGHT = 0xFFFAFAFA;
    private static final int DEFAULT_CIRCLE_DIAMETER = 56;
    private static final int STROKE_WIDTH_LARGE = 3;
    private Animation.AnimationListener mListener;
    private int mBgColor;
    private int mProgressStokeWidth;
    private int mProgress;
    private int mMax;
    private int mDiameter;
    private int mInnerRadius;
    private Paint mTextPaint;
    private int mTextColor;
    private int mTextSize;
    private boolean mHasText;
    private MaterialProgressDrawable mProgressDrawable;
    private ShapeDrawable mBgCircle;
    private boolean mCircleBackgroundEnabled;
    private int[] mRingColors;

    private Handler mHandler;

    public FlexibleImageView(Context context) {
        super(context);
        init(context, null, 0);

    }

    public FlexibleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);

    }

    public FlexibleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.FlexibleImageView, defStyleAttr, 0);

        final float density = getContext().getResources().getDisplayMetrics().density;

        mBgColor = a.getColor(
                R.styleable.FlexibleImageView_background_color, DEFAULT_CIRCLE_BG_LIGHT);

        mInnerRadius = a.getDimensionPixelOffset(
                R.styleable.FlexibleImageView_inner_radius, -1);

        mProgressStokeWidth = a.getDimensionPixelOffset(
                R.styleable.FlexibleImageView_progress_stoke_width, (int) (STROKE_WIDTH_LARGE * density));
        mTextSize = a.getDimensionPixelOffset(
                R.styleable.FlexibleImageView_progress_text_size, (int) (DEFAULT_TEXT_SIZE * density));
        mTextColor = a.getColor(
                R.styleable.FlexibleImageView_progress_text_color, Color.BLACK);

        mCircleBackgroundEnabled = a.getBoolean(R.styleable.FlexibleImageView_enable_circle_background, true);

        mProgress = a.getInt(R.styleable.FlexibleImageView_progress, 0);
        mMax = a.getInt(R.styleable.FlexibleImageView_max_flip, 100);
        int textVisible = a.getInt(R.styleable.FlexibleImageView_progress_text_visibility, 1);
        if (textVisible != 1) {
            mHasText = true;
        }

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setAntiAlias(true);
        a.recycle();
        mProgressDrawable = new MaterialProgressDrawable(getContext(), this);
        mRingColors = context.getResources().getIntArray(R.array.ring_colors);
        super.setImageDrawable(mProgressDrawable);
        mHandler = new Handler();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final float density = getContext().getResources().getDisplayMetrics().density;
        mDiameter = Math.min(getMeasuredWidth(), getMeasuredHeight());
        if (mDiameter <= 0) {
            mDiameter = (int) density * DEFAULT_CIRCLE_DIAMETER;
        }
        if (getBackground() == null && mCircleBackgroundEnabled) {
            mBgCircle = new ShapeDrawable(new OvalShape());
            ViewCompat.setElevation(this, SHADOW_ELEVATION * density);
            mBgCircle.getPaint().setColor(mBgColor);
            setBackground(mBgCircle);
        }
        mProgressDrawable.setBackgroundColor(mBgColor);
        mProgressDrawable.setColorSchemeColors(mRingColors);
        mProgressDrawable.setSizeParameters(mDiameter, mDiameter,
                mInnerRadius <= 0 ? (mDiameter - mProgressStokeWidth * 2) / 4 : mInnerRadius,
                mProgressStokeWidth, mProgressStokeWidth * 4, mProgressStokeWidth * 2);
        super.setImageDrawable(null);
        super.setImageDrawable(mProgressDrawable);
        mProgressDrawable.setAlpha(255);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressDrawable.start();
            }
        }, ANIMATE_START_DELAY);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (mHasText) {
            String text = String.format("%s%%", mProgress);
            int x = getWidth() / 2 - text.length() * mTextSize / 4;
            int y = getHeight() / 2 + mTextSize / 4;
            canvas.drawText(text, x, y, mTextPaint);
        }
    }

    public void showImg(final int resId, boolean animate) {
        if (animate) {
            ViewAnimateUtils.scaleHide(this, new Runnable() {
                @Override
                public void run() {
                    setImageDrawable(null);
                    setImageResource(resId);
                    ViewAnimateUtils.scaleShow(FlexibleImageView.this);
                }
            });
        } else {
            setImageDrawable(null);
            setImageResource(resId);
        }
    }

    public void showProgress(boolean animate) {
        if (animate) {
            ViewAnimateUtils.scaleHide(this, new Runnable() {
                @Override
                public void run() {
                    setImageResource(0);
                    setImageDrawable(mProgressDrawable);
                    ViewAnimateUtils.scaleShow(FlexibleImageView.this);
                }
            });
        } else {
            setImageResource(0);
            setImageDrawable(mProgressDrawable);
        }
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
    }

    public void setAnimationListener(Animation.AnimationListener listener) {
        mListener = listener;
    }

    @Override
    public void onAnimationStart() {
        super.onAnimationStart();
        if (mListener != null) {
            mListener.onAnimationStart(getAnimation());
        }
    }

    @Override
    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (mListener != null) {
            mListener.onAnimationEnd(getAnimation());
        }
    }


    /**
     * Set the color resources used in the progress animation from color resources.
     * The first color will also be the color of the bar that grows in response
     * to a user swipe gesture.
     *
     * @param colorResIds
     */
    public void setColorSchemeResources(int... colorResIds) {
        final Resources res = getResources();
        int[] colorRes = new int[colorResIds.length];
        for (int i = 0; i < colorResIds.length; i++) {
            colorRes[i] = res.getColor(colorResIds[i]);
        }
        setColorSchemeColors(colorRes);
    }

    /**
     * Set the colors used in the progress animation. The first
     * color will also be the color of the bar that grows in response to a user
     * swipe gesture.
     */
    public void setColorSchemeColors(int... colors) {
        mRingColors = colors;
        if (mProgressDrawable != null) {
            mProgressDrawable.setColorSchemeColors(colors);
        }
    }

    /**
     * Update the background color of the mBgCircle image view.
     */
    public void setBackgroundColor(int colorRes) {
        if (getBackground() instanceof ShapeDrawable) {
            final Resources res = getResources();
            ((ShapeDrawable) getBackground()).getPaint().setColor(res.getColor(colorRes));
        }
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        mMax = max;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        if (getMax() > 0) {
            mProgress = progress;
        }
    }

    public boolean circleBackgroundEnabled() {
        return mCircleBackgroundEnabled;
    }

    public void setCircleBackgroundEnabled(boolean enableCircleBackground) {
        this.mCircleBackgroundEnabled = enableCircleBackground;
    }

    @Override
    public int getVisibility() {
        return super.getVisibility();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (mProgressDrawable != null) {
            if (visibility != VISIBLE) {
                mProgressDrawable.stop();
            }
            mProgressDrawable.setVisible(visibility == VISIBLE, false);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mProgressDrawable != null) {
            mProgressDrawable.stop();
            mProgressDrawable.setVisible(getVisibility() == VISIBLE, false);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mProgressDrawable != null) {
            mProgressDrawable.stop();
            mProgressDrawable.setVisible(false, false);
        }
    }
}
