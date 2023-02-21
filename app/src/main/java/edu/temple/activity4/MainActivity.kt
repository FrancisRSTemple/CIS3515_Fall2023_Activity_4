package edu.temple.activity4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class MainActivity : AppCompatActivity() {

    lateinit var textSizeSelector: RecyclerView
    lateinit var textSizeDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //change activity name
        //should do this so the name of the "app" doesn't doesn't change
        //only necessary for main activity (main activity with specific intent filter in the manifest
        supportActionBar?.title = "Dashboard"

        findViewById<Button>(R.id.launchButton).setOnClickListener{
            startActivity(Intent(this, SecondActivity::class.java))
        }

        textSizeSelector = findViewById(R.id.textSizeSelectorRecyclerView)
        textSizeDisplay = findViewById(R.id.textSizeDisplayTextView)

        // Trying to create array of integers that are multiples of 5
        // Verify correctness by examining array values.
        val textSize = Array(20){(it + 1) * 5}

        val callback = {textSize: Float -> textSizeDisplay.textSize = textSize}

        Log.d("Array values", textSize.contentToString())

        with(findViewById(R.id.textSizeSelectorRecyclerView) as RecyclerView){
            adapter = TextSizeAdapter(textSize){textSizeDisplay.textSize = it}

            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        textSizeSelector.layoutManager = LinearLayoutManager(this)
    }
}


/* Convert to RecyclerView.Adapter */
class TextSizeAdapter (_textSizes: Array<Int>, _callBack:(Float) -> Unit) : RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>(){

    val textSizes = _textSizes
    val callback = _callBack

    inner class TextSizeViewHolder(view: TextView) : RecyclerView.ViewHolder(view){
        val textView = view

        init{
            textView.setOnClickListener{callback(textSizes[adapterPosition].toFloat())}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
        return TextSizeViewHolder(TextView(parent.context).apply { setPadding(5,20,0,20) })
    }

    override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
        holder.textView.apply {
            text = textSizes[position].toString()
            textSize = textSizes[position].toFloat()
        }
    }

    override fun getItemCount() = textSizes.size
}