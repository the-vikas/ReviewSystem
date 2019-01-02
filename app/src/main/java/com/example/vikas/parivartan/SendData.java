package com.example.vikas.parivartan;



import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SendData extends Activity {

    private static final int GET_FROM_GALLERY = 10;
    private static final int CAMERA_PIC_REQUEST = 10 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
        final Button galleryButton = findViewById(R.id.imageUpload);
        galleryButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){

                        //DEV NOTE (Scare) : Code to access Gallery
                        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                    }
                }
        );

        galleryButton.setOnLongClickListener(
                new Button.OnLongClickListener(){
                    public boolean onLongClick(View v){
                        //DEV NOTE (Scare) : ADD CODE TO OPEN CAMERA
                        return false;
                    }
                }
        );
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();

            //DEV NOTE (Scare) : Code to display Image on ImageView On the Activity Page (NOT DISPLAYING)
            ImageView myImage = findViewById(R.id.imageView);
            myImage.setImageURI(null);
            myImage.setImageURI(selectedImage);

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


}
