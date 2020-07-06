package com.nm.cardsdesafiomvp.ui.cardList.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nm.cardsdesafiomvp.R
import com.nm.domain.entity.Card
import java.util.*

class CardAdapter(
    private val context: Context,
    private val cards: List<Card>,
    private val itemListener: (Card) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val mGlide: RequestManager = Glide.with(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = inflater.inflate(R.layout.card_item, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]

        mGlide.load(card.img)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .into(holder.ivThumb)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    private fun colorP(): Int {
        val rnd = Random()
        return Color.argb(
            255, rnd.nextInt(255), rnd.nextInt(255),
            rnd.nextInt(255)
        )
    }

    inner class CardViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var ivThumb: ImageView = itemView.findViewById(R.id.ivThumb)

        init {
            itemView.rootView.setOnClickListener {
                val position = adapterPosition
                val item = cards!![position]
                itemListener.invoke(item)
            }
        }
    }
}
