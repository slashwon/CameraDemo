package com.example.camerademo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 一个调用系统相机的功能类
 * Created by Administrator on 2016/8/16.
 */
public class CameraWrapper {

    private final Context context;
    private Camera camera;

    public CameraWrapper(Context context){
        this.context = context;
    }
    /**
     * 检查当前设备是否有相机
     * @return
     */
    public boolean checkHasCamera(){
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    /**
     * 获取相机对象
     */
    public void getInstance(){
        camera = null;
        try {
            camera = Camera.open();
        }catch (Exception e){
            e.printStackTrace();
        }
        setCameraParams();
    }

    /**
     * 获取相机参数
     */
    public void getCameraParams(){
        Camera.Parameters parameters = camera.getParameters();
    }

    /**
     * 设置相机参数
     */
    public void setCameraParams(){
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
//        parameters.setFocusMode();
        camera.setParameters(parameters);
    }


    /**
     * 获取相机信息
     */
    public void getCameraInfo(){
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK,cameraInfo);
    }
    /**
     * 创建预览view
     */
    public SurfaceView getPreView(){
        SurfaceView view = new CameraPreView(context,camera);
        return view;
    }

    /**
     * 销毁相机对象
     */
    public void releaseCamera(){
        camera.release();
    }

    /**
     * 拍照
     */
    public void capturePhoto(){
        //拍照需要一个回调接口
        Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                //data储存了照片的信息
                //先创建一个新的图片文件
                File mediaFile = MediaFileUtil.getOutputMediaFile(MediaFileUtil.MEDIA_TYPE_IMAGE);
                if(mediaFile == null){
                    Log.e("test","创建文件失败");
                    return ;
                }

                try {
                    FileOutputStream fos = new FileOutputStream(mediaFile);
                    fos.write(data);//将图片信息写入到文件
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(context,"照片已保存!"+mediaFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();

                camera.startPreview();
            }
        };

        camera.takePicture(null,null,pictureCallback);
    }
}
