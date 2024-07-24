package com.example.example_mvvm_recyclerview.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.example_mvvm_recyclerview.Adapter.StudentAdapter
import com.example.example_mvvm_recyclerview.R
import com.example.example_mvvm_recyclerview.ViewModel.StudentViewModel
import com.example.example_mvvm_recyclerview.databinding.FragmentStudentBinding


class StudentFragment : Fragment() {

    private var _binding: FragmentStudentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StudentViewModel by viewModels()

    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listStudent.observe(viewLifecycleOwner) { item ->
            binding.rvStudent.apply {
                adapter = StudentAdapter(item)
                layoutManager = LinearLayoutManager(context)
                addOnScrollListener(object : RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                        if (!isLoading && (visibleItemCount + pastVisibleItems) >= totalItemCount){
                            isLoading = true
                            viewModel.loadMore()
                        }
                        else{
                            isLoading = false
                        }
                    }
                })
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}