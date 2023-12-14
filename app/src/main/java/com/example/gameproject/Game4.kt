package com.example.gameproject

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gameproject.databinding.ActivityGame4Binding
import kotlin.math.pow
import kotlin.math.sqrt


class Game4 : AppCompatActivity(), SensorEventListener {
    private lateinit var binding:ActivityGame4Binding
    private lateinit var sensorManager: SensorManager
    private lateinit var accsensor: Sensor
    private lateinit var lightsensor: Sensor
    private var isStarted:Boolean=false
    private var max=0.0
    private var ligmax=0.0
    private var AA=0
    private var BB=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGame4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accsensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
        lightsensor= sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!!
        binding.imghint.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder(this@Game4)
                    .setTitle("提示")
                    .setMessage("1.照亮手機\n2.搖晃手機\n3.點他本人")
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
            sensorManager.registerListener(this,lightsensor, SensorManager.SENSOR_DELAY_UI)
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
        if (p0?.sensor?.type==Sensor.TYPE_LIGHT){
            if (p0.values[0]>ligmax) ligmax= p0.values[0].toDouble()
            if(ligmax>800) {
                ligmax=800.0
                binding.imageView.setImageResource(R.drawable.bright)
            }
        }
        if (p0!!.sensor.type==Sensor.TYPE_ACCELEROMETER) {
            val x: Double = (p0.values[0].toDouble() / SensorManager.GRAVITY_EARTH).pow(2.0)
            val y: Double = (p0.values[1].toDouble() / SensorManager.GRAVITY_EARTH).pow(2.0)
            val z: Double = (p0.values[2].toDouble() / SensorManager.GRAVITY_EARTH).pow(2.0)
            val A = sqrt(x + y + z)
            if (A > 1.5 && ligmax==800.0) {
                binding.imageView.setImageResource(R.drawable.awake)
                binding.imageView.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        binding.imageView.setImageResource(R.drawable.rise)
                        binding.imageView.setOnTouchListener { _, event ->
                            if (event.action == MotionEvent.ACTION_DOWN) {
                                binding.imageView.setImageResource(R.drawable.near)
                                binding.imageView.setOnTouchListener { _, event ->
                                    if (event.action == MotionEvent.ACTION_DOWN) {
                                        binding.imageView.setImageResource(R.drawable.smash)
                                        binding.imageView.setOnTouchListener { _, event ->
                                            if (event.action == MotionEvent.ACTION_DOWN) {
                                                Intent(this,Game5::class.java).apply {
                                                    startActivity(this)
                                                }
                                            }
                                            true
                                        }
                                    }
                                    true
                                }
                            }
                            true
                        }
                    }
                    true
                }
            }
        }

        /*
        if (max > 1.2 && ligmax==800.0) {
            binding.imageView.setImageResource(R.drawable.awake)
            AA+=1
        }
        if (AA>=1){
            binding.imageView.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    binding.imageView.setImageResource(R.drawable.smash)
                }
                true
            }
        }*/


    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }
}