package br.com.fabriciocurvello.appserviceskotlin

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService : Service() {

    private val TAG = "MyService"
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate() {
        super.onCreate()
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                Toast.makeText(this@MyService, "Serviço está em execução...", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Serviço está em execução...")
                handler.postDelayed(this, 3000)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post(runnable)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this@MyService, "Serviço está parado.", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Serviço está parado.")
        handler.removeCallbacks(runnable)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}