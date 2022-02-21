package io.github.datt16.taam

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
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

    @SuppressLint("NotifyDataSetChanged")
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
            val intent = Intent(it.context, ClassDetailActivity::class.java)
            intent.putExtra("ID", id)
            it.context.startActivity(intent)
        }
        binding.TitleTv.text = cls.name
        binding.subTitleTv.text = cls.description
    }
}
