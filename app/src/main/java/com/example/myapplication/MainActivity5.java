package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class MainActivity5 extends AppCompatActivity {
    DatabaseReference reference;
    String lang;
    MediaPlayer mediaPlayer;
    String response;
    String set;
    String Playlist;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    private int seekForwardTime = 5 * 1000; // default 5 second
    private int seekBackwardTime = 5 * 1000; // default 5 second

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        String res=extras.getString("mood");
        response=res.replaceAll("\"","");
        readdata(response);
        setContentView(R.layout.activity_main5);
        Button bt = (Button) findViewById(R.id.button17);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView14);
        textView8 = (TextView) findViewById(R.id.textView13);
        textView9 = (TextView) findViewById(R.id.textView5);
        textView10 = (TextView) findViewById(R.id.textView15);
        //Button bt1=(Button) findViewById(R.id.button16);
        Button StartPlaying=findViewById(R.id.button16);
        Button StartPlaying1=findViewById(R.id.button15);
        Button StartPlaying2=findViewById(R.id.button18);
        Button StartPlaying3=findViewById(R.id.button8);
        Button StopPlaying=findViewById(R.id.button23);
        Button forward=findViewById(R.id.button26);
        Button backward=findViewById(R.id.button27);
        textView6.setText("Loading");
        textView9.setText("Loading");
        textView8.setText("Loading");
        textView7.setText("Loading");
        textView10.setText("Loading");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity5.this, MainActivity6.class));
            }
        });
//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity5.this, MainActivity3.class));
//            }
//        });
        StartPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPlaying.setText("Playing");
                forward.setVisibility(View.VISIBLE);
                backward.setVisibility(View.VISIBLE);
                StopPlaying.setVisibility(View.VISIBLE);
                mediaPlayer = new MediaPlayer();
                try{
                    Uri myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.a);
                    if(Playlist.equalsIgnoreCase("2")){
                        myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.e);
                    }
                    else if (Playlist.equalsIgnoreCase("3"))
                    {
                        myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.i);
                    }

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

                Toast.makeText(MainActivity5.this, "Start playing", Toast.LENGTH_SHORT).show();
            }
        });
        StartPlaying1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPlaying1.setText("Playing");
                forward.setVisibility(View.VISIBLE);
                backward.setVisibility(View.VISIBLE);
                StopPlaying.setVisibility(View.VISIBLE);
                mediaPlayer = new MediaPlayer();
                try{
                    Uri myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.b);
                    if(Playlist.equalsIgnoreCase("2")){
                        myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.f);
                    }
                    else if (Playlist.equalsIgnoreCase("3"))
                    {
                        myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.j);
                    }
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

                Toast.makeText(MainActivity5.this, "Start playing", Toast.LENGTH_SHORT).show();
            }
        });
        StartPlaying2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPlaying2.setText("Playing");
                forward.setVisibility(View.VISIBLE);
                backward.setVisibility(View.VISIBLE);
                StopPlaying.setVisibility(View.VISIBLE);
                mediaPlayer = new MediaPlayer();
                try{
                    Uri myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.c);
                    if(Playlist.equalsIgnoreCase("2")){
                        myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.g);
                    }
                    else if (Playlist.equalsIgnoreCase("3"))
                    {
                        myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.k);
                    }mediaPlayer.setDataSource(v.getContext(), myUri);
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

                Toast.makeText(MainActivity5.this, "Start playing", Toast.LENGTH_SHORT).show();
            }
        });
        StartPlaying3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPlaying3.setText("Playing");
                forward.setVisibility(View.VISIBLE);
                backward.setVisibility(View.VISIBLE);
                StopPlaying.setVisibility(View.VISIBLE);
                mediaPlayer = new MediaPlayer();
                try{
                    Uri myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.d);
                    if(Playlist.equalsIgnoreCase("2")){
                        myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.h);
                    }
                    else if (Playlist.equalsIgnoreCase("3"))
                    {
                        myUri=Uri.parse("android.resource://" + v.getContext().getPackageName() +"/"+ R.raw.l);
                    }
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

                Toast.makeText(MainActivity5.this, "Start playing", Toast.LENGTH_SHORT).show();
            }
        });
        StopPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPlaying.setText("Play");
                StartPlaying1.setText("Play");
                StartPlaying2.setText("Play");
                StartPlaying3.setText("Play");
                forward.setVisibility(View.INVISIBLE);
                backward.setVisibility(View.INVISIBLE);
                StopPlaying.setVisibility(View.INVISIBLE);
                if (mediaPlayer != null) {

                    mediaPlayer.stop();
                   mediaPlayer.release();
                    Toast.makeText(MainActivity5.this, "Stopped playing", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void forwardSong(View view) {
        if (mediaPlayer != null) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            if (currentPosition + seekForwardTime <= mediaPlayer.getDuration()) {
                mediaPlayer.seekTo(currentPosition + seekForwardTime);
            } else {
                mediaPlayer.seekTo(mediaPlayer.getDuration());
            }
        }
    }
    public void rewindSong(View view) {
        if (mediaPlayer != null) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            if (currentPosition - seekBackwardTime >= 0) {
                mediaPlayer.seekTo(currentPosition - seekBackwardTime);
            } else {
                mediaPlayer.seekTo(0);
            }
        }
    }
    private void readdata(String res) {
        reference = FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Emotions_db").child(res);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        Toast.makeText(MainActivity5.this,"Successfully Read Playlist From Firebase",Toast.LENGTH_LONG).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        Playlist=String.valueOf(dataSnapshot.child("playlist_id").getValue());
                        //lang=String.valueOf(dataSnapshot.child(res).getValue());
                        String r=res;
//                        r=r.replace("_"," ");
                        set=r+" Playlist";
                        System.out.println(set);
                        textView6.setText(set);
                        textView9.setText(String.valueOf(dataSnapshot.child("songs").child("0").getValue()));
                        textView8.setText(String.valueOf(dataSnapshot.child("songs").child("1").getValue()));
                        textView7.setText(String.valueOf(dataSnapshot.child("songs").child("2").getValue()));
                        textView10.setText(String.valueOf(dataSnapshot.child("songs").child("3").getValue()));


                        //Toast.makeText(MainActivity3.this,lang,Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(MainActivity5.this,"Cannot Read Emotion",Toast.LENGTH_SHORT).show();

                    }


                }else {
                    Toast.makeText(MainActivity5.this,"Failed to read Emotion",Toast.LENGTH_SHORT).show();
                }

            }
        });}
    public void logout(View view)
    {
        new User(MainActivity5.this).removeuser();
        Intent intent=new Intent(MainActivity5.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}