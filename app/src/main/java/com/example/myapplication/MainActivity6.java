package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicMarkableReference;


public class MainActivity6 extends AppCompatActivity {

    private Button StartRecording, StopRecording, StartPlaying, StopPlaying;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String AudioSavaPath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        StartRecording = findViewById(R.id.button24);
        StopRecording = findViewById(R.id.button22);
        StartPlaying = findViewById(R.id.button25);
        StopPlaying = findViewById(R.id.button26);


        StartRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (checkPermissions() == true) {

                    AudioSavaPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            +"/"+"recordingAudio.mp3";

                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    mediaRecorder.setOutputFile(AudioSavaPath);

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                        Toast.makeText(MainActivity6.this, "Recording started", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }else {

                    ActivityCompat.requestPermissions(MainActivity6.this,new String[]{
                            Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },1);
                    ActivityCompat.requestPermissions(MainActivity6.this,new String[]{
                            Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE
                    },1);
                }
            }
        });

        StopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaRecorder.stop();
                mediaRecorder.release();
                Toast.makeText(MainActivity6.this, "Recording stopped", Toast.LENGTH_SHORT).show();
            }
        });

        StartPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavaPath);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(MainActivity6.this, "Start playing", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        StopPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer != null) {

                    mediaPlayer.stop();
                    mediaPlayer.release();
                    Toast.makeText(MainActivity6.this, "Stopped playing", Toast.LENGTH_SHORT).show();
                    FirebaseStorage storage = FirebaseStorage.getInstance("gs://musicapp-a705a.appspot.com");
                    StorageReference storageReference = storage.getReference();
                    //StorageReference audio = storageReference.child(AudioSavaPath);
                    Uri file = Uri.fromFile(new File(AudioSavaPath));
                    StorageReference audio = storageReference.child("tracks/"+file.getLastPathSegment());
                    UploadTask uploadTask = audio.putFile(file);

                    // Register observers to listen for when the download is done or if it fails
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(MainActivity6.this,"Failed",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(MainActivity6.this,"Success",Toast.LENGTH_SHORT).show();
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                            // ...
                        }
                    });

                    TextView textView = (TextView) findViewById(R.id.text);
// ...

// Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(MainActivity6.this);
                    String url ="localhost:8000";

// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    textView.setText("Response is: "+ response.substring(0,500));
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textView.setText("That didn't work!");
                        }
                    });

// Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }
            }
        });


    }

    private boolean checkPermissions() {
        int first = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECORD_AUDIO);
        int second = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int third = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE);

        return first == PackageManager.PERMISSION_GRANTED &&
                second == PackageManager.PERMISSION_GRANTED && third==PackageManager.PERMISSION_GRANTED;
    }
    public void logout(View view)
    {
        new User(MainActivity6.this).removeuser();
        Intent intent = new Intent(MainActivity6.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}