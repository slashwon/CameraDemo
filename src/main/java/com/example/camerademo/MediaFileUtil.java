package com.example.camerademo;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/16.
 */
public class MediaFileUtil {
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static File currentFile ;
    public static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public static File getOutputMediaFile(int type) {
        File mediaFileDir = new File(Environment.getExternalStorageDirectory(),"MyCameraApp");
        if(!mediaFileDir.exists()){
            if(!mediaFileDir.mkdirs()){
                Log.e("test","创建目录失败");
            }
        }

        //获取时间戳
        String timeStamp = new SimpleDateFormat("yyyyMMdd_hhmm").format(new Date());
        File mediaFile;
        switch (type){
            case MEDIA_TYPE_IMAGE:
                mediaFile = new File(mediaFileDir.getPath(),timeStamp+".jpg");
                break;
            case MEDIA_TYPE_VIDEO:
                mediaFile = new File(mediaFileDir.getPath(),timeStamp+".mp4");
                break;
            default:
                mediaFile = null;
                break;
        }
        currentFile = mediaFile;
        return mediaFile;
    }

    public static File getCurrentFile(){
        return currentFile;
    }
}
