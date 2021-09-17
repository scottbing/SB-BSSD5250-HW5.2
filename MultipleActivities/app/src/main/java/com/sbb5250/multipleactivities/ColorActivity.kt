package com.sbb5250.multipleactivities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ColorActivity : AppCompatActivity() {

    companion object{
        const val COLOR_REQUESTED:String = "com.sbb5250.multipleactivities.COLOR_REQUESTED"
    }

    private var col1:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val getIntentDataButton = Button(this).apply {
            "Get Data".also { text = it }
            setOnClickListener {
                val dataSent = intent
                this@ColorActivity.findViewById<TextView>(R.id.message_text).apply {
                    //You can pull out and cas the data
                    text = dataSent.getStringExtra(COLOR_REQUESTED)
                }
                val passableData = Intent().apply {
                    putExtra(MainActivity.COLOR_RESULT, "Exit")
                }
                setResult(RESULT_OK, passableData)
                finish()
            }
        }

        val color1 = View(this).apply{
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                400)
            try {
                col1 = Color.parseColor(intent.getStringExtra(COLOR_REQUESTED))
                setBackgroundColor(col1)
            }catch(e:NumberFormatException){
                setBackgroundColor(Color.WHITE)
                col1 = 0
            }

            setOnClickListener {
                col1 += 10
                it.setBackgroundColor(col1)
                Log.i("HEXVal", "%X".format(col1))
                var hexVal = "%X".format(col1)
                    hexVal = hexVal.subSequence(2,hexVal.length).toString()
                Log.i("HEXVal", hexVal)
            }
        }

        findViewById<ConstraintLayout>(R.id.color_layout).apply {
            addView(color1)
            addView(getIntentDataButton)
        }
    }
}
