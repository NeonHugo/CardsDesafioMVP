package com.nm.cardsdesafiomvp.ui.cardTypeList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nm.cardsdesafiomvp.R
import com.nm.domain.entity.Option
import com.nm.domain.entity.Section

class SectionAdapter(
    private var sections: List<Section>,
    private val context: Context,
    private val itemListener: (Option) -> Unit
) : RecyclerView.Adapter<SectionAdapter.ViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = layoutInflater.inflate(R.layout.single_section, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.recyclerView.adapter = OptionAdapter(context, sections[position].options, itemListener)
        holder.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        holder.recyclerView.setHasFixedSize(true)
        holder.tvHeading.text = sections[position].description
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var recyclerView: RecyclerView = itemView.findViewById<View>(R.id.rvOptions) as RecyclerView
        var tvHeading: TextView = itemView.findViewById<View>(R.id.tvSectionName) as TextView
    }

}