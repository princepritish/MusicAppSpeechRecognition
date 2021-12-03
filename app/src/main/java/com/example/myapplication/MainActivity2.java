package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    public static final String TAG = "YOUR-TAG-NAME";
    DatabaseReference reference;
    //String name= getIntent().getStringExtra("name");
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText mEdit2   = (EditText)findViewById(R.id.editTextTextPersonName);
        String sr="7777";
        Button bt = (Button) findViewById(R.id.button2);
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            name= extras.getString("name");
            //The key argument here must match that used in the other activity
        }
        System.out.println(name);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });
        Button bt1 = (Button) findViewById(R.id.button3);
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mEdit2.getText().toString().equalsIgnoreCase(sr)) {
                        readData();
                        startActivity(new Intent(MainActivity2.this, MainActivity3.class));
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Incorrect"+mEdit2.getText().toString(),Toast.LENGTH_SHORT).show();

                }

            });


        }
    private void readData() {


        reference = FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
        reference.child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        //Toast.makeText(MainActivity2.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String contact = String.valueOf(dataSnapshot.child("Username").getValue());
                        String lang=String.valueOf(dataSnapshot.child("Username").child("Language").getValue());
                        Toast.makeText(MainActivity2.this,contact,Toast.LENGTH_SHORT).show();
                        User user=new User(MainActivity2.this);
                        user.setUsername(contact);

                    }else {

                        Toast.makeText(MainActivity2.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();

                    }


                }else {
                    Toast.makeText(MainActivity2.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

        }

