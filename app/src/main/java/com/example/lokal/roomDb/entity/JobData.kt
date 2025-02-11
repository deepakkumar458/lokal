package com.example.lokal.roomDb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.lokal.model.*
import com.example.lokal.roomDb.Converters
import com.google.gson.annotations.SerializedName


@Entity(tableName = "job")
@TypeConverters(Converters::class) // Handle nested objects and lists
data class JobData(

    @PrimaryKey(autoGenerate = true) @SerializedName("id")
    val id: Int = 0,

    @SerializedName("title")
    val title: String,

    @SerializedName("type")
    val type: Int,

    @SerializedName("primary_details")
    val primaryDetails: PrimaryDetails,

    @SerializedName("fee_details")
    val feeDetails: FeeDetails,

    @SerializedName("job_tags")
    val jobTags: List<JobTag>,

    @SerializedName("job_type")
    val jobType: Int,

    @SerializedName("job_category_id")
    val jobCategoryId: Int,

    @SerializedName("qualification")
    val qualification: Int,

    @SerializedName("experience")
    val experience: Int,

    @SerializedName("shift_timing")
    val shiftTiming: Int,

    @SerializedName("job_role_id")
    val jobRoleId: Int,

    @SerializedName("salary_max")
    val salaryMax: Int,

    @SerializedName("salary_min")
    val salaryMin: Int,

    @SerializedName("city_location")
    val cityLocation: Int,

    @SerializedName("locality")
    val locality: Int,

    @SerializedName("premium_till")
    val premiumTill: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("company_name")
    val companyName: String,

    @SerializedName("advertiser")
    val advertiser: Int,

    @SerializedName("button_text")
    val buttonText: String,

    @SerializedName("custom_link")
    val customLink: String,

    @SerializedName("amount")
    val amount: String,

    @SerializedName("views")
    val views: Int,

    @SerializedName("shares")
    val shares: Int,

    @SerializedName("fb_shares")
    val fbShares: Int,

    @SerializedName("is_bookmarked")
    val isBookmarked: Boolean,

    @SerializedName("is_applied")
    val isApplied: Boolean,

    @SerializedName("is_owner")
    val isOwner: Boolean,

    @SerializedName("updated_on")
    val updatedOn: String,

    @SerializedName("whatsapp_no")
    val whatsappNo: String,

    @SerializedName("contact_preference")
    val contactPreference: ContactPreference,

    @SerializedName("created_on")
    val createdOn: String,

    @SerializedName("is_premium")
    val isPremium: Boolean,

    @SerializedName("creatives")
    val creatives: List<Creative>,

    @SerializedName("locations")
    val locations: List<Location>,

    @SerializedName("contentV3")
    val contentV3: ContentV3,

    @SerializedName("status")
    val status: Int,

    @SerializedName("expire_on")
    val expireOn: String,

    @SerializedName("job_hours")
    val jobHours: String,

    @SerializedName("openings_count")
    val openingsCount: Int,

    @SerializedName("job_role")
    val jobRole: String,

    @SerializedName("other_details")
    val otherDetails: String,

    @SerializedName("job_category")
    val jobCategory: String,

    @SerializedName("num_applications")
    val numApplications: Int,

    @SerializedName("enable_lead_collection")
    val enableLeadCollection: Boolean,

    @SerializedName("is_job_seeker_profile_mandatory")
    val isJobSeekerProfileMandatory: Boolean,

    @SerializedName("job_location_slug")
    val jobLocationSlug: String,

    @SerializedName("fees_text")
    val feesText: String,

    @SerializedName("question_bank_id")
    val questionBankId: Int?,

    @SerializedName("screening_retry")
    val screeningRetry: Int?,

    @SerializedName("should_show_last_contacted")
    val shouldShowLastContacted: Boolean,

    @SerializedName("fees_charged")
    val feesCharged: Int
)

