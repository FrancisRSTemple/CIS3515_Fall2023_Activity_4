package edu.temple.activity4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        with(findViewById<TextView>(R.id.helloWorldTextView)){
            textSize = intent.getFloatExtra(TEXT_SIZE, 20.0F)
        }
    }
}