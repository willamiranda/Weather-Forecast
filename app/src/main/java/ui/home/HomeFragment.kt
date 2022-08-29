package ui.home

import android.os.Bundle
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil

import base.ScopedFragment

import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.HomeFragmentBinding
import data.db.entity.History
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import utils.NetworkResponse
import utils.PreferenceHelp.getLocation
import java.util.*

class HomeFragment : ScopedFragment() {

    private val viewModel by viewModel<HomeViewModel>()
    lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.home_fragment,
                container, false
            )

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        binding.nextDaysTitle.text =
            String.format(getString(R.string.today_city), getLocation(requireActivity()))
    }

    private fun setupObservers() {

        viewModel.getCurrentWeather(requireContext(), getLocation(requireContext()))
            .observe(requireActivity(), {
                it?.let { resource ->
                    when (resource) {
                        is NetworkResponse.Success -> {
                            binding.progressBar.visibility = View.GONE
                            viewModel.currentWeather.value = resource.data

                            viewModel.addHistory(
                                History(
                                    getLocation(requireContext()),
                                    resource.data.current.temp_c,
                                    getCurrentTime().toString()
                                )
                            )
                        }
                        is NetworkResponse.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), resource.exception, Toast.LENGTH_LONG)
                                .show()
                        }
                        is NetworkResponse.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            })
    }

    private fun getCurrentTime(): Date {
        return Calendar.getInstance().time
    }


}