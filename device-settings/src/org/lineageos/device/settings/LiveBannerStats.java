/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.device.settings;

import android.os.Build;
import android.os.SystemProperties;

import java.io.BufferedReader;
import java.io.FileReader;

final class LiveBannerStats {

    private static final String[] SKU_PROPS = {
        "ro.product.odm.model",
        "ro.product.odm.name",
        "ro.product.odm.device",
        "ro.boot.hardware.sku",
    };

    private static long readLong(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            return line == null ? -1L : Long.parseLong(line.trim());
        } catch (Exception e) {
            return -1L;
        }
    }

    static String sku() {
        for (String key : SKU_PROPS) {
            String val = SystemProperties.get(key, "").trim();
            if (val.endsWith("EEA")) {
                val = val.substring(0, val.length() - 3);
            }
            if (!val.isEmpty() && !val.toLowerCase().startsWith("aston") && !val.contains(" ")) {
                return val;
            }
        }
        String model = Build.MODEL == null ? "" : Build.MODEL.trim();
        return (model.isEmpty() || model.contains(" ")) ? "--" : model;
    }

    static String soh() {
        String oplusBase = "/sys/class/oplus_chg/battery/";
        String psyBase = "/sys/class/power_supply/battery/";
        String[] nodes = {
            oplusBase + "ui_soh",
            oplusBase + "battery_soh",
            psyBase + "health_index",
        };
        for (String path : nodes) {
            long val = readLong(path);
            if (val > 0 && val <= 100) {
                return val + "%";
            }
        }
        long full = readLong(psyBase + "charge_full");
        long design = readLong(psyBase + "charge_full_design");
        if (full > 0 && design > 0) {
            return Math.min(100L, Math.round(full * 100f / design)) + "%";
        }
        return "--";
    }
}
