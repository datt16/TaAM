package io.github.datt16.taam

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.datt16.taam.data.AppDatabase
import io.github.datt16.taam.databinding.FragmentClassListBinding
import io.github.datt16.taam.model.ClassEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClassListFragment : Fragment() {

    private var _binding: FragmentClassListBinding? = null
    private val binding get() = _binding!!


    private val sampleData: List<ClassEntity> = listOf(
        ClassEntity(0, "TEST1", "this is test data."),
        ClassEntity(1, "TEST2", "this is test data."),
        ClassEntity(2, "TEST3", "this is test data.")
    )

    private var classList: List<ClassEntity>? = null

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
//        setSampleData()
//        getClassList()
        super.onViewCreated(view, savedInstanceState)

        binding.classListRecycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
//            adapter = ClassListRecyclerViewAdapter(classList!!)
            adapter = ClassListRecyclerViewAdapter(sampleData)
        }

        binding.classListFab.setOnClickListener {
            findNavController().navigate(R.id.action_classListFragment_to_addClassFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setSampleData() {
//        AppDatabase.getInstance()?.let { db ->
//            val dao = db.classDao()
//            dao.saveClass(sampleData[0])
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
//        }
    }

    private fun getClassList() {
//        AppDatabase.getInstance()?.let { db ->
//            val dao = db.classDao()
//            dao.loadAllClasses().flatMapCompletable { setClassList(it) }
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe()
//        }
    }

//    private fun setClassList(cls: List<ClassEntity>): Completable {
//        this.classList = cls
//        return Completable.complete()
//    }
}
