package ru.spbstu.icc.kspt.lab2.continuewatch
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class preference: AppCompatActivity() {var secondsElapsed: Int = 0
    var c = 0;
    private lateinit var prefernce: SharedPreferences
    lateinit var textSecondsElapsed: TextView

    companion object{
        const val TAG = "count"
    }

    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            textSecondsElapsed.post {
                textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefernce = getPreferences(Context.MODE_PRIVATE)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
    }

    override fun onStop() {
        super.onStop()
        prefernce.edit().putInt(TAG, secondsElapsed).apply()
    }

    override fun onResume() {
        super.onResume()
        secondsElapsed = prefernce.getInt(TAG, 0)
    }
}