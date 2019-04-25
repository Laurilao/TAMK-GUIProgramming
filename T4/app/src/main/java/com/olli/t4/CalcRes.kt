package com.olli.t4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calc_res.*

class CalcRes : AppCompatActivity() {

    var volt: Double = 0.0
    var res: Double = 0.0
    var curr: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc_res)

        calcBtn.setOnClickListener {
            try {
                volt = voltAmountInput.text.toString().toDouble()
                curr = currAmountInput.text.toString().toDouble()

                calcResult()

            } catch (e: NumberFormatException) {

            }
        }


    }
    fun calcResult() {
        if (curr <= 0.0) {
            // error
        } else {
            res = volt.div(curr)
            resultInput.setText(res.toString())
        }
    }
}
