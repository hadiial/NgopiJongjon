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

class OrderSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_success)

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvSuccessGreeting = findViewById<TextView>(R.id.tvSuccessGreeting)
        val btnOke = findViewById<Button>(R.id.btnOke)

        // Tangkap nama pemesan dari OrderAddressActivity
        val username = intent.getStringExtra("USERNAME_KEY") ?: getString(R.string.default_username)
        tvSuccessGreeting.text = getString(R.string.greeting_format, username)

        btnOke.setOnClickListener {
            // Kembali ke halaman Home dan bersihkan riwayat halaman order
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USERNAME_KEY", username)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}