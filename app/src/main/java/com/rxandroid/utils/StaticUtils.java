package com.rxandroid.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import java.io.ByteArrayOutputStream;

/**
 * Created by Devrepublic-14 on 3/12/2018.
 */

public class StaticUtils {

    public static String SITE_KEY="6LfAkEwUAAAAAPASwg6DIvThVZyIL8FVdYUlvOC-";
    public static String SECERETRY_KEY="6LfAkEwUAAAAAO051nL3sftpupjYQzGd1yh1wL4u";


    public static Uri getImageUriFromCameraBitmap(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 10, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
     }


    public static String getPath(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }


}
