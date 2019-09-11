package com.example.android;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPermission();
    }

    private void initPermission() {
        try {
            // android 6.0以上才需要动态申请权限
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                return;
            }
            // 获取manifest里声明的所有权限
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] requestedPermissions = packageInfo.requestedPermissions;

            List<String> toApplyList = new ArrayList<String>();
            for (String permission : requestedPermissions) {
                if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permission)) {
                    toApplyList.add(permission);
                    // 进入到这里代表没有相应权限
                }
            }

            if (!toApplyList.isEmpty()) {
                String[] tmpList = new String[toApplyList.size()];
                ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), PERMISSION_REQUEST_CODE);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }
}
