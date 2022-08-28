package com.hcdisat.globotour.city

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.hcdisat.globotour.R
import com.hcdisat.globotour.databinding.ListItemCityBinding

class CityAdapter(
    private val cities: MutableList<City> = mutableListOf()
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.CityViewHolder =
        CityViewHolder(
            ListItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CityAdapter.CityViewHolder, position: Int) =
        holder.bind(cities[position], position)

    override fun getItemCount(): Int = cities.size

    inner class CityViewHolder(
        private val itemBinding: ListItemCityBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val isFavIcon = ResourcesCompat.getDrawable(
            itemBinding.root.context.resources,
            R.drawable.ic_favorite_filled,
            null
        )

        private val isNotFavIcon = ResourcesCompat.getDrawable(
            itemBinding.root.context.resources,
            R.drawable.ic_favorite_bordered,
            null
        )

        fun bind(city: City, position: Int) {
            itemBinding.txvCityName.text = city.name
            itemBinding.imvCity.setImageResource(city.imageId)
            setFavorite(city)

            itemBinding.imvFavorite.setOnClickListener {
                cities[position] = city.copy(isFavorite = !city.isFavorite)
                notifyItemChanged(position)
            }

            itemBinding.imvDelete.setOnClickListener {
                cities.removeAt(position)
                notifyDataSetChanged()
            }
        }

        private fun setFavorite(city: City) {
            itemBinding.imvFavorite.setImageDrawable(
                if (city.isFavorite) isFavIcon else isNotFavIcon
            )
        }
    }
}