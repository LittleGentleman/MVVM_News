package com.gmm.www.mvvmnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this,MainActivity.class));
    }

    /**
     * 当生命周期回调onStop()时，说明MainActivity已启动，并且是可见可编辑状态，SplashActivity变成
     * 不可见不可编辑状态，此时可以销毁SplashActivity
     */
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
