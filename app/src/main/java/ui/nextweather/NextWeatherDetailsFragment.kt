
package ui.nextweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.NextWeatherFragmentBinding
import data.network.ForecastResponse
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import utils.NetworkResponse
import utils.PreferenceHelp.getLocation

class NextWeatherDetailsFragment: Fragment() {private val viewModel by viewModel<NextWeatherDetailsViewModel>()
    lateinit var binding: NextWeatherFragmentBinding

    private lateinit var adapter: NextWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.next_weather_fragment,
                container, false
            )

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.nextDaysRv.layoutManager = LinearLayoutManager(requireContext())
        binding.nextDaysRv.setHasFixedSize(true)
    }

    private fun setupRecyclerViewAdapter(nextWeather: ForecastResponse) {
        adapter = NextWeatherAdapter(nextWeather)
        binding.nextDaysRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getNextWeather(requireContext(), getLocation(requireContext())).observe(requireActivity(), {
            it?.let { resource ->
                when (resource) {
                    is NetworkResponse.Success -> {
                        binding.groupLoading.visibility = View.GONE
                        setupRecyclerViewAdapter(resource.data)
                    }
                    is NetworkResponse.Error -> {
                        binding.groupLoading.visibility = View.GONE
                        Toast.makeText(requireContext(), resource.exception, Toast.LENGTH_LONG)
                            .show()
                    }
                    is NetworkResponse.Loading -> {
                        binding.groupLoading.visibility = View.VISIBLE
                    }
                }
            }
        })
}}
