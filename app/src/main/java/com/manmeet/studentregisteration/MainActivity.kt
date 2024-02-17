package com.manmeet.studentregisteration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.database


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stId = findViewById<EditText>(R.id.st_id)
        val stName = findViewById<EditText>(R.id.st_name)
        val stAge = findViewById<EditText>(R.id.st_age)
        val stAdd = findViewById<EditText>(R.id.st_add)
        val stEmail = findViewById<EditText>(R.id.st_email)

        val addBtn = findViewById<Button>(R.id.add_btn)

        addBtn.setOnClickListener() {
            val id = stId.text.toString()
            val name = stName.text.toString()
            val age = stAge.text.toString()
            val add = stAdd.text.toString()
            val email = stEmail.text.toString()

            if (id == "" || name == "" || age == "" || add == "" || email == "") {
                Toast.makeText(this, "Please Fill All The Fields!", Toast.LENGTH_LONG).show()
            } else {

                FirebaseApp.initializeApp(this)

                // Storing data to firebase
                val database = Firebase.database
                val myRef = database.getReference("students")

                val student = Student(name, age, add, email)
                myRef.child(id).setValue(student)

                Toast.makeText(this, "Student Recorde is Added Successfully!", Toast.LENGTH_LONG)
                    .show()

            }
        }
    }
}

data class Student(val name: String, val age: String, val address: String, val email: String)
