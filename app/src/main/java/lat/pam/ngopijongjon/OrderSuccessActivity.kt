package lat.pam.ngopijongjon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

import android.util.Log

class OrderSuccessActivity : AppCompatActivity() {
    private val TAG = "OrderSuccessActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity started")
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_success)

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvSuccessGreeting = findViewById<TextView>(R.id.tvSuccessGreeting)
        val btnOke = findViewById<Button>(R.id.btnOke)

        // Retrieve the customer's name passed from OrderAddressActivity
        val username = intent.getStringExtra("USERNAME_KEY") ?: getString(R.string.default_username)
        tvSuccessGreeting.text = getString(R.string.greeting_format, username)

        btnOke.setOnClickListener {
            Log.d(TAG, "btnOke clicked: Returning to HomeActivity and clearing order stack")
            // Return to HomeActivity and clear the order flow back stack
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USERNAME_KEY", username)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
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