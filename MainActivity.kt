package com.example.colortiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.colortiles.View.MyView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyView(this))
    }
}