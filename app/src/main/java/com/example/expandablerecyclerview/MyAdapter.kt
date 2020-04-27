package com.example.expandablerecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.outer_row_item.view.*

class MyAdapter(private val list: ArrayList<MyDataModel>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var context: Context? = null
    override fun getItemCount(): Int = list.size

    private var previousPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        if (context == null) {
            context = parent.context
        }
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.outer_row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (list[position].isExpanded) {
            holder.rvView?.apply {
                visibility = View.VISIBLE
            }
            holder.imageExpandCollapse?.rotation = 270F
        } else {
            holder.rvView?.apply {
                visibility = View.GONE
            }
            holder.imageExpandCollapse?.rotation = 90F
        }
        holder.txtHead?.text=list[position].tag
        holder.rvView?.apply {
            tag=list[position].tag
            this.layoutManager = LinearLayoutManager(context)
            adapter = InnerAdapter(list[position].innerList)
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageExpandCollapse = itemView.imageExpandCollapse ?: null
        val rvView = itemView.rvView ?: null
        val txtHead=itemView.txtHead?:null
        init {
            itemView.setOnClickListener {
                if (previousPosition != -1 && previousPosition!=adapterPosition) {
                    val obj = list[previousPosition]
                    obj.isExpanded=false
                    list[previousPosition] = obj
                }
                previousPosition = adapterPosition
                val obj = list[adapterPosition]
                obj.isExpanded=!list[adapterPosition].isExpanded
                list[adapterPosition] = obj
                notifyDataSetChanged()
            }
        }
    }
}