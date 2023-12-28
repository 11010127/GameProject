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
import androidx.appcompat.app.AlertDialog
import com.example.gameproject.databinding.ActivityGame5Binding
import kotlin.math.pow
import kotlin.math.sqrt

class Game5 : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityGame5Binding
    private lateinit var sensorManager: SensorManager
    private lateinit var accsensor: Sensor
    private var isStarted:Boolean=false
    private var count=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGame5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accsensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
        binding.imghint.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder(this@Game5)
                    .setTitle("提示")
                    .setMessage("請搖晃手機")
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
            sensorManager.registerListener(this,accsensor,SensorManager.SENSOR_DELAY_UI)
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
        if (p0!!.sensor.type==Sensor.TYPE_ACCELEROMETER){
            val x:Double=(p0.values[0].toDouble()/SensorManager.GRAVITY_EARTH).pow(2.0)
            val y:Double=(p0.values[1].toDouble()/SensorManager.GRAVITY_EARTH).pow(2.0)
            val z:Double=(p0.values[2].toDouble()/SensorManager.GRAVITY_EARTH).pow(2.0)
            val A= sqrt(x+y+z)
            if (A>1.5 && count==0){
                binding.imgPeople.setImageResource(R.drawable.earthquake)
                count=1
            }

            binding.imgCard.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN && count==1) {
                    binding.imgPeople.setImageResource(R.drawable.onecards)
                    binding.imgMe.setImageResource(R.drawable.endcard)
                    binding.imgMe.setOnTouchListener { _, even ->
                        if (event.action==MotionEvent.ACTION_DOWN){
                            Intent(this,Game6::class.java).apply {
                                startActivity(this)
                            }
                        }
                        true
                    }
                }
                true
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }
}