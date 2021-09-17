package com.sbb5250.multipleactivities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class ColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val getIntentDataButton = Button(this).apply {
            "Get Data".also { text = it }
            setOnClickListener {
                //The intent is sitting in the activity manager,
                // waiting for us to get it
                val dataSent = intent
                this@ColorActivity.findViewById<TextView>(R.id.message_text).apply {
                    //You can pull out and cas the data
                    text = dataSent.getStringExtra("message")
                }
                val passableData = Intent().apply {
                    putExtra("returnMessage", "Bye")
                }
                setResult(RESULT_OK, passableData)
                finish()
            }
        }

        findViewById<ConstraintLayout>(R.id.color_layout).apply {
            setBackgroundColor(Color.RED)
            addView(getIntentDataButton)
        }
    }
}
