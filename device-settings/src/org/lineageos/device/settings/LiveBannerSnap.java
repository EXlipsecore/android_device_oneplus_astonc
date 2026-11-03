/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.device.settings;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.StatFs;

import java.util.Locale;

final class LiveBannerSnap {

    static String[] read(Context context) {
        String ram = "--";
        String rom = "--";
        String temp = "";
        String batt = "--";

        try {
            ActivityManager am = context.getSystemService(ActivityManager.class);
            if (am != null) {
                ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
                am.getMemoryInfo(info);
                double gib = 1073741824.0;
                ram = String.format(Locale.US, "%.1f/%dGB",
                        (info.totalMem - info.availMem) / gib,
                        (int) Math.ceil(info.totalMem / gib));
            }
        } catch (Exception ignored) {
        }

        try {
            StatFs statFs = new StatFs("/data");
            double gb = 1000000000.0;
            double totalGb = statFs.getTotalBytes() / gb;
            double usedGb = (statFs.getTotalBytes() - statFs.getAvailableBytes()) / gb;
            int tier = 64;
            while (tier < totalGb && tier < 4096) {
                tier <<= 1;
            }
            rom = String.format(Locale.US, "%.0f/%dGB", usedGb, tier);
        } catch (Exception ignored) {
        }

        Intent batteryIntent = context.registerReceiver(
                null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        if (batteryIntent != null) {
            int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
            int rawTemp = batteryIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
            if (level >= 0 && scale > 0) {
                batt = (level * 100 / scale) + "%";
            }
            if (rawTemp > 0) {
                temp = (rawTemp / 10) + "\u00B0C";
            }
        }

        return new String[]{ram, rom, temp, batt};
    }
}
