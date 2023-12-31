package com.example.gameproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gameproject.databinding.ActivityPuzzleVideoGameBinding

class PuzzleVideoGame : AppCompatActivity() {
    private lateinit var binding: ActivityPuzzleVideoGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityPuzzleVideoGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn2.isEnabled=false
        binding.btn3.isEnabled=false
        binding.btn4.isEnabled=false
        binding.btn5.isEnabled=false
        binding.btn6.isEnabled=false
        binding.btn7.isEnabled=false
        binding.btn8.isEnabled=false
        binding.btn9.isEnabled=false
        binding.btn10.isEnabled=false
        binding.btn1.setOnClickListener {
            Intent(this,Game1::class.java).apply {
                startActivity(this)
                binding.btn1.setBackgroundColor(Color.RED)
                binding.btn2.isEnabled=true
            }
        }


        binding.btn2.setOnClickListener {
            Intent(this,Game2::class.java).apply {
                startActivity(this)
                binding.btn2.setBackgroundColor(Color.RED)
                binding.btn3.isEnabled=true
            }
        }
        binding.btn3.setOnClickListener {
            Intent(this,Game3::class.java).apply {
                startActivity(this)
                binding.btn3.setBackgroundColor(Color.RED)
                binding.btn4.isEnabled=true
            }
        }
        binding.btn4.setOnClickListener {
            Intent(this,Game4::class.java).apply {
                startActivity(this)
                binding.btn4.setBackgroundColor(Color.RED)
                binding.btn5.isEnabled=true
            }
        }
        binding.btn5.setOnClickListener {
            Intent(this,Game5::class.java).apply {
                startActivity(this)
                binding.btn5.setBackgroundColor(Color.RED)
                binding.btn6.isEnabled=true
            }
        }
        binding.btn6.setOnClickListener {
            Intent(this,Game6::class.java).apply {
                startActivity(this)
                binding.btn6.setBackgroundColor(Color.RED)
                binding.btn7.isEnabled=true
            }
        }
        binding.btn7.setOnClickListener {
            Intent(this,Game7::class.java).apply {
                startActivity(this)
                binding.btn7.setBackgroundColor(Color.RED)
                binding.btn8.isEnabled=true
            }
        }
        binding.btn8.setOnClickListener {
            Intent(this,Game8::class.java).apply {
                startActivity(this)
                binding.btn8.setBackgroundColor(Color.RED)
                binding.btn9.isEnabled=true
            }
        }
        binding.btn9.setOnClickListener {
            Intent(this,Game9::class.java).apply {
                startActivity(this)
                binding.btn9.setBackgroundColor(Color.RED)
                binding.btn10.isEnabled=true
            }
        }
        binding.btn10.setOnClickListener {
            Intent(this,Game10::class.java).apply {
                startActivity(this)
                binding.btn10.setBackgroundColor(Color.RED)
            }
        }
    }

}