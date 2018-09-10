package me.lyz.threaddemo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * @author LYZ 2018.09.07
 */
public class BitmapCompressUtils {

    public static void compressByQuality(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        byte[] byteArray = outputStream.toByteArray();
        Bitmap newBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }


}
