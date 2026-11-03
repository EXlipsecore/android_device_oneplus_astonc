/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.device.settings;

import android.content.Context;
import android.util.AttributeSet;

public class LiveBannerView extends LiveBannerBox {

    private final String mSku = LiveBannerStats.sku();
    private final String mModel =
            ("OP5CF9L1".equals(mSku) || "PJE110".equals(mSku)) ? "Ace 3" : "Ace 3";
    private String mSoh = "--";

    private final Runnable mTick = new Runnable() {
        @Override
        public void run() {
            String[] snap = LiveBannerSnap.read(getContext());
            if ("--".equals(mSoh)) {
                mSoh = LiveBannerStats.soh();
            }
            mEx[0].setText("--".equals(mSku) ? "" : mSku);
            mVl[0].setText(mModel);
            mVl[1].setText(snap[0]);
            mVl[2].setText(snap[1]);
            mEx[3].setText(snap[2]);
            mVl[3].setText(snap[3]);
            mVl[4].setText(mSoh);
            if (isShown()) {
                postDelayed(this, 3000L);
            }
        }
    };

    public LiveBannerView(Context context) {
        this(context, null, 0);
    }

    public LiveBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LiveBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks(mTick);
        mTick.run();
    }

    @Override
    protected void onDetachedFromWindow() {
        removeCallbacks(mTick);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        removeCallbacks(mTick);
        if (visibility == VISIBLE) {
            mTick.run();
        }
    }
}
