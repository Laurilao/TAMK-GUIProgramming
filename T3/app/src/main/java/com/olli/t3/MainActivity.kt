package com.olli.t3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentFreq: Double = 0.0
    var ch1: Double = 0.0
    var ch2: Double = 0.0
    var ch3: Double = 0.0
    var selectedRadio: Int = 0
    val simulatedStations: Array<Double> = arrayOf(97.5, 99.0, 104.2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        freqBar.max = 210
        freqBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentFreq = progress * 0.1 + 87
                refreshFreqLabel()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                saveFreq()
            }
        })

        nextBtn.setOnClickListener{
            getNextPreset("Forward")
        }
        previousBtn.setOnClickListener {
            getNextPreset("Back")
        }

        ch1Radio.setOnClickListener {
            checkRadioBtn(1)
        }
        ch2Radio.setOnClickListener {
            checkRadioBtn(2)
        }
        ch3Radio.setOnClickListener {
            checkRadioBtn(3)
        }
        offRadio.setOnClickListener {
            currentFreq = 0.0
            refreshFreqBar()
        }


    }

    fun saveFreq() {
        when (selectedRadio) {
            1 -> ch1 = currentFreq
            2 -> ch2 = currentFreq
            3 -> ch3 = currentFreq
            else -> {
                // Nothing
            }
        }
    }

    fun getNextPreset(direction: String) {
        println(direction)
        if (direction == "Forward") {
            for (station in simulatedStations) {
                if (currentFreq < station) {
                    currentFreq = station
                    break
                }
            }
        }
        else {
            for (station in simulatedStations.reversed()) {
                println(station)
                if (currentFreq > station) {
                    currentFreq = station
                    break
                }
            }
        }
        refreshFreqBar()
        saveFreq()
    }

    fun refreshFreqBar() {
        val prog = ((currentFreq - 87) * 10).toInt()
        freqBar.progress = prog
        refreshFreqLabel()
    }

    fun refreshFreqLabel() {
        selectedFreqLabel.text = "$currentFreq MHz"
    }

    fun checkRadioBtn(radioNro: Int) {
        selectedRadio = radioNro

        println("Valitsit: $selectedRadio")
        when (selectedRadio) {
            1 -> currentFreq = ch1
            2 -> currentFreq = ch2
            3 -> currentFreq = ch3
            else -> {
                // Nothing
            }
        }
        refreshFreqBar()
    }
}
