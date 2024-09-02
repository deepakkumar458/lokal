package com.example.lokal.repo

import com.example.lokal.model.JobResponse
import com.example.lokal.network.ApiService
import com.example.lokal.utils.ApiState

class JobsRepo(private val mApiService : ApiService) {

    suspend fun getJobs( page : Int): ApiState<JobResponse> {
        return try {
            val response = mApiService.getJobData(page)
            if (response.isSuccessful) {
                ApiState.Success(response.body()!!)
            } else {
                ApiState.Error("Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            ApiState.Error(e.message ?: "Unknown Error")
        }
    }

}