package com.abrahamkeven.netflixo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.abrahamkeven.netflixo.ui.theme.NetflixoTheme

class ForgetActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)

        val logo = findViewById<ImageView>(R.id.logoImageView)
        val btn = findViewById<Button>(R.id.completeButton)

        logo.setOnClickListener {
            irParaMain()
        }

        btn.setOnClickListener {
            irParaLogin()
        }
    }

    private fun irParaMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irParaLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}