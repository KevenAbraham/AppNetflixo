package com.abrahamkeven.netflixo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.abrahamkeven.netflixo.ui.theme.NetflixoTheme

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val logout = findViewById<Button>(R.id.logoutButton)

        val btnConferir = findViewById<Button>(R.id.checkButton)

        btnConferir.setOnClickListener {
            // URL do vídeo do YouTube que você deseja abrir
            val videoUrl = "https://www.youtube.com/watch?v=xw5oyJhsRKo" // Exemplo de URL

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setPackage("com.google.android.youtube")

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // Se o aplicativo do YouTube não estiver instalado, abrir no navegador
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                startActivity(webIntent)
            }
        }

        logout.setOnClickListener {
            irParaMain()
        }

        //Cards
        val smallImage1 = findViewById<ImageView>(R.id.smallImage1)
        smallImage1.setOnClickListener {
            val videoUrl = "https://www.youtube.com/watch?v=yNQP4clFJtw"
            abrirVideoNoYoutube(videoUrl)
        }

        val smallImage2 = findViewById<ImageView>(R.id.smallImage2)
        smallImage2.setOnClickListener {
            val videoUrl = "https://www.youtube.com/watch?v=wh-NyCsIUBc&t=1s"
            abrirVideoNoYoutube(videoUrl)
        }

        val smallImage3 = findViewById<ImageView>(R.id.smallImage3)
        smallImage3.setOnClickListener {
            val videoUrl = "https://www.youtube.com/watch?v=AwVdngHssto"
            abrirVideoNoYoutube(videoUrl)
        }

        val smallImage4 = findViewById<ImageView>(R.id.smallImage4)
        smallImage4.setOnClickListener {
            val videoUrl = "https://www.youtube.com/watch?v=T6aKnvQnWfY"
            abrirVideoNoYoutube(videoUrl)
        }

        val smallImage5 = findViewById<ImageView>(R.id.smallImage5)
        smallImage5.setOnClickListener {
            val videoUrl = "https://www.youtube.com/watch?v=kZwH4eXjx3I"
            abrirVideoNoYoutube(videoUrl)
        }

        val smallImage6 = findViewById<ImageView>(R.id.smallImage6)
        smallImage6.setOnClickListener {
            val videoUrl = "https://www.youtube.com/watch?v=PQFLHzuag1E"
            abrirVideoNoYoutube(videoUrl)
        }

    }

    private fun irParaMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun abrirVideoNoYoutube(videoUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.google.android.youtube")

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
            startActivity(webIntent)
        }
    }

}