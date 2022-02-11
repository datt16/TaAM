package io.github.datt16.taam

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.datt16.taam.databinding.FragmentClassDetailBinding
import io.github.datt16.taam.model.ClassEntity
import java.text.SimpleDateFormat
import java.util.*

class ClassDetailFragment : Fragment() {

    private val args: ClassDetailFragmentArgs by navArgs()

    private var _binding: FragmentClassDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var classDetailViewModel: ClassDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        classDetailViewModel = ViewModelProvider(
            this, ClassDetailViewModelFactory(
                activity?.application as Application, args.classId.toInt()
            )
        ).get(ClassDetailViewModel::class.java)

        _binding = FragmentClassDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(classDetailViewModel.classData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bind(classData: ClassEntity) {
        // UIに変数を適用する処理
        val memoText = "id: " + classData.id.toString()

        val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.JAPAN)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Tokyo")

        binding.classMemoValueTv.text = memoText
        binding.okTimesValueTv.text = classDetailViewModel.attendancePresenceCount.toString()
        binding.absenceTimesValueTv.text = classDetailViewModel.attendanceAbsenceCount.toString()
        binding.canceledTimesValueTv.text = classDetailViewModel.attendanceCanceledCount.toString()
        binding.classAttendanceLastDateTv.text = sdf.format(classDetailViewModel.lastAttendanceDate)
    }


}