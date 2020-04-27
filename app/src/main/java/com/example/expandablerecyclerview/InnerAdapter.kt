package com.example.expandablerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.inner_row_item.view.*

class InnerAdapter(private val list: ArrayList<InnerDataModel>?) :
    RecyclerView.Adapter<InnerAdapter.MyViewHolder>() {


    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == 3) {
            holder.viewDivider?.visibility = View.INVISIBLE
        } else {
            holder.viewDivider?.visibility = View.VISIBLE
        }
        holder.txtName?.text = list?.get(position)?.name ?: ""
        holder.checkBox?.isChecked = list?.get(position)?.isChecked ?: false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.inner_row_item, parent, false)
        return MyViewHolder(view)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewDivider = itemView.viewDivider ?: null
        val txtName = itemView.txtName ?: null
        val checkBox = itemView.checkBox ?: null

        init {
            checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->
                if (buttonView.isPressed) {
                    val obj = list?.get(adapterPosition)
                    obj?.isChecked = isChecked
                    obj?.let { list?.set(adapterPosition, it) }
                    notifyDataSetChanged()
                }
            }
            txtName?.setOnClickListener {
                val obj = list?.get(adapterPosition)
                obj?.isChecked = !(obj?.isChecked ?: false)
                obj?.let { list?.set(adapterPosition, it) }
                notifyDataSetChanged()
            }
        }
    }
}