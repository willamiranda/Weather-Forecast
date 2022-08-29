package ui.nextweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.NextWeatherFragmentBinding
import com.example.weatherforecast.databinding.NextWeatherItemBinding
import data.network.Forecast
import data.network.ForecastResponse

class NextWeatherAdapter (private val dataSet: ForecastResponse) :
    RecyclerView.Adapter<NextWeatherAdapter.ViewHolder>() {

    lateinit var binding: NextWeatherItemBinding

    class ViewHolder(private val binding: NextWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: Forecast) {
            binding.dateTv.text = forecast.date

            val x = forecast.day.maxtemp_c
            binding.temperatureTv.text = "${forecast.day.maxtemp_c.toInt()} / ${forecast.day.mintemp_c.toInt()}"

     /*       GlideApp.with(binding.conditionIcon)
                .load("https:${forecast.day.condition.icon}")
                .error(R.drawable.ic_sunny_cloud)
                .into(binding.conditionIcon)*/
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context), R.layout.next_weather_item,
                viewGroup, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val forecast = dataSet.forecast.forecastday[position]
        viewHolder.bind(forecast)
    }

    override fun getItemCount() = dataSet.forecast.forecastday.size
}
