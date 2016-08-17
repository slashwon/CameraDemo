package com.example.camerademo;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * 自定义相机
 */
public class CustomCameraActivity extends AppCompatActivity implements View.OnClickListener {

    private CameraUtil cameraUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_custom_camera);

        cameraUtil = new CameraUtil(this);
        cameraUtil.getInstance();

        //添加预览画面
        CameraPreView cameraPreView = (CameraPreView) cameraUtil.getPreView();
        FrameLayout framePreview = (FrameLayout) findViewById(R.id.framePreview);
        framePreview.addView(cameraPreView);

        findViewById(R.id.btn_capture).setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        cameraUtil.releaseCamera();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_capture){
            cameraUtil.capturePhoto(); //拍摄照片
        }
    }
}
