package com.example.lokal.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lokal.databinding.ActivityJobDetailsBinding

class JobDetails : AppCompatActivity() {

    private lateinit var mBinding:ActivityJobDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityJobDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        getBundleExtras()


        mBinding.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getBundleExtras() {
        mBinding.expTv.text = intent.getStringExtra("expTv")
        mBinding.locationTv.text = intent.getStringExtra("locationTv")
        mBinding.titleTv.text = intent.getStringExtra("title")
        mBinding.salaryTv.text = intent.getStringExtra("salaryTv")
        mBinding.qualificatonTv.text = intent.getStringExtra("qualificationTv")
        mBinding.contentTv.text = intent.getStringExtra("content")
    }
}