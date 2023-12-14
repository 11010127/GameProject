package com.example.gameproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.gameproject.databinding.ActivityGame7Binding
import java.util.*

class Game7 : AppCompatActivity() {
    private lateinit var binding: ActivityGame7Binding
    private val REQUEST_CODE_SPEECH_INPUT=1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGame7Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button2.setOnClickListener {
            startSpeech()
        }
        binding.imghint.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("請用語音對話說出[生日快樂]")
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
    private fun startSpeech() {
        var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())//抓取手機系統設定語系
            putExtra(RecognizerIntent.EXTRA_PROMPT,"請說話...") //提示
        }
        try {
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT)
        }catch (ex:Exception){
            Log.d("myTag",ex.toString())
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SPEECH_INPUT){
            if(resultCode == RESULT_OK && data != null){
                val result =data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val spokenText=result?.get(0)
                Log.d("myTag",data.toString())
                checkSpokenText(spokenText)
            }else{
                Log.d("myTag","回傳錯誤")
            }
        }
    }

    private fun checkSpokenText(s:String?) {
       if (s=="生日快樂"||s=="happy birthday"){
           Log.d("myTag","成功")
           binding.imageView.setImageResource(R.drawable.happybirthday)
           binding.imageView.setOnTouchListener { _, event ->
               if (event.action == MotionEvent.ACTION_DOWN) {
                   Intent(this,Game1::class.java).apply {
                       startActivity(this)
                   }
               }
               true
           }
       }else{
           Toast.makeText(this@Game7,"錯誤", Toast.LENGTH_LONG).show()
       }
    }
}