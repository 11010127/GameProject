package com.example.gameproject

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import com.example.gameproject.databinding.ActivityGame8Binding

class Game8 : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityGame8Binding
    private lateinit var sensorManager: SensorManager
    private lateinit var proSensor: Sensor
    private var isStarted:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGame8Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proSensor= sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)!!


        binding.img.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                Toast.makeText(this@Game8,"錯誤", Toast.LENGTH_LONG).show()
            }
            true
        }
    }
    override fun onResume() {
        super.onResume()
        if(!isStarted){
            isStarted=true
            sensorManager.registerListener(this,proSensor,SensorManager.SENSOR_DELAY_UI)
        }

    }
    override fun onPause() {
        super.onPause()
        if(isStarted){
            isStarted=false
            sensorManager.unregisterListener(this)
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        binding.imgcall.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                binding.img.setImageResource(R.drawable.connect)
                if(p0?.sensor?.type==Sensor.TYPE_PROXIMITY){
                    if (p0.values[0]<0.1){
                        binding.img.setImageResource(R.drawable.endofconnection)
                    }
                }
            }
            true
        }


    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }
}