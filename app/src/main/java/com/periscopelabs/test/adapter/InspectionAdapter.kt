package com.periscopelabs.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.periscopelabs.test.databinding.ItemInspectionsBinding
import com.periscopelabs.test.models.InspectionModel
import com.periscopelabs.test.ui.listeners.InspectionItemClickListeners

class InspectionAdapter(val context: Context, val inspection: List<InspectionModel>, val listeners: InspectionItemClickListeners): RecyclerView.Adapter<InspectionAdapter.ViewHolder>() {
    class ViewHolder(itemView: ItemInspectionsBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemInspectionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return inspection.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = inspection[position].title
        holder.binding.tvDesc.text = inspection[position].desc
        holder.binding.tvDate.text = inspection[position].date

        holder.itemView.setOnClickListener {
            listeners.onItemCLick(inspection[position])
        }
    }

}