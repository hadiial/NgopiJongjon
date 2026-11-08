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

class OrderAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_address)

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etFullName = findViewById<TextInputEditText>(R.id.etFullName)
        val etAddress = findViewById<TextInputEditText>(R.id.etAddress)
        val etAddressDetail = findViewById<TextInputEditText>(R.id.etAddressDetail)
        val btnOrderSend = findViewById<Button>(R.id.btnOrderSend)

        // Pre-fill the full name field using the username passed from HomeActivity
        val usernameFromHome = intent.getStringExtra("USERNAME_KEY")
        if (!usernameFromHome.isNullOrEmpty()) {
            etFullName.setText(usernameFromHome)
        }

        btnOrderSend.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val address = etAddress.text.toString().trim()
            val detail = etAddressDetail.text.toString().trim()

            // Validate that all required address fields are filled
            if (fullName.isEmpty() || address.isEmpty() || detail.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_empty_address), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.toast_order_processed), Toast.LENGTH_SHORT).show()

                // Navigate to OrderSuccessActivity and pass the customer's name
                val intent = Intent(this, OrderSuccessActivity::class.java)
                intent.putExtra("USERNAME_KEY", fullName)
                startActivity(intent)
                finish()
            }
        }
    }
}