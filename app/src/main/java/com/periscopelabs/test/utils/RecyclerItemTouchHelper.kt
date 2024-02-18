package com.periscopelabs.test.utils

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.periscopelabs.test.adapter.RoomInspectionAdapter

class RecyclerItemTouchHelper(
    context: Context,
    private val adapter: RoomInspectionAdapter
) : ItemTouchHelper.Callback() {


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {

        val flags = ItemTouchHelper.START or ItemTouchHelper.END

        return makeMovementFlags(0, flags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false // We don't want support moving items up/down
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.START) {
            adapter.onSwipedLeft(viewHolder.adapterPosition, viewHolder)
        } else if (direction == ItemTouchHelper.END) {
           // adapter.onSwipedRight(viewHolder.adapterPosition)
        }
        adapter.notifyItemChanged(viewHolder.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

}