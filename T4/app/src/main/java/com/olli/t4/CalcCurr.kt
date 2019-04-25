package com.olli.t4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calc_curr.*
import java.lang.NumberFormatException

class CalcCurr : AppCompatActivity() {

    var volt: Double = 0.0
    var res: Double = 0.0
    var curr: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc_curr)

        calcBtn.setOnClickListener {
            try {
                volt = voltAmountInput.text.toString().toDouble()
                res = resAmountInput.text.toString().toDouble()

                calcResult()

            } catch (e: NumberFormatException) {

            }
        }

    }

    fun calcResult(){
        if (res <= 0.0) {
            // error
        } else {
            curr = volt.div(res)
            resultInput.setText(curr.toString())
        }
    }
}
