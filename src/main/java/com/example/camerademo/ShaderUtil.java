package com.example.camerademo;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by Administrator on 2016/8/16.
 */
public class ShaderUtil {

    private static int compileShader(int type , String source){
        int shader = GLES20.glCreateShader(type);
        if(shader == 0){
            Log.e("test","failed to create shader");
            return 0;
        }

        GLES20.glShaderSource(shader,source);
        GLES20.glCompileShader(shader);

        int[] shaderStatus = new int[1];
        GLES20.glGetShaderiv(shader,GLES20.GL_COMPILE_STATUS,shaderStatus,0);
        if(shaderStatus[0] == 0){
            Log.e("test","failed to compile shader");
            GLES20.glDeleteShader(shader);
            return 0;
        }
        return shader;
    }

    public static int createProgram(String vertexSource, String fragmentSource){
        int program = GLES20.glCreateProgram();
        if(program == 0){
            Log.e("test","failed to create program");
            return 0;
        }

        int vertexShader = compileShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        int fragShader = compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);

        GLES20.glAttachShader(program,vertexShader);
        GLES20.glAttachShader(program,fragShader);

        GLES20.glLinkProgram(program);

        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(program,GLES20.GL_LINK_STATUS,linkStatus,0);
        if(linkStatus[0] == 0){
            LogUtil.e("failed to link program" , ShaderUtil.class);
            GLES20.glDeleteProgram(program);
            return 0;
        }
        return program;
    }
}
