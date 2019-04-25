package com.olli.t1

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    var timer: Double? = 0.0
    var zoomLevel: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshZoomLabel()

        startBtn.setOnClickListener {
            startBtn.setBackgroundColor(Color.DKGRAY)
            stopBtn.setBackgroundColor(resources.getColor(R.color.stopBtn))
            statusLabel.text = "Started"
        }

        stopBtn.setOnClickListener {
            stopBtn.setBackgroundColor(Color.DKGRAY)
            startBtn.setBackgroundColor(resources.getColor(R.color.startBtn))
            statusLabel.text = "Stopped"
        }

        zoomInBtn.setOnClickListener {
            zoomLevel++
            statusLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, (zoomLevel * 4.0f) + 36f)
            refreshZoomLabel()
        }
        zoomOutBtn.setOnClickListener {
            if (zoomLevel > 0) {
                zoomLevel--
                statusLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, (zoomLevel * 4.0f) + 36f)
                refreshZoomLabel()
            }
        }

    }

    fun refreshZoomLabel() {
        zoomLevelLabel.text = "Zoom = $zoomLevel"
    }
}
