package com.example.lokal.network

import com.example.lokal.model.JobResponse
import retrofit2.Response
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("common/jobs")
    suspend fun getJobData(
        @Query("page") page: Int
    ): Response<JobResponse>
}