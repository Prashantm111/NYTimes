package com.example.prashantmtask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.taskprashantm.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickButton = findViewById(R.id.click_button) as Button
        clickButton.setOnClickListener {
            val intent = Intent(this, NewsListActivity::class.java)
            startActivity(intent)

        }
    }
}