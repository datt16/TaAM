package io.github.datt16.taam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.datt16.taam.databinding.FragmentClassDetailBinding
import io.github.datt16.taam.model.ClassEntity

class ClassDetailFragment : Fragment() {

    private val args: ClassDetailFragmentArgs by navArgs()

    private var _binding: FragmentClassDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var classListViewModel: ClassListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        classListViewModel = ViewModelProvider(
            this,
            ClassListViewModelFactory(activity?.application as Application)
        ).get(ClassListViewModel::class.java)

        _binding = FragmentClassDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val targetId = args.classId
        classListViewModel.allClasses.observe(this.requireActivity(), { cls ->
            cls?.let { list -> bind(list.filter { it.id == targetId.toInt() }) }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bind(classData: List<ClassEntity>) {
        binding.headerTitleTv.text = classData[0].name
        binding.memoTv.text = classData[0].description
    }


}