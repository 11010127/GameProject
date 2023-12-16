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
import com.example.gameproject.databinding.ActivityGame8Binding

class Game8 : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityGame8Binding
    private lateinit var sensorManager: SensorManager
    private lateinit var proSensor: Sensor
    private var isStarted:Boolean=false
    private var AA=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGame8Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proSensor= sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)!!
        binding.imghint.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("1.點選接通電話\n2.將手機靠近耳朵\n3.點選下一關")
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
        binding.imgcall.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                binding.img.setImageResource(R.drawable.connect)
                binding.imgcall.setImageDrawable(null)
                binding.imgx.setImageDrawable(null)
                AA+=1
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
        if(p0?.sensor?.type==Sensor.TYPE_PROXIMITY){
            if (p0.values[0]<0.1&&AA==1){
                binding.img.setImageResource(R.drawable.endofconnection)
                binding.img.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        Intent(this,Game9::class.java).apply {
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