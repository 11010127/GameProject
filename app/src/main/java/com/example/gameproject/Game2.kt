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
import com.example.gameproject.databinding.ActivityGame2Binding

class Game2 : AppCompatActivity(), SensorEventListener {
    private lateinit var binding2: ActivityGame2Binding
    private lateinit var sensorManager: SensorManager
    private lateinit var lightsensor: Sensor
    private var isStarted:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2=ActivityGame2Binding.inflate(layoutInflater)
        setContentView(binding2.root)
        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightsensor= sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!!
        binding2.img1.setOnTouchListener(failItem)
        binding2.img2.setOnTouchListener(failItem)
        binding2.img3.setOnTouchListener(failItem)
        binding2.img4.setOnTouchListener(failItem)
        binding2.imghint.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("請照亮手機")
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
            sensorManager.registerListener(this@Game2,lightsensor, SensorManager.SENSOR_DELAY_UI)
        }

    }
    override fun onPause() {
        super.onPause()
        if(!isStarted){
            isStarted=false
            sensorManager.unregisterListener(this@Game2)
        }
    }

    private val failItem= object : View.OnTouchListener{
        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
            if (p1?.action== MotionEvent.ACTION_DOWN){
                when(p0?.id){
                    R.id.img1->{
                        Log.d("myTag","點到img1")
                        Toast.makeText(this@Game2,"錯誤", Toast.LENGTH_LONG).show()
                    }
                    R.id.img2->{
                        Log.d("myTag","點到img2")
                        Toast.makeText(this@Game2,"錯誤", Toast.LENGTH_LONG).show()
                    }
                    R.id.img3->{
                        Log.d("myTag","點到img3")
                        Toast.makeText(this@Game2,"錯誤", Toast.LENGTH_LONG).show()
                    }
                    R.id.img4->{
                        Log.d("myTag","點到img4")
                        Toast.makeText(this@Game2,"錯誤", Toast.LENGTH_LONG).show()
                    }
                }

            }
            return true
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0?.sensor?.type==Sensor.TYPE_LIGHT){
            if (p0.values[0]>800){
                binding2.img1.setImageResource(R.drawable.melt)
                binding2.img4.setImageResource(R.drawable.melt)
                binding2.img2.setImageResource(R.drawable.melt)

                binding2.img3.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        AlertDialog.Builder(this@Game2)
                            .setTitle("勝利")
                            .setMessage("我是巧克力冰淇淋...")
                            .setPositiveButton("下一關") { dialog, which ->
                                Intent(this,Game1::class.java).apply {
                                    startActivity(this)
                                }
                                Toast.makeText(this@Game2, "第三關", Toast.LENGTH_LONG).show()

                            }
                    }
                    true
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

}