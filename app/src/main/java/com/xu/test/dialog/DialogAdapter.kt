package com.xu.test.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xu.test.R

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/8/7 13:56
 * @version 2.2
 */
class DialogAdapter(var layoutId:Int,var data:List<String>):RecyclerView.Adapter<DialogAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogAdapter.Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId,parent,false)
        return Holder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv.text = data[position]
    }
    inner class Holder( itemView:View): RecyclerView.ViewHolder(itemView) {
        var tv:TextView = itemView.findViewById(R.id.tv)
    }
}