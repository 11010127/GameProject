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
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.gameproject.databinding.ActivityGame1Binding
import kotlin.math.pow
import kotlin.math.sqrt

class Game1 : AppCompatActivity(), SensorEventListener {
    private lateinit var binding:ActivityGame1Binding
    private lateinit var sensorManager: SensorManager
    private lateinit var accsensor: Sensor
    private var isStarted:Boolean=false
    private var max=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGame1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accsensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!

        binding.img1.setOnTouchListener(failItem)
        binding.img2.setOnTouchListener(failItem)
        binding.img3.setOnTouchListener(failItem)
        binding.img4.setOnTouchListener(failItem)
        binding.imghint.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("請搖晃手機")
                    .setPositiveButton("OK") { dialog, which ->
                        Toast.makeText(this, "OK", Toast.LENGTH_LONG).show()
                    }
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
        if(!isStarted){
            isStarted=false
            sensorManager.unregisterListener(this)
        }
    }
    private val failItem= object : View.OnTouchListener{
        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
            if (p1?.action==MotionEvent.ACTION_DOWN){
                when(p0?.id){
                    R.id.img1->{
                        Log.d("myTag","點到img1")
                        Toast.makeText(this@Game1,"錯誤", Toast.LENGTH_LONG).show()

                    }
                    R.id.img2->{
                        Log.d("myTag","點到img2")
                        Toast.makeText(this@Game1,"錯誤", Toast.LENGTH_LONG).show()
                    }
                    R.id.img3->{
                        Log.d("myTag","點到img3")
                        Toast.makeText(this@Game1,"錯誤", Toast.LENGTH_LONG).show()
                    }
                    R.id.img4->{
                        Log.d("myTag","點到img4")
                        Toast.makeText(this@Game1,"錯誤", Toast.LENGTH_LONG).show()
                    }
                }

            }
            return true
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0!!.sensor.type==Sensor.TYPE_ACCELEROMETER){
            val x:Double=(p0.values[0].toDouble()/SensorManager.GRAVITY_EARTH).pow(2.0)
            val y:Double=(p0.values[1].toDouble()/SensorManager.GRAVITY_EARTH).pow(2.0)
            val z:Double=(p0.values[2].toDouble()/SensorManager.GRAVITY_EARTH).pow(2.0)
            val A= sqrt(x+y+z)
            if (A>max) max=A
            if(max>1.2) {
                binding.img4.setImageResource(R.drawable.fall)

                binding.img4.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        AlertDialog.Builder(this)
                            .setTitle("勝利")
                            .setMessage("晃到我跌倒了")
                            .setPositiveButton("下一關") { dialog, which ->
                                Intent(this,PuzzleVideoGame::class.java).apply {
                                    startActivity(this)
                                }
                                Toast.makeText(this, "第二關", Toast.LENGTH_LONG).show()
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