package lat.pam.ngopijongjon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.textfield.TextInputEditText

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvProfileGreeting = findViewById<TextView>(R.id.tvProfileGreeting)
        val etProfileName = findViewById<TextInputEditText>(R.id.etProfileName)
        val btnSaveProfile = findViewById<Button>(R.id.btnSaveProfile)
        val btnNavHome = findViewById<Button>(R.id.btnNavHome)
        val btnNavOrder = findViewById<Button>(R.id.btnNavOrder)

        // Ambil nama saat ini dari Intent
        var currentUsername = intent.getStringExtra("USERNAME_KEY") ?: getString(R.string.default_username)
        tvProfileGreeting.text = getString(R.string.greeting_format, currentUsername)
        etProfileName.setText(currentUsername)

        // Aksi tombol Save untuk memperbarui nama statis
        btnSaveProfile.setOnClickListener {
            val newName = etProfileName.text.toString().trim()
            if (newName.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_empty_name), Toast.LENGTH_SHORT).show()
            } else {
                currentUsername = newName
                tvProfileGreeting.text = getString(R.string.greeting_format, currentUsername)
                Toast.makeText(this, getString(R.string.toast_profile_saved), Toast.LENGTH_SHORT).show()
            }
        }

        // Navigasi kembali ke Home membawa nama terbaru
        btnNavHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USERNAME_KEY", currentUsername)
            startActivity(intent)
            finish()
        }

        // Navigasi ke halaman Order membawa nama terbaru
        btnNavOrder.setOnClickListener {
            val intent = Intent(this, OrderAddressActivity::class.java)
            intent.putExtra("USERNAME_KEY", currentUsername)
            startActivity(intent)
        }
    }
}