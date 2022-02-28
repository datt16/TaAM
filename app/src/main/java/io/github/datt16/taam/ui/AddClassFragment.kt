package io.github.datt16.taam.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import io.github.datt16.taam.R
import io.github.datt16.taam.databinding.FragmentAddClassBinding


class AddClassFragment : Fragment() {

    private var _binding: FragmentAddClassBinding? = null
    private val binding get() = _binding!!

    private var newTitle: String = ""
    private var newDescription: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAddClassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveBtn.setOnClickListener {
            validate()
        }
    }

    // バリデーション
    private fun validate() {
        newTitle = binding.classNameEt.text.toString()
        newDescription = binding.classDescriptionEt.text.toString()

        if (TextUtils.isEmpty(newTitle)) {
            binding.classNameTil.error = "授業名を入力してください"
        } else {
            // 授業リスト画面に遷移
            findNavController().navigate(R.id.action_addClassFragment_to_classListFragment)
        }
    }
}