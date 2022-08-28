package com.hcdisat.globotour.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hcdisat.globotour.city.City
import com.hcdisat.globotour.databinding.ListItemFavoriteBinding

class FavoritesAdapter(
    private val favorites: List<City>
): RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(
            ListItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) =
        holder.bind(favorites[position])

    override fun getItemCount(): Int = favorites.size

    inner class FavoriteViewHolder(
        private val binding: ListItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) {
            binding.imvCity.setImageResource(city.imageId)
            binding.txvCityName.text = city.name
        }
    }
}