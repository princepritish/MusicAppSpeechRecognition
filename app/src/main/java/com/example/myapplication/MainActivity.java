package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
                  FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(mEdit1.getText().toString());
                FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(mEdit1.getText().toString()).child("Username").setValue(mEdit1.getText().toString());
                  FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(mEdit1.getText().toString()).child("Contact No").setValue(mEdit.getText().toString());
                    Toast.makeText(getApplicationContext(), "Welcome Mr." + mEdit1.getText().toString() + mEdit.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("name", mEdit1.getText().toString());
                    intent.putExtra("mobile",mEdit.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
    public void openNewActivity1(){
        Intent intent = new Intent(this, MainActivity6.class);
        startActivity(intent);
        finish();
    }
}