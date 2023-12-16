package com.example.gameproject

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.gameproject.databinding.ActivityGame1Binding
import com.example.gameproject.databinding.ActivityGame3Binding

class Game3 : AppCompatActivity(), LocationListener {
    private lateinit var binding: ActivityGame3Binding
    private lateinit var locationManager: LocationManager
    private var hasGPS: Boolean = false
    private var hasNetwork: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGame3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imghint.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("請到致理科技大學幫她找眼鏡")
                    .setPositiveButton("OK") { dialog, which ->
                        Toast.makeText(this, "OK", Toast.LENGTH_LONG).show()
                    }
                    .setNegativeButton("退出遊戲") { dialog, which ->
                        finish()
                    }
                    .show()
            }
            true
        }
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //檢視 gps and network GPS_PROVIDER代碼
        hasGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (hasGPS) Log.d("myTag", "設備提供GPS")
        if (hasNetwork) Log.d("myTag", "設備提供網路定位")
        //登記 location / GPS  or
        if (hasGPS || hasNetwork) {
            //誰提供，時間，距離，處理什麼事情
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //return
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            }
            if (hasGPS)
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    1.0F,
                    this
                )
            if (hasNetwork)
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    1.0F,
                    this
                )
        } else {
            Log.d("myTag", "設備未提供定位服務")
        }

    }

    override fun onResume() {
        super.onResume()
        //啟動GPS的event
        if (hasGPS || hasNetwork) {
            //誰提供，時間，距離，處理什麼事情
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            }
            if (hasGPS)
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    1.0F,
                    this
                )
            if (hasNetwork)
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    1.0F,
                    this
                )
        } else {
            Log.d("myTag", "設備未提供定位服務")
        }
    }

    //跳到背景
    override fun onPause() {
        super.onPause()
        locationManager.removeUpdates(this)
    }
    override fun onLocationChanged(p0: Location) {
        var lat=p0.latitude.toString().toDouble()
        var long=p0.longitude.toString().toDouble()
        Log.d("myTag", "??"+p0.latitude.toString()+p0.longitude.toString())
//        緯度範圍：25.021055628076443 到 25.02121638064911
//        經度範圍：121.46517663348575 到 121.46522059134752
        //if(lat>37&&lat<38){
         //if(long>-123&&long<-121){
       // if(lat>25&&lat<26){
            //if(long>121&&long<122){
        if(lat>25.02003796061073&&lat<25.02257993300558&&long>121.46224450536772&&long<121.46545539457185){
//        if(lat>25.020508162721168&&lat<25.021692944469446&&long>121.46195414533585&&long<121.46530506938743){

                //if(long>-123&&long<-121){
                //  if(lat>37&&lat<38){
                //binding3.textView.text=p0.latitude.toString()+p0.longitude.toString()
                binding.imageView.setImageResource(R.drawable.glasses)
                binding.textView.text="請點擊找到的眼鏡"
                binding.imageView.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        binding.imageView.setImageResource(R.drawable.glassesgirl2)
                        binding.textView.text="哇！謝謝你！"
                        binding.imageView.setOnTouchListener { _, event ->
                            if (event.action == MotionEvent.ACTION_DOWN) {
                                Intent(this,Game4::class.java).apply {
                                    startActivity(this)
                                }
                                Toast.makeText(this@Game3, "第四關", Toast.LENGTH_LONG).show()

                            }
                            true
                        }
                    }
                    true
                }

        }



    }
}