package com.rifaldi.yoga.quranapp.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.rifaldi.yoga.quranapp.databinding.ActivitySplashBinding
import com.rifaldi.yoga.quranapp.presentation.ui.base.BaseInterface

/**
 * Created by aldi on 04/04/22.
 */
class SplashActivity : AppCompatActivity(), BaseInterface{

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        subscribeListeners()
        subscribeObservers()
    }

    override fun initComponents() {

    }

    override fun subscribeListeners() {

    }

    override fun subscribeObservers() {

    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper())
            .postDelayed(Runnable {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
    }
}