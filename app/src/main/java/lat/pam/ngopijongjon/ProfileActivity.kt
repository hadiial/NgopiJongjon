package lat.pam.ngopijongjon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.textfield.TextInputEditText

import android.util.Log

class ProfileActivity : AppCompatActivity() {
    private val TAG = "ProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity started")
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        // Set status bar icons to light mode for the espresso header
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvProfileGreeting = findViewById<TextView>(R.id.tvProfileGreeting)
        val etProfileName = findViewById<TextInputEditText>(R.id.etProfileName)
        val btnSaveProfile = findViewById<Button>(R.id.btnSaveProfile)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnNavHome = findViewById<Button>(R.id.btnNavHome)
        val btnNavOrder = findViewById<Button>(R.id.btnNavOrder)

        // Retrieve the current username from the Intent
        var currentUsername = intent.getStringExtra("USERNAME_KEY") ?: getString(R.string.default_username)
        tvProfileGreeting.text = getString(R.string.greeting_format, currentUsername)
        etProfileName.setText(currentUsername)

        // Save button action to update static username
        btnSaveProfile.setOnClickListener {
            Log.d(TAG, "btnSaveProfile clicked: Validating profile name update")
            val newName = etProfileName.text.toString().trim()
            if (newName.isEmpty()) {
                Log.w(TAG, "Profile update failed: Name cannot be empty")
                Toast.makeText(this, getString(R.string.toast_empty_name), Toast.LENGTH_SHORT).show()
            } else {
                Log.i(TAG, "Profile updated successfully")
                currentUsername = newName
                tvProfileGreeting.text = getString(R.string.greeting_format, currentUsername)
                Toast.makeText(this, getString(R.string.toast_profile_saved), Toast.LENGTH_SHORT).show()
            }
        }

        // Logout button action with confirmation popup
        btnLogout.setOnClickListener {
            Log.d(TAG, "btnLogout clicked: Showing logout confirmation dialog")
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_logout_title))
                .setMessage(getString(R.string.dialog_logout_message))
                .setPositiveButton(getString(R.string.dialog_yes)) { _, _ ->
                    Log.i(TAG, "Logout confirmed. Navigating to WelcomeActivity")
                    // Return to WelcomeActivity and clear activity stack
                    val intent = Intent(this, WelcomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton(getString(R.string.dialog_no)) { _, _ ->
                    Log.d(TAG, "Logout cancelled")
                }
                .show()
        }

        // Navigate back to Home and pass the latest username
        btnNavHome.setOnClickListener {
            Log.d(TAG, "btnNavHome clicked: Navigating back to HomeActivity")
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USERNAME_KEY", currentUsername)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        // Navigate to Order screen and pass the latest username
        btnNavOrder.setOnClickListener {
            Log.d(TAG, "btnNavOrder clicked: Navigating to OrderAddressActivity")
            val intent = Intent(this, OrderAddressActivity::class.java)
            intent.putExtra("USERNAME_KEY", currentUsername)
            startActivity(intent)
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