package com.gp.ppermissons;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoping on 2017/5/23.
 */

public class PPermissions {

    private PPermissions() {

    }

    private static class SingletonFactory {
        private static PPermissions instance = new PPermissions();
    }

    public static PPermissions getInstance() {
        return SingletonFactory.instance;
    }

    //check没有授权的权限
    @TargetApi(value = Build.VERSION_CODES.M)
    private List<String> findDeniedPermissions(Activity activity, String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }

    //申请权限
    public void needPermission(Activity activity, int requestCode, String[] permissions, OnpermissionResult listener) {
        requestPermissions(activity, requestCode, permissions, listener);
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    private void requestPermissions(Activity activity, int requestCode, String[] permissions, OnpermissionResult listener) {
        setClickListener(listener);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            listener.onSuccess();
            return;
        }
        List<String> deniedPermissions = findDeniedPermissions(activity, permissions);
        if (deniedPermissions.size() > 0) {
            activity.requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
        }else {
            listener.onSuccess();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case Contanst.REQUEST_SDCARD:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    listener.onSuccess();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    listener.onDenied();
                }
                break;
        }
    }

    public interface OnpermissionResult {
        void onSuccess();
        void onDenied();
    }
    private OnpermissionResult listener;

    public void setClickListener(OnpermissionResult listener) {
        this.listener = listener;
    }
}
