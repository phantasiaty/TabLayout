package com.example.tablayout.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablayout.LoginActivity
import com.example.tablayout.MainActivity
import com.example.tablayout.R
import java.util.*

class splashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Timer().schedule(object:TimerTask(){
            override fun run() {
                val intent = Intent(this@splashActivity, StepCounter::class.java)
                startActivity(intent)
                finish()

                //com.example.tablayout.Timer
            }

        },3000L)
    }
}
