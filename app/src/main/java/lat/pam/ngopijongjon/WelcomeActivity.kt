package lat.pam.ngopijongjon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        // Memastikan ikon status bar (jam, sinyal, baterai) berwarna hitam di atas BG #FFFFFF
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        // Menyesuaikan padding otomatis dengan system bars (status bar & navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnStartNow: Button = findViewById(R.id.btnStartNow)

        btnStartNow.setOnClickListener {
            val intent = Intent(this, AuthChoiceActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}