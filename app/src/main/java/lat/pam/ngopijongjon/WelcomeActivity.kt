package lat.pam.ngopijongjon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

import android.util.Log

class WelcomeActivity : AppCompatActivity() {
    private val TAG = "WelcomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity started")
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        // Automatically adjust padding based on system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnStartNow: Button = findViewById(R.id.btnStartNow)

        // Set up button click listener
        btnStartNow.setOnClickListener {
            Log.d(TAG, "btnStartNow clicked: Navigating to AuthChoiceActivity")
            val intent = Intent(this, AuthChoiceActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "onStart: Activity visible")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "onResume: Activity in foreground")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG, "onPause: Activity losing focus")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "onStop: Activity no longer visible")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity destroyed")
    }
}