package com.example.gameproject

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.gameproject.databinding.ActivityGame2Binding
import com.example.gameproject.databinding.ActivityGame6Binding

class Game6 : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityGame6Binding
    private lateinit var sensorManager: SensorManager
    private lateinit var lightsensor: Sensor
    private var isStarted:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGame6Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightsensor= sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!!
        binding.imghint.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("請讓手機待在暗處或\n遮住手機光感測器")
                    .setPositiveButton("OK") { dialog, which ->
                        Toast.makeText(this, "OK", Toast.LENGTH_LONG).show()
                    }
                    .setNegativeButton("退出") { dialog, which ->
                        finish()
                    }
                    .show()
            }
            true
        }
    }
    override fun onResume() {
        super.onResume()
        if(!isStarted){
            isStarted=true
            sensorManager.registerListener(this@Game6,lightsensor, SensorManager.SENSOR_DELAY_UI)
        }

    }
    override fun onPause() {
        super.onPause()
        if(isStarted){
            isStarted=false
            sensorManager.unregisterListener(this@Game6)
        }
    }
    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0?.sensor?.type==Sensor.TYPE_LIGHT){
            if (p0.values[0]<10){
                binding.imageView.setImageResource(R.drawable.night1)
                binding.imageView.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        Intent(this,Game7::class.java).apply {
                            startActivity(this)
                        }
                    }
                    true
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }
}