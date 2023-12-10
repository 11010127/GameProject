package com.example.gameproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gameproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btSnake.setOnClickListener {
            Intent(this@MainActivity,SnakeGame::class.java).apply {
                startActivity(this)
            }
        }
        binding.btPuzzle.setOnClickListener {
            Intent(this@MainActivity,PuzzleVideoGame::class.java).apply {
                startActivity(this)
            }
        }
    }
}