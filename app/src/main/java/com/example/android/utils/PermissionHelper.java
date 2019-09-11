package com.example.android.utils;

import android.os.Build;

public class PermissionHelper {

    public static boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        }
        return true;
    }
}
