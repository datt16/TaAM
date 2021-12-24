package io.github.datt16.taam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.datt16.taam.model.ClassEntity

class ClassListRecyclerViewAdapter(private val classList: List<ClassEntity>) :
    RecyclerView.Adapter<ClassListRecyclerViewAdapter.ClassListViewHolder>() {

    class ClassListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.Title_Tv)
        val subTitleTv: TextView = itemView.findViewById(R.id.sub_title_Tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassListViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.class_list_recycler_view_item, parent, false)
        return ClassListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClassListViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.Title_Tv).text = classList[position].name
        holder.itemView.findViewById<TextView>(R.id.sub_title_Tv).text =
            classList[position].description
    }

    override fun getItemCount(): Int {
        return classList.size
    }

}