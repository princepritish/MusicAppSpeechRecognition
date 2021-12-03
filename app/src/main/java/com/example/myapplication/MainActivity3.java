package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button bt = (Button) findViewById(R.id.button4);
        User user=new User(MainActivity3.this);
        String name= user.getUsername();
        Toast.makeText(MainActivity3.this,"Welcome Back"+name,Toast.LENGTH_SHORT).show();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User(MainActivity3.this);
                String username=user.getUsername();
                FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(username).child("Language").setValue("Hindi");
                readData();
                startActivity(new Intent(MainActivity3.this, MainActivity6.class));
            }
        });
        Button bt1 = (Button) findViewById(R.id.button6);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User(MainActivity3.this);
                String username=user.getUsername();
                FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(username).child("Language").setValue("English");
                readData();
                startActivity(new Intent(MainActivity3.this, MainActivity6.class));
            }
        });
        Button bt2 = (Button) findViewById(R.id.button9);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User(MainActivity3.this);
                String username=user.getUsername();
                FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(username).child("Language").setValue("Tamil");
                readData();
                startActivity(new Intent(MainActivity3.this, MainActivity6.class));
            }
        });
        Button bt3 = (Button) findViewById(R.id.button10);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User(MainActivity3.this);
                String username=user.getUsername();
                FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(username).child("Language").setValue("Telugu");
                readData();
                startActivity(new Intent(MainActivity3.this, MainActivity6.class));
            }
        });


    }
    public void logout(View view)
    {
        new User(MainActivity3.this).removeuser();
        Intent intent=new Intent(MainActivity3.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
//    public void hindi(View view)
//    {
//        User user=new User(MainActivity3.this);
//        String username=user.getUsername();
//        FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(username).child("Language").setValue("Hindi");
//    }
//    public void english(View view)
//    {
//        User user=new User(MainActivity3.this);
//        String username=user.getUsername();
//        FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(username).child("Language").setValue("English");
//    }
//    public void tamil(View view)
//    {
//        User user=new User(MainActivity3.this);
//        String username=user.getUsername();
//        FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(username).child("Language").setValue("Tamil");
//    }
//    public void telugu(View view)
//    {
//        User user=new User(MainActivity3.this);
//        String username=user.getUsername();
//        FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users").child(username).child("Language").setValue("Telugu");
//    }
private void readData() {

    User user=new User(MainActivity3.this);
    String name= user.getUsername();
    reference = FirebaseDatabase.getInstance("https://musicapp-a705a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
    reference.child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DataSnapshot> task) {

            if (task.isSuccessful()){

                if (task.getResult().exists()){

                    Toast.makeText(MainActivity3.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                    DataSnapshot dataSnapshot = task.getResult();
                    String lang=String.valueOf(dataSnapshot.child("Language").getValue());
                    user.setLang(lang);
                    //Toast.makeText(MainActivity3.this,lang,Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(MainActivity3.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();

                }


            }else {
                Toast.makeText(MainActivity3.this,"Failed to read",Toast.LENGTH_SHORT).show();
            }

        }
    });

}
}