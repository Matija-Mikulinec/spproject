package com.example.spproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText
    private lateinit var editTextDob: EditText
    private lateinit var checkBoxAccept: CheckBox
    private lateinit var editTextComment: EditText

    private val PREFS_NAME = "SurveyPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Force light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.activity_main)

        // Find views by ID
        editTextName = findViewById(R.id.editTextName)
        editTextSurname = findViewById(R.id.editTextSurname)
        editTextDob = findViewById(R.id.editTextDob)
        checkBoxAccept = findViewById(R.id.checkBoxAccept)
        editTextComment = findViewById(R.id.editTextComment)

        // Load saved values from SharedPreferences
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editTextName.setText(prefs.getString("name", ""))
        editTextSurname.setText(prefs.getString("surname", ""))
        editTextDob.setText(prefs.getString("dob", ""))
        checkBoxAccept.isChecked = prefs.getBoolean("accepted", false)
        editTextComment.setText(prefs.getString("comment", ""))
        val buttonSend: Button = findViewById(R.id.buttonSend)

        buttonSend.setOnClickListener {
            val name = editTextName.text.toString()
            val surname = editTextSurname.text.toString()
            val dob = editTextDob.text.toString()
            val accepted = checkBoxAccept.isChecked
            val comment = editTextComment.text.toString()

            // Simple action for now: show confirmation
            Toast.makeText(
                this,
                "Submitted: $name $surname ($dob)\nAccepted: $accepted\nComment: $comment",
                Toast.LENGTH_LONG
            ).show()
        }
    }



    override fun onPause() {
        super.onPause()

        // Save current values to SharedPreferences
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString("name", editTextName.text.toString())
            putString("surname", editTextSurname.text.toString())
            putString("dob", editTextDob.text.toString())
            putBoolean("accepted", checkBoxAccept.isChecked)
            putString("comment", editTextComment.text.toString())
            apply()
        }
    }
}
