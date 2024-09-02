package com.example.lokal.adapter

import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lokal.activity.JobDetails
import com.example.lokal.databinding.JobsSingleRowLayoutBinding
import com.example.lokal.model.Job

class JobsAdapter(
     val mContext :Context,
    private val onItemClick: (Job) -> Unit
) : ListAdapter<Job, JobsAdapter.JobViewHolder>(JobDiffCallback()) {

    inner class JobViewHolder(private val binding: JobsSingleRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(job: Job) {
            with(binding) {
                titleTv.text = job.title ?: ""
                expTv.text = job.experience?.toString()?.let { " : $it" } ?: "N/A"
                salaryTv.text = job.primaryDetails?.salary?.let { " : $it" } ?: "N/A"
                locationTv.text = job.primaryDetails?.place?.let { " : $it" } ?: "N/A"
                qualificatonTv.text = job.primaryDetails?.qualification?.let { " : $it" } ?: "N/A"

                addToBookmarkIv.setOnClickListener {
                    onItemClick(job)
                }

                mainCl.setOnClickListener {
                    val intent = Intent(mContext, JobDetails::class.java).apply {
                        putExtra("isFromServer", "yes")
                        putExtra("title" , job.title)
                        putExtra("expTv" , job.experience?.toString())
                        putExtra("salaryTv" , job.primaryDetails?.salary?.toString())
                        putExtra("locationTv" , job.primaryDetails?.place)
                        putExtra("qualificationTv" , job.primaryDetails?.qualification)
                        putExtra("content" , job.content)
                    }
                    mContext.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = JobsSingleRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class JobDiffCallback : DiffUtil.ItemCallback<Job>() {
    override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem == newItem
    }
}
