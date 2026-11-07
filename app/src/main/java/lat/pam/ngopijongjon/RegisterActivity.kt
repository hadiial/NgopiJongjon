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

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Agar ikon status bar (jam, baterai) berwarna gelap di atas background putih
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

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
            val fullname = etFullname.text.toString()
            // Remove leading and trailing whitespace from user input
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Condition 1: Check for empty required fields
            if (fullname.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Mohon untuk lengkapi formulir di atas!", Toast.LENGTH_SHORT).show()
            }
            // Condition 2:
            else {
                Toast.makeText(this, "Daftar berhasil! Silakan masuk untuk melanjutkan", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}