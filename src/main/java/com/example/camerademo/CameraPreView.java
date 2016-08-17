package com.example.camerademo;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class CameraPreView extends SurfaceView implements SurfaceHolder.Callback {
    private Camera mCamera;
    private SurfaceHolder mHolder;

    public CameraPreView(Context context) {
        super(context);
    }

    public CameraPreView(Context context , Camera camera){
        super(context);

        this.mCamera = camera;
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//表明该surfaceView的数据是从外部获取的
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //开启相机预览
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mHolder.getSurface() == null){
            return ;
        }
        //surface发生变化时，先停用相机预览！
        try {
            mCamera.stopPreview();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //处理变化
        Log.e("test","width = "+width+";height ="+height);
        //获取所有支持的尺寸
//        int i = 0;
        Camera.Parameters parameters = mCamera.getParameters();
//        List<Camera.Size> supportedSizes = parameters.getSupportedPictureSizes();
//        for (Camera.Size size :
//                supportedSizes) {
//            Log.e("test", "width = "+size.width+"; height = "+size.height+"; i = "+(i++));
//        }

        //设置相机预览尺寸
        parameters.setPreviewSize(width,height);
        mCamera.setParameters(parameters);
        //重新开启预览
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
