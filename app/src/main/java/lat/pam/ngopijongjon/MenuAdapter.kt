package lat.pam.ngopijongjon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MenuAdapter(
    private var menuList: List<MenuItem>,
    private val onItemClick: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMenuFoto: ImageView = itemView.findViewById(R.id.ivMenuFoto)
        val tvMenuName: TextView = itemView.findViewById(R.id.tvMenuName)
        val tvMenuDesc: TextView = itemView.findViewById(R.id.tvMenuDesc)
        val tvMenuPrice: TextView = itemView.findViewById(R.id.tvMenuPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = menuList[position]
        
        // Use Glide to load and scale images efficiently
        Glide.with(holder.itemView.context)
            .load(item.imageResId)
            .centerCrop()
            .into(holder.ivMenuFoto)
            
        holder.tvMenuName.text = item.name
        holder.tvMenuDesc.text = holder.itemView.context.getString(R.string.menu_bullet_format, item.description)
        holder.tvMenuPrice.text = holder.itemView.context.getString(R.string.menu_bullet_format, item.price)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = menuList.size

    // Refresh data when a filter tab is clicked
    fun updateList(newList: List<MenuItem>) {
        menuList = newList
        notifyDataSetChanged()
    }
}