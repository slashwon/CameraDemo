package com.example.camerademo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_camera).setOnClickListener(this);
        findViewById(R.id.btn_custom_camera).setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    //处理获取到的activity数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE://捕捉图像
                if(resultCode == RESULT_OK){
                    //data.getData()为空：当intent指定uri路径时，data的返回值为空
                    File imgFile = MediaFileUtil.getCurrentFile();
                    Log.e("test","path = "+imgFile.getAbsolutePath());
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    opts.inSampleSize = 2;
                    Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), opts);
                    imageView.setImageBitmap(bitmap);
                } else if(resultCode == RESULT_CANCELED){
                    //取消了
                } else {

                }

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri fileUri = MediaFileUtil.getOutputMediaFileUri(MediaFileUtil.MEDIA_TYPE_IMAGE);//创建一个保存图片的文件
                intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);//给文件命名

                startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.btn_custom_camera:
                startActivity(new Intent(MainActivity.this,CustomCameraActivity.class));
                break;
        }
    }
}
