package com.example.example_mvvm_recyclerview.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example_mvvm_recyclerview.Model.StudentGraduate
import com.example.example_mvvm_recyclerview.Model.StudentUnderGraduate

class StudentViewModel() : ViewModel() {

    private var _listStudent = MutableLiveData<MutableList<Any>>()
    val listStudent: LiveData<MutableList<Any>> get() = _listStudent

    init {
        _listStudent.value = addStudent()
    }


    private fun addStudent(): MutableList<Any> {
        val list = mutableListOf<Any>()
        list.add(StudentGraduate(1, "Android_01", "HN", 3.2f, "giỏi", 4.5f))
        list.add(StudentUnderGraduate(7, "Android_07", "HD", "9A"))
        list.add(StudentGraduate(2, "Android_02", "NB", 3.8f, "xuất xắc", 4.5f))
        list.add(StudentUnderGraduate(9, "Android_09", "HD", "9C"))
        list.add(StudentGraduate(3, "Android_03", "QN", 3.3f, "giỏi", 5.0f))
        list.add(StudentUnderGraduate(11, "Android_11", "HD", "8B"))
        list.add(StudentGraduate(4, "Android_04", "TB", 3.5f, "giỏi", 4.5f))
        list.add(StudentUnderGraduate(10, "Android_10", "HD", "8A"))
        list.add(StudentGraduate(5, "Android_05", "NĐ", 3.1f, "khá", 5.0f))
        list.add(StudentUnderGraduate(12, "Android_12", "HD", "8C"))
        list.add(StudentGraduate(6, "Android_06", "HD", 2.8f, "khá", 4.5f))
        list.add(StudentUnderGraduate(8, "Android_08", "HD", "9B"))
        return list
    }

    fun loadMore() {
        val list = mutableListOf<Any>()
        listStudent.value?.let {
            list.addAll(it)
        }
        val currentSize = _listStudent.value?.size ?: 0
        for (i in currentSize..currentSize + 20) {
            list.add(StudentUnderGraduate(i, "Android_0$i", "HD", "9B"))
        }
        _listStudent.value = list
    }

    fun refreshData(){
        _listStudent.value?.clear()
        _listStudent.value = _listStudent.value
        loadMore()
    }

}