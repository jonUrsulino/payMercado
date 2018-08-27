package mercado.test.jonathan.paymercado

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.View
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class RecyclerAdapter(val context: Context, val item: List<PaymentType>, val function: (PaymentType) -> Unit): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    companion object {
        const val TAG = "RecyclerAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")
        holder.name.text = item[position].name
        val url = item[position].thumbnail
        Picasso.with(context)
                .load(url)
                .placeholder(android.R.drawable.ic_menu_info_details)
                .error(android.R.drawable.ic_menu_close_clear_cancel)
                .into(holder.icon)
        holder.itemView.setOnClickListener {
            function.invoke(item[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name) as TextView
        var icon: ImageView = itemView.findViewById(R.id.icon) as ImageView
    }
}