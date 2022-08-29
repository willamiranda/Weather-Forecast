package utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.weatherforecast.R


class BindingUtils {
    companion object {

        @JvmStatic
        @BindingAdapter("bind:glideLoad")
        fun loadImageView(image: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {

              /*GlideApp.with(image)
                  .load("https:${url}")
                   .error(R.drawable.ic_sunny_cloud)
                    .into(image)
 */           }
        }

        @JvmStatic
        fun convertFloatToInt(value: Float): Int {
            return value.toInt()
        }

    }
}