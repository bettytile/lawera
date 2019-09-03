package com.example.dell.lawera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class CustomInsert extends AppCompatActivity implements View.OnClickListener {
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "StoreImageActivity";
    private Button btnOpenGallery, btnSaveImage,record,stop,play;
    private ImageView imgView;
    Custom custom;
    private Uri selectedImageUri;
    EditText nametext;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    Random random ;
        String RandomAudioFileName = "LaweraAudio";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_insert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setElevation(0);

        btnOpenGallery = findViewById(R.id.open_gallery);
        btnSaveImage = findViewById(R.id.custom_save_button);
        imgView = findViewById(R.id.selectedImg);
        nametext = findViewById(R.id.custom_insert_editText);
        record = findViewById(R.id.record);
        stop = findViewById(R.id.stop);
        play = findViewById(R.id.play);

        btnOpenGallery.setOnClickListener(this);
        btnSaveImage.setOnClickListener(this);
        stop.setEnabled(false);
        stop.setBackground(getResources().getDrawable(R.drawable.stop_disabled));
        play.setEnabled(false);
        play.setBackground(getResources().getDrawable(R.drawable.disabled_play));

        custom = new Custom(this);
        btnSaveImage.setBackground(getResources().getDrawable(R.drawable.button_disabled));
        btnSaveImage.setEnabled(false);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPermission()) {
                    MediaRecorderReady();
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {

                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    record.setEnabled(false);
                    record.setBackground(getResources().getDrawable(R.drawable.record_disabled));
                    stop.setEnabled(true);
                    stop.setBackground(getResources().getDrawable(R.drawable.stop));

                    Toast.makeText(CustomInsert.this, "መቅዳት ተጀምሮዋል",
                            Toast.LENGTH_LONG).show();
                } else {
                    requestPermission();
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {

                stop.setEnabled(false);
                stop.setBackground(getResources().getDrawable(R.drawable.stop_disabled));
                record.setEnabled(true);
                record.setBackground(getResources().getDrawable(R.drawable.record));
//                buttonStopPlayingRecording.setEnabled(true);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(getFilesDir()+"/"+nametext.getText().toString()+".m4a");
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(CustomInsert.this, "ቅጂ እየተሰማ ነው",
                        Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                stop.setEnabled(false);
                stop.setBackground(getResources().getDrawable(R.drawable.stop_disabled));
//                buttonPlayLastRecordAudio.setEnabled(true);
                play.setEnabled(true);
                play.setBackground(getResources().getDrawable(R.drawable.play));
                record.setEnabled(true);
                record.setBackground(getResources().getDrawable(R.drawable.record));
//                buttonStopPlayingRecording.setEnabled(false);

                Toast.makeText(CustomInsert.this, "በተሳካ ሁኔታ ተቀድቶዋል",
                        Toast.LENGTH_LONG).show();
            }
        });
        btnSaveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomInsert.this, "በተሳካ ሁኔታ ገብቶዋል",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CustomInsert.this,Learn.class);
                startActivity(intent);
            }
        });

    }
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    imgView.setImageURI(selectedImageUri);
                    btnSaveImage.setBackground(getResources().getDrawable(R.drawable.button_red_negetive));
                    btnSaveImage.setEnabled(true);

                }
            }
        }
    }

    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
//        mediaRecorder.setOutputFile(AudioSavePathInDevice);
        mediaRecorder.setOutputFile(getFilesDir()+"/"+nametext.getText().toString()+".m4a");

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(CustomInsert.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(CustomInsert.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(CustomInsert.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }



    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

//    public void storedAudio(){
//        store.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                MediaPlayer mediaPlayer = new MediaPlayer();
//                try {
//                    mediaPlayer.setDataSource(getFilesDir()+"/audio1.m4a");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    mediaPlayer.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                mediaPlayer.start();
//            }
//        });
//    }



    @Override
    public void onClick(View view) {
        if (view == btnOpenGallery)
            openImageChooser();

        if (view == btnSaveImage) {
            // Saving to Database...
            if (saveImageInDB()) {
                Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
            }
        }
//        if (v == btnLoadImage)
//            loadImageFromDB();

//        if (v == nxt) {
//            Intent i = new Intent(MainActivity.this, Main2Activity.class);
//            startActivity(i);
//
//        }

    }
    boolean saveImageInDB() {

        try {
            custom.open();
            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
            custom.insertData(inputData,nametext.getText().toString());
            custom.close();
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            custom.close();
            return false;
        }

    }
}
