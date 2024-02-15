package com.example.horosctest.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toBitmapOrNull
import androidx.recyclerview.widget.RecyclerView
import com.example.horosctest.R
import com.example.horosctest.activities.activities.MainActivity
import data.Horoscope


class CustomAdapter(private var  listHoroscope: List<Horoscope>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Proporcina una referencia a los tipos de views que puede usar
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView:ImageView
        var textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            imageView= view.findViewById(R.id.horoImage)
            textView = view.findViewById(R.id.horoText)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_horoscope, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //TODO: Falta enlazar con los datos
        val context:Context = viewHolder.itemView.context

        viewHolder.imageView.setImageBitmap(context.getDrawable(listHoroscope[position].img)
            ?.toBitmap() ?: null )
        viewHolder.textView.text = context.getString(listHoroscope[position].name)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = listHoroscope.size
}

