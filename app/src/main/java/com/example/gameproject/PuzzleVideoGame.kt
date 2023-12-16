package com.example.gameproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.gameproject.databinding.ActivityPuzzleVideoGameBinding

class PuzzleVideoGame : AppCompatActivity() {
    private lateinit var binding: ActivityPuzzleVideoGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPuzzleVideoGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn1.setOnClickListener {
            Intent(this,Game1::class.java).apply {
                startActivity(this)
            }
        }
        binding.btn2.setOnClickListener {
            Intent(this,Game2::class.java).apply {
                startActivity(this)
            }
        }
        binding.btn3.setOnClickListener {
            Intent(this,Game3::class.java).apply {
                startActivity(this)
            }
        }
        binding.btn4.setOnClickListener {
            Intent(this,Game4::class.java).apply {
                startActivity(this)
            }
        }
        binding.btn5.setOnClickListener {
            Intent(this,Game5::class.java).apply {
                startActivity(this)
            }
        }
        binding.btn6.setOnClickListener {
            Intent(this,Game6::class.java).apply {
                startActivity(this)
            }
        }
        binding.btn7.setOnClickListener {
            Intent(this,Game7::class.java).apply {
                startActivity(this)
            }
        }
        binding.btn8.setOnClickListener {
            Intent(this,Game8::class.java).apply {
                startActivity(this)
            }
        }
        binding.btn9.setOnClickListener {
            Intent(this,Game9::class.java).apply {
                startActivity(this)
            }
        }
    }

}