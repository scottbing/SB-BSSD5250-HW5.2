package com.sbb5250.multipleactivities

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class ColorActivity : AppCompatActivity() {

    companion object{
        const val COLOR_REQUESTED_01:String = "com.sbb5250.multipleactivities.COLOR_REQUESTED_01"
        const val COLOR_REQUESTED_02:String = "com.sbb5250.multipleactivities.COLOR_REQUESTED_02"
        const val COLOR_REQUESTED_03:String = "com.sbb5250.multipleactivities.COLOR_REQUESTED_03"
    }

    private var col1:Int = 0
    private var col2:Int = 0
    private var col3:Int = 0
    //private var height:Int = Resources.getSystem().getDisplayMetrics().heightPixels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val getIntentDataButton = Button(this).apply {
            "Save".also { text = it }
            setOnClickListener {
                val dataSent = intent
                this@ColorActivity.findViewById<TextView>(R.id.message_text).apply {
                    //You can pull out and cas the data
                    text = dataSent.getStringExtra(COLOR_REQUESTED_01)
                    text = dataSent.getStringExtra(COLOR_REQUESTED_02)
                    text = dataSent.getStringExtra(COLOR_REQUESTED_03)
                }
                val passableData = Intent().apply {
                    putExtra(MainActivity.COLOR_RESULT_01, "C1 Exit")
                    putExtra(MainActivity.COLOR_RESULT_02, "C2 Exit")
                    putExtra(MainActivity.COLOR_RESULT_03, "C3 Exit")
                }
                setResult(RESULT_OK, passableData)
                finish()
            }
        }

        val color1 = View(this).apply{

            // from: https://stackoverflow.com/questions/35780980/getting-the-actual-screen-height-android/45158798
            val height: Int = Resources.getSystem().getDisplayMetrics().heightPixels/3

            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                height)

            try {
                col1 = Color.parseColor(intent.getStringExtra(COLOR_REQUESTED_01))
                setBackgroundColor(col1)
            }catch(e:NumberFormatException){
                setBackgroundColor(Color.WHITE)
                col1 = 0
            }

            setOnClickListener {
                col1 += 10
                it.setBackgroundColor(col1)
                Log.i("Color01: HEXVal", "%X".format(col1))
                var hexVal = "%X".format(col1)
                    hexVal = hexVal.subSequence(2,hexVal.length).toString()
                Log.i("Color01: HEXVal", hexVal)
            }
        }

        val color2 = View(this).apply{

            // from: https://stackoverflow.com/questions/35780980/getting-the-actual-screen-height-android/45158798
            val height: Int = Resources.getSystem().getDisplayMetrics().heightPixels/3

            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                height)

            try {
                col2 = Color.parseColor(intent.getStringExtra(COLOR_REQUESTED_02))
                setBackgroundColor(col2)
            }catch(e:NumberFormatException){
                setBackgroundColor(Color.WHITE)
                col2 = 0
            }

            setOnClickListener {
                col2 += 10
                it.setBackgroundColor(col2)
                Log.i("Color02: HEXVal", "%X".format(col2))
                var hexVal = "%X".format(col2)
                hexVal = hexVal.subSequence(2,hexVal.length).toString()
                Log.i("Color02: HEXVal", hexVal)
            }
        }

        val color3 = View(this).apply{

            // from: https://stackoverflow.com/questions/35780980/getting-the-actual-screen-height-android/45158798
            val height: Int = Resources.getSystem().getDisplayMetrics().heightPixels/3

            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                height)

            try {
                col3 = Color.parseColor(intent.getStringExtra(COLOR_REQUESTED_03))
                setBackgroundColor(col3)
            }catch(e:NumberFormatException){
                setBackgroundColor(Color.WHITE)
                col3 = 0
            }

            setOnClickListener {
                col3 += 10
                it.setBackgroundColor(col3)
                Log.i("Color03: HEXVal", "%X".format(col3))
                var hexVal = "%X".format(col3)
                hexVal = hexVal.subSequence(2,hexVal.length).toString()
                Log.i("Color03: HEXVal", hexVal)
            }
        }

        // Create a ConstraintLayout in which to add the ImageView
        var linearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.BLUE)
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )
            weightSum = 1.0f
            // Add the ImageView to the layout.
            addView(color1)
            addView(color2)
            addView(color3)
        }


        findViewById<ConstraintLayout>(R.id.color_layout).apply {
            addView(linearLayout)
            addView(getIntentDataButton)
        }
    }
}
