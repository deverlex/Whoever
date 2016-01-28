package vn.whoever.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by spider man on 1/13/2016.
 */
public class ConvertImage {
    /**
     * TODO: convert Image to String-base64 and String-base64 to Image
     *
     */
    private static ConvertImage convertImage = new ConvertImage();

    private ConvertImage() {}

    public static ConvertImage getInstance() {
        return  convertImage;
    }

    public synchronized Bitmap stringToBitmap(String str) {
        try{
            byte [] encodeByte=Base64.decode(str,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    public synchronized String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b = baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}
