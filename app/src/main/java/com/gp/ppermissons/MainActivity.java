package com.gp.ppermissons;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PPermissions.getInstance().needPermission(this, Contanst.REQUEST_SDCARD, new String[]{Manifest.permission.INSTALL_SHORTCUT}, new PPermissions.OnpermissionResult() {
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
