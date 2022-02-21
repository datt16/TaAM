package io.github.datt16.taam

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.datt16.taam.databinding.FragmentClassListBinding
import io.github.datt16.taam.model.ClassEntity

class ClassListFragment : Fragment() {

    private var _binding: FragmentClassListBinding? = null
    private val binding get() = _binding!!

    private lateinit var classListViewModel: ClassListViewModel

    private var classList: List<ClassEntity>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        classListViewModel = ViewModelProvider(
            this,
            ClassListViewModelFactory(activity?.application as Application)
        ).get(ClassListViewModel::class.java)
        _binding = FragmentClassListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = binding.classListRecycleView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()

        val adapter = ClassListRecyclerViewAdapter(this.requireContext())
        recyclerView.adapter = adapter

        classListViewModel.allClasses.observe(this.requireActivity()) { cls ->
            cls?.let { adapter.setClass(it) }
        }


        binding.classListFab.setOnClickListener {
            findNavController().navigate(R.id.action_classListFragment_to_addClassFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun goDetailPage(){
        val intent = Intent(this.context, ClassDetailActivity::class.java)
        intent.putExtra("ID", 1)
        startActivity(intent)

    }
}
