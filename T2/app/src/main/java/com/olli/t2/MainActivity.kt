package com.olli.t2

import android.content.Context
import android.drm.DrmStore
import android.media.Ringtone
import android.media.RingtoneManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var hour: Int = 0
    var minute: Int = 0
    var timeHandler: Handler = Handler()
    var alarmHandler: Handler = Handler()
    var alarmTime: Date? = null
    lateinit var ringTone: Ringtone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ringTone: Ringtone = RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
        this.ringTone = ringTone

        initTime()

        setAlarmBtn.setOnClickListener {
            setAlarm()
        }

        removeAlarmBtn.setOnClickListener {
            stopAlarm()
        }

        checkAlarmBtn.setOnClickListener {
            stopAlarm()
        }

        snoozeBtn.setOnClickListener {
            setSnooze()
        }

        // region timeButtonListeners
        addHourBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    addHour()
                    timeHandler.postDelayed(addHourAction, 500)
                }
                MotionEvent.ACTION_UP -> {
                    timeHandler.removeCallbacks(addHourAction)
                }
            }
            return@setOnTouchListener true
        }

        minusHourBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    backHour()
                    timeHandler.postDelayed(backHourAction, 500)
                }
                MotionEvent.ACTION_UP -> {
                    timeHandler.removeCallbacks(backHourAction)
                }
            }
            return@setOnTouchListener true
        }

        addMinuteBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    addMinute()
                    timeHandler.postDelayed(addMinuteAction, 500)
                }
                MotionEvent.ACTION_UP -> {
                    timeHandler.removeCallbacks(addMinuteAction)
                }
            }
            return@setOnTouchListener true
        }

        minusMinuteBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    backMinute()
                    timeHandler.postDelayed(backMinuteAction, 500)
                }
                MotionEvent.ACTION_UP -> {
                    timeHandler.removeCallbacks(backMinuteAction)
                }
            }
            return@setOnTouchListener true
        }
        //endregion



    }

    fun setSnooze() {
        stopAlarm()
        if (alarmTime != null) {
            var snoozeTime = Calendar.getInstance(TimeZone.getTimeZone("Europe/Helsinki"))
            snoozeTime.time = alarmTime
            snoozeTime.add(Calendar.MINUTE, 10)
            alarmTime = snoozeTime.time
            updateAlarmTimeLabel(snoozeTime)
            alarmHandler.postDelayed(alarmAction, 100)
        }
    }

    val alarmAction: Runnable = object : Runnable {
        override fun run() {
            var now = Date()
            println("now: $now")
            println("alarmtime: $alarmTime")
            if (now > alarmTime) {
                playAlarm()
                alarmHandler.removeCallbacks(this)
            }
            alarmHandler.postDelayed(this, 5000) // Check every 5 seconds if its alarm time
        }
    }

    fun stopAlarm() {
        ringTone.stop()
        alarmHandler.removeCallbacks(alarmAction)
        alarmTimeLabel.text = "Asetettu: "
    }

    fun playAlarm() {
        if (!ringTone.isPlaying) {
            ringTone.play()
        }
    }

    fun setAlarm() {
        var calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Helsinki"))
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        updateAlarmTimeLabel(calendar)
        alarmTime = calendar.time
        alarmHandler.postDelayed(alarmAction, 100)
    }

    // region timeButtonActions
    var addHourAction: Runnable = object : Runnable {
        override fun run() {
            addHour()
            timeHandler.postDelayed(this, 100)
        }
    }

    var backHourAction: Runnable = object : Runnable {
        override fun run() {
            backHour()
            timeHandler.postDelayed(this, 100)
        }
    }

    var addMinuteAction: Runnable = object : Runnable {
        override fun run() {
            addMinute()
            timeHandler.postDelayed(this, 100)
        }
    }

    var backMinuteAction: Runnable = object : Runnable {
        override fun run() {
            backMinute()
            timeHandler.postDelayed(this, 100)
        }
    }

    fun addHour() {
        hour++
        if (hour == 24) hour = 0
        if (hour < 10) {
            hoursLabel.text = "0$hour"
        } else {
            hoursLabel.text = "$hour"
        }
    }

    fun backHour() {
        hour--
        if (hour < 0) hour = 23
        if (hour < 10) {
            hoursLabel.text = "0$hour"
        } else {
            hoursLabel.text = "$hour"
        }
    }

    fun addMinute() {
        minute++
        if (minute == 60) {
            minute = 0
            hour++
        }
        renderTime()
    }

    fun backMinute() {
        minute--
        if (minute < 0) {
            minute = 59
            hour--
        }
        renderTime()
    }
    //endregion

    fun initTime() {
        val now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Helsinki"))
        hour = now.get(Calendar.HOUR_OF_DAY)
        minute = now.get(Calendar.MINUTE)
        renderTime()
    }

    fun updateAlarmTimeLabel(calendar: Calendar) {
        val hh = calendar.get(Calendar.HOUR_OF_DAY)
        val mm = calendar.get(Calendar.MINUTE)
        var str = if (hh < 10) "Asetettu: 0$hh:" else "Asetettu: $hh:"
        if (mm < 10) str = str + "0$mm" else str = str + "$mm"
        alarmTimeLabel.text = str
    }

    fun renderTime() {
        hoursLabel.text = if (hour < 10) "0$hour" else "$hour"
        minutesLabel.text = if (minute < 10) "0$minute" else "$minute"
    }
}
