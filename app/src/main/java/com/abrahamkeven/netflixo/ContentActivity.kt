package com.abrahamkeven.netflixo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abrahamkeven.netflixo.ui.theme.NetflixoTheme

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
    }
}