package com.abrahamkeven.netflixo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val card1 = findViewById<LinearLayout>(R.id.cardProfile1)
        val card2 = findViewById<LinearLayout>(R.id.cardProfile2)
        val card3 = findViewById<LinearLayout>(R.id.cardProfile3)
        val card4 = findViewById<LinearLayout>(R.id.cardProfile4)
        val logout = findViewById<Button>(R.id.logoutButton)

        card1.setOnClickListener {
            irParaContent()
        }

        card2.setOnClickListener {
            irParaContent()
        }

        card3.setOnClickListener {
            irParaContent()
        }

        card4.setOnClickListener {
            irParaContent()
        }

        logout.setOnClickListener {
            irParaMain()
        }
    }

    private fun irParaContent() {
        val intent = Intent(this, ContentActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun irParaMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}