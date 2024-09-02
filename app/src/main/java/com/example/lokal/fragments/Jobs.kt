package com.example.lokal.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.lokal.R
import com.example.lokal.adapter.JobsAdapter
import com.example.lokal.databinding.FragmentJobsBinding
import com.example.lokal.model.Job
import com.example.lokal.network.ApiService
import com.example.lokal.repo.JobsRepo
import com.example.lokal.roomDb.AppDatabase
import com.example.lokal.roomDb.dao.JobDao
import com.example.lokal.utils.ApiState
import com.example.lokal.utils.RetrofitClient
import com.example.lokal.viewModel.JobsViewModel
import com.example.lokal.viewModelFactory.JobsViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class Jobs : Fragment() {

    private lateinit var mBinding: FragmentJobsBinding
    private lateinit var viewModel: JobsViewModel
    private lateinit var database: AppDatabase
    private lateinit var jobDao: JobDao
    private lateinit var adapter: JobsAdapter
    private var currentPage = 1
    private val pageSize = 20 // Define your page size
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentJobsBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize database and DAO
        database = AppDatabase.getDatabase(requireContext())
        jobDao = database.jobDao()

        // Create ApiService and Repository
        val mApi = RetrofitClient.retrofit.create(ApiService::class.java)
        val jobsRepo = JobsRepo(mApi)

        // Create ViewModel using ViewModelProvider and ViewModelFactory
        val viewModelFactory = JobsViewModelFactory(jobsRepo)
        viewModel = ViewModelProvider(this, viewModelFactory).get(JobsViewModel::class.java)

        adapter = JobsAdapter(requireContext()) { job ->
            saveJobToDatabase(job)
        }
        mBinding.recView.adapter = adapter

        // Observe the LiveData
        viewModel.jobData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ApiState.Loading -> {
                    isLoading = true
                    mBinding.progressBar.visibility = View.VISIBLE
                }
                is ApiState.Success -> {
                    isLoading = false
                    mBinding.progressBar.visibility = View.GONE
                    appendDataToAdapter(state.data.results)
                }
                is ApiState.Error -> {
                    isLoading = false
                    mBinding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        // Fetch the first page of data
        viewModel.fetchJobData(currentPage)

        // Set up scroll listener for pagination
        mBinding.recView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    // User scrolled to the bottom, load next page
                    currentPage++
                    viewModel.fetchJobData( currentPage)
                }
            }
        })
    }


    private fun appendDataToAdapter(newData: List<Job>) {
        val currentList = adapter.currentList.toMutableList()
        currentList.addAll(newData)
        adapter.submitList(currentList)
    }

    private fun saveJobToDatabase(job: Job) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                jobDao.insert(job)
                Log.e(TAG, "saveJobToDatabase: successfully")
            } catch (e: Exception) {
                // Handle exception
                withContext(Dispatchers.Main) {
                    Log.e(TAG, "saveJobToDatabase: ${e.message}")
                    Toast.makeText(requireContext(), "${e.message}..", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
