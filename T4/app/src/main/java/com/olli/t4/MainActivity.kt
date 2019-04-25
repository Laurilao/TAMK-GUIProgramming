package com.olli.t4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calcResBtn.setOnClickListener {
            val intent = Intent(this, CalcRes::class.java)
            startActivity(intent)
        }
        calcCurrBtn.setOnClickListener {
            val intent = Intent(this, CalcCurr::class.java)
            startActivity(intent)
        }
        calcVoltBtn.setOnClickListener {
            val intent = Intent(this, CalcVolt::class.java)
            startActivity(intent)
        }
        quitBtn.setOnClickListener {
            finish()
            exitProcess(0)
        }



    }
}
