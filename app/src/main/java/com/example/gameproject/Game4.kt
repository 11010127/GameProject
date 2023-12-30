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
    private var ligmax=0.0
    private var count=0
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
        if(!isStarted){ //!=不  //isStarted=falst  =true   false  --- true
            isStarted=true
            sensorManager.registerListener(this,accsensor,SensorManager.SENSOR_DELAY_UI)
            sensorManager.registerListener(this,lightsensor, SensorManager.SENSOR_DELAY_UI)
        }

    }
    override fun onPause() {
        super.onPause()
        if(isStarted){  //true  --- false
            isStarted=false
            sensorManager.unregisterListener(this)
        }
    }
    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0?.sensor?.type==Sensor.TYPE_LIGHT){
            if (p0.values[0]>ligmax) ligmax= p0.values[0].toDouble()
            if(ligmax>800 && count==0) {
                count=1
                binding.imageView.setImageResource(R.drawable.bright)
            }
        }
        if (p0!!.sensor.type==Sensor.TYPE_ACCELEROMETER) {
            val x: Double = (p0.values[0].toDouble() / SensorManager.GRAVITY_EARTH).pow(2.0)
            val y: Double = (p0.values[1].toDouble() / SensorManager.GRAVITY_EARTH).pow(2.0)
            val z: Double = (p0.values[2].toDouble() / SensorManager.GRAVITY_EARTH).pow(2.0)
            val A = sqrt(x + y + z)
            if (A > 1.5 && count==1) {
                binding.imageView.setImageResource(R.drawable.awake)
                count=2
                binding.imageView.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN && count==2) {
                        //binding.textView.text="$AB"
                        binding.imageView.setImageResource(R.drawable.rise)
                        count=3
                        binding.imageView.setOnTouchListener { _, event ->
                            if (event.action == MotionEvent.ACTION_DOWN && count==3) {
                                binding.imageView.setImageResource(R.drawable.near)
                                count=4
                                binding.imageView.setOnTouchListener { _, event ->
                                    if (event.action == MotionEvent.ACTION_DOWN && count==4) {
                                        binding.imageView.setImageResource(R.drawable.smash)
                                        count=5
                                        binding.imageView.setOnTouchListener { _, event ->
                                            if (event.action == MotionEvent.ACTION_DOWN && count==5) {
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
    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }
}