package com.example.cdio_solitaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cdio_solitaire.controller.SolitaireSolver

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val solver = SolitaireSolver()
        solver.solve()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
