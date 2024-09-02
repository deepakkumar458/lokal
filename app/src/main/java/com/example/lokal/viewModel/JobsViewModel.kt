package com.example.lokal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokal.model.JobResponse
import com.example.lokal.repo.JobsRepo
import com.example.lokal.utils.ApiState
import kotlinx.coroutines.launch

class JobsViewModel (private val repository: JobsRepo) : ViewModel() {

    private val _jobData = MutableLiveData<ApiState<JobResponse>>()
    val jobData: LiveData<ApiState<JobResponse>> get() = _jobData

    fun fetchJobData(page: Int) {
        viewModelScope.launch {
            _jobData.value = ApiState.Loading
            val result = repository.getJobs(page)
            _jobData.value = result
        }
    }
}