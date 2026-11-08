package lat.pam.ngopijongjon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.textfield.TextInputEditText

import android.util.Log

class RegisterActivity : AppCompatActivity() {
    private val TAG = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity started")
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etFullname = findViewById<TextInputEditText>(R.id.etFullname)
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnRegisterAction: Button = findViewById(R.id.btnRegisterAction)

        btnRegisterAction.setOnClickListener {
            Log.d(TAG, "btnRegisterAction clicked: Validating registration form")
            
            val fullname = etFullname.text.toString()
            // Remove leading and trailing whitespace from user input
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Condition 1: Check for empty required fields
            if (fullname.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Log.w(TAG, "Registration failed: Missing required fields")
                Toast.makeText(this, getString(R.string.toast_empty_form), Toast.LENGTH_SHORT).show()
            }
            // Condition 2: Successfully registered
            else {
                Log.i(TAG, "Registration successful")
                Toast.makeText(this, getString(R.string.toast_register_success), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
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