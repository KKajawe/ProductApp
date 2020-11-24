package com.example.appcheck24

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcheck24.Network.Product

class MainAdapter(private val products: ArrayList<Product>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {
            itemView.apply {
                txtViewtitle.text = product.productItem.name
                description.text = product.productItem.description
                Glide.with(imageLeft.context)
                    .load(product.productItem.imageURL)
                    .into(imageLeft)
            }
        }

        var txtViewtitle: TextView
        var description: TextView
        var price: TextView
        var imageLeft: ImageView
        var imageRight: ImageView

        init {
            txtViewtitle = itemView.findViewById(R.id.txtViewtitle)
            description = itemView.findViewById(R.id.description)
            price = itemView.findViewById(R.id.price)
            imageLeft = itemView.findViewById(R.id.imageLeft)
            imageRight = itemView.findViewById(R.id.imageRight)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun addProducts(products: List<Product>) {
        this.products.apply {
            clear()
            addAll(products)
        }

    }
}