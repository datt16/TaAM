package io.github.datt16.taam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.datt16.taam.databinding.ClassListRecyclerViewItemBinding
import io.github.datt16.taam.model.ClassEntity

class ClassListRecyclerViewAdapter(private val classList: List<ClassEntity>) :
    RecyclerView.Adapter<ClassListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassListViewHolder {
        val view = ClassListRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ClassListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassListViewHolder, position: Int) {
        holder.bind(classList[position])
    }

    override fun getItemCount(): Int {
        return classList.size
    }
}

class ClassListViewHolder(private val binding: ClassListRecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cls: ClassEntity) {
        binding.TitleTv.text = cls.name
        binding.subTitleTv.text = cls.description
    }

}