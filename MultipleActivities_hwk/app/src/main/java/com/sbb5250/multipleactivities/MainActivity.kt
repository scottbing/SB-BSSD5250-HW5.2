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

    private var color1: EditText? = null
    private var color2: EditText? = null
    private var color3: EditText? = null

    companion object{
        const val COLOR_RESULT_01:String = "com.sbb5250.multipleactivities.COLOR_RESULT_01"
        const val COLOR_RESULT_02:String = "com.sbb5250.multipleactivities.COLOR_RESULT_02"
        const val COLOR_RESULT_03:String = "com.sbb5250.multipleactivities.COLOR_RESULT_03"
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                color1?.setText(intent?.getStringExtra(COLOR_RESULT_01).toString())
                color2?.setText(intent?.getStringExtra(COLOR_RESULT_02).toString())
                color3?.setText(intent?.getStringExtra(COLOR_RESULT_03).toString())
                // Handle the Intent
                Log.i("MACTResult1", intent?.getStringExtra(COLOR_RESULT_01).toString())
                Log.i("MACTResult2", intent?.getStringExtra(COLOR_RESULT_02).toString())
                Log.i("MACTResult3", intent?.getStringExtra(COLOR_RESULT_03).toString())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        color1 = EditText(this).apply {
            hint = "Color1: Enter Hex Color without #"
        }

        color2 = EditText(this).apply {
            hint = "Color2: Enter Hex Color without #"
        }

        color3 = EditText(this).apply {
            hint = "Color3: Enter Hex Color without #"
        }

        val submitButton = Button(this).apply {
            "Submit".also { text = it }
            setOnClickListener {
                val passableData = Intent(applicationContext, ColorActivity::class.java).apply {
                    putExtra(ColorActivity.COLOR_REQUESTED_01, "#"+ color1!!.text.toString())
                    putExtra(ColorActivity.COLOR_REQUESTED_02, "#"+ color2!!.text.toString())
                    putExtra(ColorActivity.COLOR_REQUESTED_03, "#"+ color3!!.text.toString())
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
            addView(color3)
            addView(submitButton)
        }

        //look up the main layout by the id we just gave it
        findViewById<ConstraintLayout>(R.id.main_layout).apply {
            setBackgroundColor(Color.YELLOW)
            addView(linearLayout)
        }
    }
}