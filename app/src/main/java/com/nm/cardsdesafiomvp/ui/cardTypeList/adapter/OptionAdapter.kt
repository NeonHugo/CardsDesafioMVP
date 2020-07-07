package com.nm.cardsdesafiomvp.ui.cardTypeList.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nm.cardsdesafiomvp.R
import com.nm.cardsdesafiomvp.ui.cardTypeList.adapter.OptionAdapter.CustomViewHolder
import com.nm.domain.entity.Option
import java.util.*

class OptionAdapter(
    private val context: Context,
    private val options: ArrayList<Option>,
    private val itemListener: (Option) -> Unit
) : RecyclerView.Adapter<CustomViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view: View = inflater.inflate(R.layout.single_option, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val (_, description) = options[position]
        holder.tvOptionName.text = description
        holder.cardView.setCardBackgroundColor(colorP())
    }

    override fun getItemCount(): Int {
        return options.size
    }

    private fun colorP(): Int {
        val rnd = Random()
        return Color.argb(
            255, rnd.nextInt(255), rnd.nextInt(255),
            rnd.nextInt(255)
        )
    }

    inner class CustomViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvOptionName: TextView = itemView.findViewById(R.id.tvOptionName)
        var cardView: CardView = itemView.findViewById(R.id.card_view)

        init {
            itemView.rootView.setOnClickListener {
                val position = adapterPosition
                val item = options!![position]
                itemListener.invoke(item)
            }
        }
    }
}
