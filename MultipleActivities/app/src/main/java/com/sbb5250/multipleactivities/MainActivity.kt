package com.sbb5250.multipleactivities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    companion object{
        const val COLOR_RESULT_01:String = "com.sbb5250.multipleactivities.COLOR_RESULT_01"
        const val COLOR_RESULT_02:String = "com.sbb5250.multipleactivities.COLOR_RESULT_02"
        const val COLOR_RESULT_03:String = "com.sbb5250.multipleactivities.COLOR_RESULT_03"
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                // Handle the Intent
                Log.i("MACTResult1", intent?.getStringExtra(COLOR_RESULT_01).toString())
                Log.i("MACTResult2", intent?.getStringExtra(COLOR_RESULT_02).toString())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val color1 = EditText(this).apply {
            hint = "Color1: Enter Hex Color without #"
        }

        val color2 = EditText(this).apply {
            hint = "Color2: Enter Hex Color without #"
        }

        val submitButton = Button(this).apply {
            "Submit".also { text = it }
            setOnClickListener {
                val passableData = Intent(applicationContext, ColorActivity::class.java).apply {
                    putExtra(ColorActivity.COLOR_REQUESTED_01, "#"+color1.text.toString())
                    putExtra(ColorActivity.COLOR_REQUESTED_02, "#"+color1.text.toString())
                }
                startForResult.launch(passableData)
            }
        }

        val linearLayout = LinearLayoutCompat(this).apply {
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT)
            orientation = LinearLayoutCompat.VERTICAL
            addView(color1)
            addView(color2)
            addView(submitButton)
        }

        //look up the main layout by the id we just gave it
        findViewById<ConstraintLayout>(R.id.main_layout).apply {
            setBackgroundColor(Color.GREEN)
            addView(linearLayout)
        }
    }
}