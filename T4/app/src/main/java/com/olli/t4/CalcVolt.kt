package com.olli.t4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calc_volt.*
import java.lang.NumberFormatException
import kotlin.math.absoluteValue

class CalcVolt : AppCompatActivity() {

    var volt: Double = 0.0
    var res: Double = 0.0
    var curr: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc_volt)

        calcBtn.setOnClickListener {
            try {
                res = resAmountInput.text.toString().toDouble()
                curr = currAmountInput.text.toString().toDouble()

                calcResult()

            } catch (e: NumberFormatException) {

            }
        }

    }

    fun calcResult() {
        volt = res.times(curr)
        resultInput.setText(volt.toString())
    }
}
