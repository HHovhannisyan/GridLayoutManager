package com.example.gridlayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gridlayout.RecyclerViewAdapter.RecyclerViewHolder

internal class RecyclerViewAdapter(
    private val menuDataArrayList: ArrayList<RecyclerData>,
) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val recyclerData = menuDataArrayList[position]
        holder.menuName.text = recyclerData.menuName
        holder.count.text = recyclerData.count
    }

    override fun getItemCount(): Int {
        return menuDataArrayList.size
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuName: TextView
        val count: TextView

        init {
            menuName = itemView.findViewById(R.id.txt_menu_name)
            count = itemView.findViewById(R.id.txt_count)
        }
    }
}