package com.periscopelabs.test.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.periscopelabs.test.R
import com.periscopelabs.test.databinding.ItemRoomInspectionBinding
import com.periscopelabs.test.models.InspectionsRoomModel
import com.periscopelabs.test.ui.images.ImagesActivity
import com.periscopelabs.test.ui.listeners.RoomInspectionItemListener

class RoomInspectionAdapter(val context: Context, val inspectionData: List<InspectionsRoomModel>, val listeners: RoomInspectionItemListener): RecyclerView.Adapter<RoomInspectionAdapter.ViewHolder>() {
    class ViewHolder(itemView: ItemRoomInspectionBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRoomInspectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return inspectionData.size
    }

    fun onSwipedLeft(adapterPosition: Int, viewHolder: RecyclerView.ViewHolder, ) {
        viewHolder.itemView.findViewById<ConstraintLayout>(R.id.btn_view).visibility =
            View.VISIBLE
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tv1.text = inspectionData[position].title

        when(inspectionData[position].colorStatus){
            1-> {
                holder.binding.vStatusColor.setBackgroundColor(context.getColor(R.color.greenish))
            }
            2->{
                holder.binding.vStatusColor.setBackgroundColor(context.getColor(R.color.gray_100))
            }
            3->{
                holder.binding.vStatusColor.setBackgroundColor(context.getColor(R.color.orange_100))
            }
            4->{
                holder.binding.vStatusColor.setBackgroundColor(context.getColor(R.color.red_100))
            }
        }

        holder.binding.mainItem.setOnClickListener {
            context.startActivity(Intent(context, ImagesActivity::class.java))
        }

        holder.binding.btnClose.setOnClickListener {
            listeners.onStatusButtonClick(position, 4)
        }

        holder.binding.btnMinus.setOnClickListener {
            listeners.onStatusButtonClick(position, 3)
        }

        holder.binding.btnOff.setOnClickListener {
            listeners.onStatusButtonClick(position, 2)
        }

        holder.binding.btnOk.setOnClickListener {
            listeners.onStatusButtonClick(position, 1)
        }

    }

}