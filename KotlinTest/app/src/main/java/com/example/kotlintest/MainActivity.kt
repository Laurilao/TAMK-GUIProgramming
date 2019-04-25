package com.example.kotlintest

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val label = colorLabel

        selectGreen.setOnClickListener {
            label.setBackgroundColor(Color.GREEN)
            label.text = "Vihreä"
        }

        selectRed.setOnClickListener {
            label.setBackgroundColor(Color.RED)
            label.text = "Punainen"
        }


    }
}
