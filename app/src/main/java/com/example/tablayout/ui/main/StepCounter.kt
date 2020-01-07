package com.example.tablayout.ui.main

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_step_counter.*

class StepCounter : AppCompatActivity(), SensorEventListener {


    var running = false
    var sensorManager:SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tablayout.R.layout.activity_step_counter)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume(){
        super.onResume()
        running = true
        var stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if(stepSensor == null){
            Toast.makeText(this,"No Step Counter Sensor!",Toast.LENGTH_SHORT).show()
        }else{
            sensorManager?.registerListener(this,stepSensor,SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause(){
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }


    override fun onSensorChanged(event: SensorEvent) {
        if(running){
            stepsValue.setText("" + event.values[0])
        }
    }


}
