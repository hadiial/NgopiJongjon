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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLoginAction: Button = findViewById(R.id.btnLoginAction)

        btnLoginAction.setOnClickListener {
            // Remove leading and trailing whitespace from user input
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Condition 1: Check for empty required fields
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_empty_username_password), Toast.LENGTH_SHORT).show()
            }
            // Condition 2: Verify credential matching (username = user, password = user123)
            else if (username == "user" && password == "user123") {
                Toast.makeText(this, getString(R.string.toast_login_success), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("USERNAME_KEY", username)
                startActivity(intent)
                finish()
            }
            // Condition 3: Invalid credentials
            else {
                Toast.makeText(this, getString(R.string.toast_invalid_credentials), Toast.LENGTH_SHORT).show()
            }
        }
    }
}