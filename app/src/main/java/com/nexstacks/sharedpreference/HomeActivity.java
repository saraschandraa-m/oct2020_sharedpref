package com.nexstacks.sharedpreference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class HomeActivity extends AppCompatActivity {

    private ImageView mIvUserImage;
    private Bitmap imagePath;

    private SharedPreferences prefManger;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        prefManger = getApplicationContext().getSharedPreferences("Nextstacks", MODE_PRIVATE);
        editor = prefManger.edit();

        mIvUserImage = findViewById(R.id.imageView);


        mIvUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 901);
            }
        });

        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult(new Intent(HomeActivity.this, EditActivity.class), 101);
            }
        });

        String retrievedImageValue = prefManger.getString("IMAGEBYTES", "");

        if(!retrievedImageValue.isEmpty()){
            byte[] imageToDisplayBytes = retrievedImageValue.getBytes();

            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageToDisplayBytes, 0, imageToDisplayBytes.length);
            mIvUserImage.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 901){
            if(data.getExtras() != null){
                imagePath = (Bitmap) data.getExtras().get("data");
            }else{
                try {
                    imagePath = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            mIvUserImage.setImageBitmap(imagePath);


            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagePath.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageBytes = stream.toByteArray();
            String imageBytesStringValue = new String(imageBytes);
            editor.putString("IMAGEBYTES", imageBytesStringValue);
            editor.commit();
        }else if(requestCode == 101){

        }
    }
}