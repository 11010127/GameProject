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
import com.example.gameproject.databinding.ActivityGame10Binding
import com.example.gameproject.databinding.ActivityGame9Binding

class Game10 : AppCompatActivity(), SensorEventListener{
//
    private lateinit var binding: ActivityGame10Binding
    private lateinit var sensorManager: SensorManager
    private lateinit var gysensor: Sensor
    private lateinit var lightsensor: Sensor
    private var isStarted:Boolean=false
    private var AA=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGame10Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gysensor= sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!!
        lightsensor= sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!!
        binding.imghint.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("1.將手機旋轉45度澆花\n2.用燈照亮手機")
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
        if(p0?.sensor?.type==Sensor.TYPE_GYROSCOPE){
            if (Math.abs(p0.values[2])>2.0&&AA==0){
                binding.imageView.setImageResource(R.drawable.watering)
                AA+=1
            }

            if (Math.abs(p0.values[2])<0.1&&AA==1){
                binding.imageView.setImageResource(R.drawable.greenbuds)
                AA+=1
            }
        }
        if (p0?.sensor?.type==Sensor.TYPE_LIGHT){
            if (p0.values[0]>800&&AA==2){
                binding.imageView.setImageResource(R.drawable.flower)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }
}