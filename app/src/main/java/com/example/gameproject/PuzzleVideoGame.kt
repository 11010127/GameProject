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
    }

}