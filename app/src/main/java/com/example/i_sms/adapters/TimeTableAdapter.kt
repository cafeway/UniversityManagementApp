package com.example.i_sms.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.i_sms.R
import com.example.i_sms.models.Timetable
import com.google.android.material.chip.Chip

class TimeTableAdapter (private  val context: Context,private val applications: ArrayList<Timetable>):
    RecyclerView.Adapter<TimeTableAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val day = itemView.findViewById<TextView>(R.id.venue)
        val unitCode = itemView.findViewById<TextView>(R.id.unitCode)
        val Lecturer_venue = itemView.findViewById<TextView>(R.id.lecturer)
        val done = itemView.findViewById<TextView>(R.id.DoneClasses)
        val undone = itemView.findViewById<TextView>(R.id.RemainingClasses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =  layoutInflater.inflate(R.layout.timetableview,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.day.text = applications.get(position).Venue
        holder.unitCode.text = applications.get(position).UnitCode
        holder.Lecturer_venue.text = applications.get(position).Lecturer.toString()
        holder.done.text = applications.get(position).TotalClasses.toString()
        holder.undone.text = applications.get(position).RemainingClasses.toString()

    }

    override fun getItemCount(): Int {
        return  applications.size
    }
}