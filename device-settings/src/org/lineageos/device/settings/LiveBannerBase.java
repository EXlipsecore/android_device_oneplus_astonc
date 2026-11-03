/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.device.settings;

import android.content.Context;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class LiveBannerBase extends FrameLayout {

    FrameLayout mPlate;
    View mFrost;
    final TextView[] mEx = new TextView[5];
    final TextView[] mVl = new TextView[5];

    LiveBannerBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        float radius = 16f * getResources().getDisplayMetrics().density;
        mPlate = findViewById(R.id.lb_plate);
        mFrost = findViewById(R.id.lb_frost);
        mFrost.setRenderEffect(RenderEffect.createBlurEffect(
                radius, radius, Shader.TileMode.CLAMP));

        LinearLayout column = findViewById(R.id.lb_col);
        int[] icons = {
            R.drawable.ic_lb_dev,
            R.drawable.ic_lb_ram,
            R.drawable.ic_lb_rom,
            R.drawable.ic_lb_bat,
            R.drawable.ic_lb_soh,
        };
        String[] labels = {"Device", "RAM", "Storage", "Battery", "Health"};

        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < 5; i++) {
            View row = inflater.inflate(R.layout.lb_row, column, false);
            ((ImageView) row.findViewById(R.id.lb_ic)).setImageResource(icons[i]);
            ((TextView) row.findViewById(R.id.lb_lb)).setText(labels[i]);
            mEx[i] = row.findViewById(R.id.lb_ex);
            mVl[i] = row.findViewById(R.id.lb_vl);
            column.addView(row);
        }
    }
}
