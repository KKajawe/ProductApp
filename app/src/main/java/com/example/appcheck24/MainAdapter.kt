package com.example.appcheck24

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcheck24.Network.ProductX
import com.example.appcheck24.Network.ProductXX
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

class MainAdapter(private val products: ArrayList<ProductXX>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView) ,LayoutContainer{

        fun bind(product: ProductXX) {
            itemView.apply {
                txtViewtitle.text = product.name
                description.text = product.description
                Glide.with(imageLeft.context)
                    .load(product.imageURL)
                    .into(prodctavailable_Img)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false),
        )

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(products.get(position))
    }

    fun addProducts(products: ProductX) {
        this.products.apply {
            clear()
            addAll(products.products!!)
        }

    }
}

