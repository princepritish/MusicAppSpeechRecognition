package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user=new User(MainActivity.this);
        if(user.getUsername()=="")
        {
            setContentView(R.layout.activity_main);
        }
        else
        {
            openNewActivity1();
        }
        Button bt= (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mEdit   = (EditText)findViewById(R.id.editTextTextPassword);
                EditText mEdit1   = (EditText)findViewById(R.id.editTextTextPersonName2);
                if( mEdit.getText().toString().isEmpty()||mEdit1.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Cannot be empty", Toast.LENGTH_SHORT).show();


                }
                else {
                HashMap<String, Object> map = new HashMap<>();

                map.put("Song1", "A.mp3");
                map.put("Song2", "A.mp3");
                map.put("Song3", "A.mp3");
                HashMap<String, Object> map1 = new HashMap<>();

                map1.put("Song1", "A.mp3");
                map1.put("Song2", "A.mp3");
                map1.put("Song3", "A.mp3");
                  FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(mEdit1.getText().toString());
                FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(mEdit1.getText().toString()).child("Username").setValue(mEdit1.getText().toString());
                  FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(mEdit1.getText().toString()).child("Contact No").setValue(mEdit.getText().toString());
                   FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(mEdit1.getText().toString()).child("Playlist").child("Default Playlist").updateChildren(map);
                FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(mEdit1.getText().toString()).child("Queue").updateChildren(map);

                //FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("users").child("User1").setValue("01");

                    Toast.makeText(getApplicationContext(), "Welcome Mr." + mEdit1.getText().toString() + mEdit.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("name", mEdit1.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }



    public void openNewActivity1(){
        Intent intent = new Intent(this, MainActivity6.class);
        startActivity(intent);
    }
}