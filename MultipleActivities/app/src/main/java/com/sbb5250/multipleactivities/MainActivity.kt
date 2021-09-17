package com.sbb5250.multipleactivities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeActivityButton = Button(this).apply {
            "Change Activity".also { text = it }
            setOnClickListener {
                Log.i("MainActivityChange", "Not setup yet.")
                val passableData = Intent(applicationContext, ColorActivity::class.java).apply {
                    putExtra("message", "Payload message")
                }
                startActivity(passableData)
            }
        }

        //look up the main layout by the id we just gave it
        findViewById<ConstraintLayout>(R.id.main_layout).apply {
            setBackgroundColor(Color.GREEN)
            addView(changeActivityButton)
        }
    }
}