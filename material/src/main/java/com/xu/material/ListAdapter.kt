package com.xu.material

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/8/7 15:51
 * @version 2.2
 */
class ListAdapter(var data: Array<String>) : RecyclerView.Adapter<ListAdapter.VH>() {
    private var onItemClickListener:OnItemClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return VH(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.item.text = data[position]
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item by lazy { itemView.findViewById<MaterialTextView>(R.id.item) }
    }
    fun setOnItemClickListener(listener:OnItemClickListener?){
        onItemClickListener = listener
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}