package com.example.lokal.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lokal.R
import com.example.lokal.adapter.JobsAdapter
import com.example.lokal.databinding.FragmentBookmarkBinding
import com.example.lokal.model.Job
import com.example.lokal.roomDb.AppDatabase
import com.example.lokal.roomDb.dao.JobDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Bookmark : Fragment() {

    private lateinit var mBinding: FragmentBookmarkBinding
    private lateinit var database: AppDatabase
    private lateinit var jobDao: JobDao
    private lateinit var adapter: JobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize database and DAO
        database = AppDatabase.getDatabase(requireContext())
        jobDao = database.jobDao()

        // Initialize the adapter with an empty list and set it to RecyclerView
        adapter = JobsAdapter(requireContext()) { job ->

        }
        mBinding.recView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.recView.adapter = adapter

        // Fetch and display the data from local storage
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = jobDao.getAllJobs()
                withContext(Dispatchers.Main) {
                    if (data.isNotEmpty()) {
                        // Update the adapter with the fetched data
                        adapter.submitList(data)
                    } else {
                        Toast.makeText(requireContext(), "No jobs available", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                // Handle exception
                withContext(Dispatchers.Main) {
                    Log.e(ContentValues.TAG, "Error fetching data: ${e.message}")
                    Toast.makeText(requireContext(), "Error fetching data: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
