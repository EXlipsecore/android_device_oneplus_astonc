/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.device.settings;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.Preference;

import com.android.settingslib.widget.GroupSectionDividerMixin;

public class LiveBannerPreference extends Preference implements GroupSectionDividerMixin {

    public LiveBannerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutResource(R.layout.preference_live_banner);
        setSelectable(false);
        setPersistent(false);
    }
}
