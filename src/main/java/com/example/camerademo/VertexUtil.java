package com.example.camerademo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Administrator on 2016/8/16.
 */
public class VertexUtil {
    public static FloatBuffer getVertexBuffer(float[] vertices){
        FloatBuffer floatBuffer = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertices);
        floatBuffer.position(0);
        return floatBuffer;
    }
}
