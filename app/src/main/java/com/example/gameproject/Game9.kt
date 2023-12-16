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
import com.example.gameproject.databinding.ActivityGame1Binding
import com.example.gameproject.databinding.ActivityGame9Binding

class Game9 : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityGame9Binding
    private lateinit var sensorManager: SensorManager
    private lateinit var gysensor: Sensor
    private var isStarted:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGame9Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gysensor= sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!!
        binding.imghint.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("請將手機旋轉180度")
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
            sensorManager.registerListener(this,gysensor,SensorManager.SENSOR_DELAY_UI)
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
        if(p0?.sensor?.type==Sensor.TYPE_GYROSCOPE){
            if (Math.abs(p0.values[2])>3.0){
                binding.imageView.setImageResource(R.drawable.handstand)
                binding.imageView.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        Intent(this,Game9::class.java).apply {
                            startActivity(this)
                        }
                    }
                    true
                }
            }
           /* binding.textView.text="X=${p0.values[0]}\n" +
                    "Y=${p0.values[1]}\n" +
                    "Z=${p0.values[2]}"
            Log.d("myTag","X=${p0.values[0]}\nY=${p0.values[0]}\nZ=${p0.values[0]}")*/
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }
}