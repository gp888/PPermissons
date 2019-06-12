package com.gp.ppermissons;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by guoping on 2017/5/24.
 */

public class MyFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PPermissions.getInstance().needPermission(getActivity(),
                Contanst.REQUEST_SDCARD, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                new PPermissions.OnpermissionResult() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onDenied() {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PPermissions.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
