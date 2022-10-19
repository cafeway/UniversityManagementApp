package com.example.i_sms.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.i_sms.R
import com.example.i_sms.models.unit

class UnitsAdapter (private val context: Context, private val units: ArrayList<unit>):
    RecyclerView.Adapter<UnitsAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val unit = itemView.findViewById<TextView>(R.id.unitTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =  layoutInflater.inflate(R.layout.unitsview,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.unit.text = units.get(position).unit
//        holder.done.text = applications.get(position).TotalClasses.toString()
//        holder.undone.text = applications.get(position).RemainingClasses.toString()

    }

    override fun getItemCount(): Int {
        return  units.size
    }
}