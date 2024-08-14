import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submissiondicodingevent.R
import com.dicoding.submissiondicodingevent.data.response.ListEventsItem

class EventsAdapter(private val eventsList: List<ListEventsItem>) :
    RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_image, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventsList[position])
    }

    override fun getItemCount(): Int = eventsList.size

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventName: TextView = itemView.findViewById(R.id.event_name)  // Referensi ke TextView dengan ID event_name
        private val eventImage: ImageView = itemView.findViewById(R.id.item_image) // Referensi ke ImageView dengan ID item_image

        fun bind(event: ListEventsItem) {
            eventName.text = event.name  // Mengatur teks untuk TextView
            Glide.with(itemView.context)
                .load(event.mediaCover) // Memuat gambar dari URL
                .placeholder(R.drawable.ic_launcher_background)
                .into(eventImage) // Menempatkan gambar ke dalam ImageView
        }
    }
}
