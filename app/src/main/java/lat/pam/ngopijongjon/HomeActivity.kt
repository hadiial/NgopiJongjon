package lat.pam.ngopijongjon

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

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
        val btnTabAll = findViewById<MaterialButton>(R.id.btnTabAll)
        val btnTabDrinks = findViewById<MaterialButton>(R.id.btnTabDrinks)
        val btnTabFood = findViewById<MaterialButton>(R.id.btnTabFood)
        val btnNavOrder = findViewById<Button>(R.id.btnNavOrder)
        val btnNavProfile = findViewById<Button>(R.id.btnNavProfile)

        // Retrieve the username from the Login or Register screen
        val username = intent.getStringExtra("USERNAME_KEY") ?: getString(R.string.default_username)
        tvGreeting.text = getString(R.string.greeting_format, username)

        // Static menu data with categories and images
        val allMenuList = listOf(
            // Beverage category
            MenuItem("Americano", "Espresso, air panas/dingin", "Rp 20.000", "Minuman", R.drawable.espresso),
            MenuItem("Kopi Susu Gula Aren", "Espresso, susu, gula aren", "Rp 22.000", "Minuman", R.drawable.kopsu_aren),
            MenuItem("Cappuccino", "Espresso, foam susu lembut", "Rp 25.000", "Minuman", R.drawable.cappuccino),
            MenuItem("Matcha Latte", "Matcha jepang, susu segar", "Rp 27.000", "Minuman", R.drawable.matcha),
            MenuItem("Lemon Tea", "Teh, lemon segar", "Rp 18.000", "Minuman", R.drawable.lemon_tea),
            MenuItem("Air Mineral", "Air minum kemasan", "Rp 10.000", "Minuman", R.drawable.water),

            // Food category
            MenuItem("Donat Cokelat", "Donat empuk, glaze cokelat", "Rp 12.000", "Makanan", R.drawable.donut),
            MenuItem("Croissant", "Croissant mentega renyah", "Rp 15.000", "Makanan", R.drawable.croissant),
            MenuItem("French Fries", "Kentang goreng gurih", "Rp 20.000", "Makanan", R.drawable.fries),
            MenuItem("Chicken Wings", "Wings empuk, saus BBQ panggang", "Rp 35.000", "Makanan", R.drawable.wings)
        )

        // Attach the adapter to the RecyclerView
        val menuAdapter = MenuAdapter(allMenuList) { selectedItem ->
            Toast.makeText(this, getString(R.string.toast_memilih, selectedItem.name), Toast.LENGTH_SHORT).show()
            val intent = Intent(this, OrderAddressActivity::class.java)
            intent.putExtra("USERNAME_KEY", username)
            startActivity(intent)
        }
        rvMenu.layoutManager = LinearLayoutManager(this)
        rvMenu.adapter = menuAdapter

        // Helper function to update the active tab button style
        fun setActiveTab(activeBtn: MaterialButton, inactiveBtn1: MaterialButton, inactiveBtn2: MaterialButton) {
            // Active tab style (solid black background, white text)
            activeBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.btn_dark))
            activeBtn.setTextColor(ContextCompat.getColor(this, R.color.bg_primary))
            activeBtn.strokeWidth = 0

            // Inactive tab style (cream background, black text, thin border)
            val inactiveBg = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.surface_card))
            val inactiveText = ContextCompat.getColor(this, R.color.text_primary)
            val borderStroke = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.border_light))

            listOf(inactiveBtn1, inactiveBtn2).forEach { btn ->
                btn.backgroundTintList = inactiveBg
                btn.setTextColor(inactiveText)
                btn.strokeColor = borderStroke
                btn.strokeWidth = 3
            }
        }

        // Handle filter tab click events
        btnTabAll.setOnClickListener {
            setActiveTab(btnTabAll, btnTabDrinks, btnTabFood)
            menuAdapter.updateList(allMenuList)
        }

        btnTabDrinks.setOnClickListener {
            setActiveTab(btnTabDrinks, btnTabAll, btnTabFood)
            val drinksOnly = allMenuList.filter { it.category == "Minuman" }
            menuAdapter.updateList(drinksOnly)
        }

        btnTabFood.setOnClickListener {
            setActiveTab(btnTabFood, btnTabAll, btnTabDrinks)
            val foodOnly = allMenuList.filter { it.category == "Makanan" }
            menuAdapter.updateList(foodOnly)
        }

        // Bottom navigation actions
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