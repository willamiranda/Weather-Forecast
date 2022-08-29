package ui.history


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.HistoryFragmentBinding
import data.db.entity.History
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastHistoryFragment: Fragment() {

    private val viewModel by viewModel<ForecastHistoryViewModel>()
    lateinit var binding: HistoryFragmentBinding
    private lateinit var adapter: ForecastHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.history_fragment,
                container, false
            )
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHistory()
        setupRecyclerView()

        viewModel.listHistory.observe(viewLifecycleOwner, { history ->
            setupRecyclerViewAdapter(history)
            binding.groupLoading.visibility = View.GONE
        })
    }

    private fun setupRecyclerView() {
        binding.forecastHistoryRv.layoutManager = LinearLayoutManager(requireContext())
        binding.forecastHistoryRv.setHasFixedSize(true)
    }

    private fun setupRecyclerViewAdapter(history: List<History>) {
        adapter = ForecastHistoryAdapter(history)
        binding.forecastHistoryRv.adapter = adapter
    }
}
