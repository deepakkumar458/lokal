package com.example.lokal.roomDb.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lokal.model.Job

@Dao
interface JobDao {
    @Insert
    suspend fun insert(job: Job)

    @Query("SELECT * FROM job WHERE id = :jobId")
    suspend fun getJobById(jobId: Int): Job?

    @Query("DELETE FROM job WHERE id = :jobId")
    suspend fun deleteJobById(jobId: Int)

    @Query("SELECT * FROM job")
    suspend fun getAllJobs(): List<Job>

}
