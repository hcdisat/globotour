package com.hcdisat.globotour.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hcdisat.globotour.city.City
import com.hcdisat.globotour.city.VacationSpots
import com.hcdisat.globotour.databinding.FragmentFavoriteBinding
import java.util.*


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favAdapter = FavoritesAdapter(VacationSpots.favoriteCityList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        setupFavoriteList()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupFavoriteList() {
        binding.favRecycler.apply {
            adapter = favAdapter
            setHasFixedSize(true)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            targetViewHolder: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = targetViewHolder.adapterPosition

            Collections.swap(VacationSpots.favoriteCityList, fromPosition, toPosition)
            favAdapter.notifyItemMoved(fromPosition, toPosition)

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // remove favorite from list
            viewHolder.adapterPosition.run {
                val city = VacationSpots.favoriteCityList[this]
                val cityIndex = VacationSpots.cityList.indexOf(city)
                deleteCity(cityIndex, this)

                // show snack bar with an undo button
                Snackbar.make(binding.favRecycler, "Deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                       addCity(city, cityIndex, this)
                    }.show()
            }
        }
    })

    private fun deleteCity(cityIndex: Int, favoriteIndex: Int) {
        VacationSpots.cityList.removeAt(cityIndex)
        VacationSpots.favoriteCityList.removeAt(favoriteIndex)

        favAdapter.notifyItemRemoved(favoriteIndex)
        favAdapter.notifyItemRangeChanged(
            favoriteIndex,
            VacationSpots.favoriteCityList.size
        )
    }

    private fun addCity(city: City, cityListIndex: Int, favoriteIndex: Int) {
        VacationSpots.cityList.add(cityListIndex, city)
        VacationSpots.favoriteCityList.add(favoriteIndex, city)

        favAdapter.notifyItemInserted(favoriteIndex)
        favAdapter.notifyItemRangeChanged(
            favoriteIndex,
            VacationSpots.favoriteCityList.size
        )
    }
}
