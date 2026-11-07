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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val rvMenu = findViewById<RecyclerView>(R.id.rvMenu)
        val btnNavOrder = findViewById<Button>(R.id.btnNavOrder)
        val btnNavProfile = findViewById<Button>(R.id.btnNavProfile)

        // 1. Tangkap nama/username dari Login atau Register (jika ada)
        val username = intent.getStringExtra("USERNAME_KEY") ?: "Teman Ngopi"
        tvGreeting.text = "Halo $username,"

        // 2. Siapkan 5 Data Statis (Dummy Data) sesuai Wireframe
        val dummyMenuList = listOf(
            MenuItem("Kopi Susu Gula Aren", "Espresso, susu, gula aren", "Rp 22.000"),
            MenuItem("Donat Cokelat", "Donat empuk, glaze cokelat", "Rp 12.000"),
            MenuItem("Sourdough Loaf", "Roti fermentasi alami", "Rp 35.000"),
            MenuItem("Cappuccino", "Espresso, foam susu lembut", "Rp 25.000"),
            MenuItem("Matcha Latte", "Matcha jepang, susu segar", "Rp 27.000")
        )

        // 3. Pasang Adapter ke RecyclerView
        rvMenu.layoutManager = LinearLayoutManager(this)
        rvMenu.adapter = MenuAdapter(dummyMenuList) { selectedItem ->
            Toast.makeText(this, "Memilih: ${selectedItem.name}", Toast.LENGTH_SHORT).show()
            // Opsional: Langsung pindah ke halaman Order saat item diklik
            val intent = Intent(this, OrderAddressActivity::class.java)
            intent.putExtra("USERNAME_KEY", username)
            startActivity(intent)
        }

        // 4. Navigasi Tombol Bawah
        btnNavOrder.setOnClickListener {
            val intent = Intent(this, OrderAddressActivity::class.java)
            intent.putExtra("USERNAME_KEY", username)
            startActivity(intent)
        }

        btnNavProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("USERNAME_KEY", username)
            startActivity(intent)
        }
    }
}