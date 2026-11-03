/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.device.settings;

import android.content.Context;
import android.util.AttributeSet;

class LiveBannerBox extends LiveBannerBase {

    LiveBannerBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = Math.round(width * 662f / 1080f);
        int leftOffset = Math.round(width * 0.345f);
        int topOffset = Math.round(height * 0.20f);
        int margin = Math.round(12f * getResources().getDisplayMetrics().density);

        LayoutParams plateParams = (LayoutParams) mPlate.getLayoutParams();
        LayoutParams frostParams = (LayoutParams) mFrost.getLayoutParams();

        plateParams.width = Math.max(1, width - leftOffset - margin);
        plateParams.height = Math.max(1, height - topOffset - margin);
        plateParams.leftMargin = leftOffset;
        plateParams.topMargin = topOffset;

        frostParams.width = Math.max(1, width);
        frostParams.height = Math.max(1, height);
        mFrost.setTranslationX(-leftOffset);
        mFrost.setTranslationY(-topOffset);

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }
}
