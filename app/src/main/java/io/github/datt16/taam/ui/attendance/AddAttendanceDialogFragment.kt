package io.github.datt16.taam.ui.attendance

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import io.github.datt16.taam.databinding.FragmentAddAttendanceBinding
import io.github.datt16.taam.model.AttendanceEntity
import io.github.datt16.taam.ui.classdetail.ClassDetailViewModel

class AddAttendanceDialogFragment : DialogFragment() {

    private var _binding: FragmentAddAttendanceBinding? = null
    private val binding: FragmentAddAttendanceBinding get() = _binding!!

    val TAG = "AddAttendanceDialogFragment"

    val model: ClassDetailViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentAddAttendanceBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this
        binding.model = this.model

        binding.presenceButton.setOnClickListener {
            model.addAttendance(AttendanceEntity.ok, model.nextClassIndex)
//            model.loadData()
            dismiss()
        }

        binding.absenceButton.setOnClickListener {
            model.addAttendance(AttendanceEntity.absence, model.nextClassIndex)
//            model.loadData()
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        val builder = AlertDialog.Builder(activity)
        val view = binding.root

        builder.setView(view)

        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
