package com.manmeet.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText stId = findViewById(R.id.st_id);
        EditText stName = findViewById(R.id.st_name);
        EditText stAge = findViewById(R.id.st_age);
        EditText stAdd = findViewById(R.id.st_add);
        EditText stEmail = findViewById(R.id.st_email);

        Button btn = findViewById(R.id.add_btn);

        // Get firebase db ref

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values
                String id = stId.getText().toString();
                String name = stName.getText().toString();
                String age = stAge.getText().toString();
                String add = stAdd.getText().toString();
                String email = stEmail.getText().toString();

                if (id.equals("") || name.equals("") || age.equals("") || add.equals("") || email.equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this, "Please Fill All The Fields!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    // Add record to db
                    DatabaseReference myRef = database.getReference("student");

                    Student student = new Student(name, age, add, email);
                    // myRef.child(id).setValue(student);
                    myRef.child("student").child(id).setValue(student);

                    Toast toast = Toast.makeText(MainActivity.this, "Record Added to Database!", Toast.LENGTH_LONG);
                    toast.show();

                    // Set the field to empty
                    stId.setText("");
                    stName.setText("");
                    stAge.setText("");
                    stAdd.setText("");
                    stEmail.setText("");

                    System.out.println(myRef);
                }
            }
        });
    }
}

@IgnoreExtraProperties
class Student {

    public String name;
    public String age;
    public String add;
    public String email;

    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Student(String name, String age, String add, String email) {
        this.name = name;
        this.age = age;
        this.add = add;
        this.email = email;
    }

}