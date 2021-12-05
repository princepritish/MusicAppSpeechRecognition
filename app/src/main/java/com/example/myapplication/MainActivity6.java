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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    private String AudioSavaPath1 = null;
    private String AudioSavaPath2 = null;
    DatabaseReference reference;
    String lang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        StartRecording = findViewById(R.id.button24);
        StopRecording = findViewById(R.id.button22);
        StartPlaying = findViewById(R.id.button25);
        StopPlaying = findViewById(R.id.button26);
        TextView textView = (TextView) findViewById(R.id.text);



        StartRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (checkPermissions() == true) {
                    AudioSavaPath = getExternalCacheDir().getAbsolutePath();
                    AudioSavaPath += "/audiorecordtest.mp3";
//                    AudioSavaPath = Environment.getExternalStorageDirectory().getAbsolutePath()
//                            +"/tracks"+"recordingAudio.mp3";
                    AudioSavaPath1 = getExternalCacheDir().getAbsolutePath()+"/"+"happy.mp3";
                    AudioSavaPath2 =  getExternalCacheDir().getAbsolutePath()+"/"+"sad.mp3";

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
                textView.setText("Loading");

                Toast.makeText(MainActivity6.this, "Recording stopped", Toast.LENGTH_SHORT).show();
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


// ...

// Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity6.this);
                String url ="http://35.200.155.9:8000/user/get_mood";

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String raw_response) {
                                String response=raw_response.replaceAll("\"","");
                                // Display the first 500 characters of the response string.
                                textView.setText(response);
                                readdata(response);
                                //System.out.println(response);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        // textView.setText();
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

        StartPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer = new MediaPlayer();
                try{

                    Uri myUri = Uri.parse("android.resource://" + v.getContext().getPackageName() + "/" + R.raw.happy);
                    System.out.println(myUri);
                    mediaPlayer.setDataSource(v.getContext(), myUri);
                    mediaPlayer.prepare();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                        @Override
                        public void onPrepared(MediaPlayer playerM){
                            mediaPlayer.start();
                        }
                    });
                }catch(IOException e){
                    e.printStackTrace();
                }

                //                    if(lang.equalsIgnoreCase("happy.mp3"))
//                        mediaPlayer.setDataSource(AudioSavaPath1);
//                    else
//                        mediaPlayer.setDataSource(AudioSavaPath2);
//                mediaPlayer.create(MainActivity6.this, R.raw.happy);
//                //mediaPlayer.prepare();
//                mediaPlayer.start();
                Toast.makeText(MainActivity6.this, "Start playing", Toast.LENGTH_SHORT).show();
            }
        });

        StopPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer != null) {

                    mediaPlayer.stop();
                   mediaPlayer.release();
                    Toast.makeText(MainActivity6.this, "Stopped playing", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void readdata(String res) {

        TextView textView1 = (TextView) findViewById(R.id.text3);
        textView1.setText("Loading");
        reference = FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Emotions");
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        Toast.makeText(MainActivity6.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        lang=String.valueOf(dataSnapshot.child(res).getValue());
                        textView1.setText(lang);

                        //Toast.makeText(MainActivity3.this,lang,Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(MainActivity6.this,"Cannot Read Emotion",Toast.LENGTH_SHORT).show();

                    }


                }else {
                    Toast.makeText(MainActivity6.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });}

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
        User user=new User(MainActivity6.this);
        user.removeuser();
        Intent intent = new Intent(MainActivity6.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}