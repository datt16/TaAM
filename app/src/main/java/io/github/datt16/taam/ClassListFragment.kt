package io.github.datt16.taam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.datt16.taam.databinding.FragmentClassListBinding
import io.github.datt16.taam.model.ClassEntity

class ClassListFragment : Fragment() {

    private var _binding: FragmentClassListBinding? = null
    private val binding get() = _binding!!


    private val sampleData: List<ClassEntity> = listOf(
        ClassEntity(0, "this is test data.", "TEST1"),
        ClassEntity(1, "this is test data.", "TEST2"),
        ClassEntity(2, "this is test data.", "TEST3")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentClassListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.classListRecycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = ClassListRecyclerViewAdapter(sampleData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
