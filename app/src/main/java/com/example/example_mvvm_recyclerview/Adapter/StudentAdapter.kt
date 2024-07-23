package com.example.example_mvvm_recyclerview.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_mvvm_recyclerview.Model.StudentGraduate
import com.example.example_mvvm_recyclerview.Model.StudentUnderGraduate
import com.example.example_mvvm_recyclerview.databinding.LayoutItemStudentGraduateBinding
import com.example.example_mvvm_recyclerview.databinding.LayoutItemStudentUndergraduateBinding

class StudentAdapter(private val listStudent: List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val GRADUATE = 0
        private const val UNDER_GRADUATE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            UNDER_GRADUATE -> {
                val binding = LayoutItemStudentUndergraduateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                UnderGraduateViewHolder(binding)
            }

            GRADUATE -> {
                val binding = LayoutItemStudentGraduateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                GraduateViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (listStudent[position]) {
            is StudentUnderGraduate -> UNDER_GRADUATE
            is StudentGraduate -> GRADUATE
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    inner class GraduateViewHolder(private val binding: LayoutItemStudentGraduateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: StudentGraduate) {
            binding.txtName.text = student.name
            binding.txtAddress.text = student.address
            binding.txtDegree.text = student.degree
            binding.txtGpa.text = student.gpa.toString()
            binding.txtNumberStudy.text = student.numberStudy.toString()
        }
    }

    inner class UnderGraduateViewHolder(private val binding: LayoutItemStudentUndergraduateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: StudentUnderGraduate) {
            binding.txtName.text = student.name
            binding.txtAddress.text = student.address
            binding.txtClasses.text = student.classes
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UnderGraduateViewHolder -> holder.bind(listStudent[position] as StudentUnderGraduate)
            is GraduateViewHolder -> holder.bind(listStudent[position] as StudentGraduate)
        }
    }

    override fun getItemCount(): Int = listStudent.size
}