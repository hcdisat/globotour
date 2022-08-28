package com.hcdisat.globotour.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hcdisat.globotour.R
import com.hcdisat.globotour.databinding.FragmentCityListBinding


class CityListFragment : Fragment() {

    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {
        val cityAdapter = CityAdapter(VacationSpots.cityList)
        binding.cityRecyclerView.apply {
            adapter = cityAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}