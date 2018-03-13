package com.rxandroid.activities;
import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.rxandroid.R;
import com.rxandroid.utils.StaticUtils;
import java.io.ByteArrayOutputStream;

/**
 * Created by Devrepublic-14 on 3/12/2018.
 */

public class FileUplaodActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1001;
    private static final int TAKE_PICTURE = 1002;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 101;
    private ImageView imgPreview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);
        try {
            initComp();
           } catch (Exception e) {
         }
    }
    private void initComp() {
        imgPreview = findViewById(R.id.imgPreview);
        imgPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPicChooserDailog();
            }
        });
    }
    public void getPicChooserDailog() {
        final Dialog dialog = new Dialog(FileUplaodActivity.this);
        dialog.setContentView(R.layout.dailog_chooser);
        Button btnCamera = dialog.findViewById(R.id.btnCamera);
        Button btnGallery = dialog.findViewById(R.id.btnGallery);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
              public void onClick(View v) {
                dialog.cancel();
                requestForPermission();
              }
        });
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                imageBrowse();
             }
        });
        dialog.show();
    }
    private void takePicFromCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, TAKE_PICTURE);
    }
    private void requestForPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                return;
            }
          } else {
            takePicFromCamera();
        }
    }
    private void imageBrowse() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                Uri picUri = data.getData();
                String filePath = StaticUtils.getPath(getApplicationContext(), picUri);
                imgPreview.setImageURI(picUri);
                Log.e("Image path", "" + filePath);
                } else if (requestCode == TAKE_PICTURE) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imgPreview.setImageBitmap(photo);
                Uri tempUri = StaticUtils.getImageUriFromCameraBitmap(getApplicationContext(), photo);
                Log.e("Camera image", "" + StaticUtils.getPath(getApplicationContext(), tempUri));
                // CALL THIS METHOD TO GET THE ACTUAL PATH
               }
          }
    }
    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        takePicFromCamera();
    }
}
