package com.example.spproject

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var checkBoxAgree: CheckBox

    private val PREFS_NAME = "MyPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        checkBoxAgree = findViewById(R.id.checkBoxAgree)

        // Load saved data
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedName = prefs.getString("name", "")
        val agreed = prefs.getBoolean("agreed", false)

        editTextName.setText(savedName)
        checkBoxAgree.isChecked = agreed
    }

    override fun onPause() {
        super.onPause()

        // Save data
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString("name", editTextName.text.toString())
            putBoolean("agreed", checkBoxAgree.isChecked)
            apply()
        }
    }
}
