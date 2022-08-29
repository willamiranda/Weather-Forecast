package ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.HistoryItemBinding
import data.db.entity.History

class ForecastHistoryAdapter(private val dataSet: List<History>) :
    RecyclerView.Adapter<ForecastHistoryAdapter.ViewHolder>() {

    lateinit var binding: HistoryItemBinding

    class ViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.textCityApi.text = history.region
            binding.temp.text = "" + history.temp
            binding.textHiDateTv.text = history.date
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context), R.layout.history_item,
                viewGroup, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val forecast = dataSet[position]
        viewHolder.bind(forecast)
    }

    override fun getItemCount() = dataSet.size
}
