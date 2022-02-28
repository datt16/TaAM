package io.github.datt16.taam.ui.attendance

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import io.github.datt16.taam.databinding.FragmentAddAttendanceBinding
import io.github.datt16.taam.ui.classdetail.ClassDetailViewModel

class AddAttendanceFragment : Fragment() {

    private var _binding: FragmentAddAttendanceBinding? = null
    private val binding get() = _binding!!

    val model: ClassDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("AAF:onCreateView", model.hashCode().toString())
        setHasOptionsMenu(true)
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAddAttendanceBinding.inflate(inflater, container, false)
        return binding.root
    }
}
