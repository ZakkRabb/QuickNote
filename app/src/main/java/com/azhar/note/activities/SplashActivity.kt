package com.azhar.note.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.azhar.modelNote.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Mengatur layout splash screen

        // Thread untuk menahan tampilan splash selama beberapa detik
        val splashTimeOut = 2000 // Waktu tampilan splash dalam milidetik (misalnya 3000ms = 3 detik)

        val homeIntent = Intent(this, MainActivity::class.java) // Ganti MainActivity dengan kelas utama aplikasi Anda
        val splashTimer = object : Thread() {
            override fun run() {
                try {
                    sleep(splashTimeOut.toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(homeIntent)
                    finish() // Menutup aktivitas splash screen
                }
            }
        }
        splashTimer.start()
    }
}
