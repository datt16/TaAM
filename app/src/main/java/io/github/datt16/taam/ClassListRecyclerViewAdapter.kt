package io.github.datt16.taam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.datt16.taam.databinding.ClassListRecyclerViewItemBinding
import io.github.datt16.taam.model.ClassEntity

class ClassListRecyclerViewAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<ClassListViewHolder>() {

    private var classList: List<ClassEntity> = emptyList()

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

    internal fun setClass(clsList: List<ClassEntity>) {
        this.classList = clsList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return classList.size
    }
}

class ClassListViewHolder(private val binding: ClassListRecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cls: ClassEntity) {
        val id = cls.id

        binding.cardViewLayout.setOnClickListener {
            val action = ClassListFragmentDirections.actionClassListFragmentToClassDetailFragment(id.toLong())
            findNavController(it).navigate(action)
        }
        binding.TitleTv.text = cls.name
        binding.subTitleTv.text = cls.description
    }
}
