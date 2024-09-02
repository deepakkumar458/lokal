package com.example.lokal.roomDb
import androidx.room.TypeConverter
import com.example.lokal.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromPrimaryDetails(primaryDetails: PrimaryDetails): String {
        return gson.toJson(primaryDetails)
    }

    @TypeConverter
    fun toPrimaryDetails(primaryDetailsString: String): PrimaryDetails {
        return gson.fromJson(primaryDetailsString, PrimaryDetails::class.java)
    }

    @TypeConverter
    fun fromFeeDetails(feeDetails: FeeDetails): String {
        return gson.toJson(feeDetails)
    }

    @TypeConverter
    fun toFeeDetails(feeDetailsString: String): FeeDetails {
        return gson.fromJson(feeDetailsString, FeeDetails::class.java)
    }

    @TypeConverter
    fun fromJobTags(jobTags: List<JobTag>): String {
        return gson.toJson(jobTags)
    }

    @TypeConverter
    fun toJobTags(jobTagsString: String): List<JobTag> {
        val type = object : TypeToken<List<JobTag>>() {}.type
        return gson.fromJson(jobTagsString, type)
    }

    @TypeConverter
    fun fromContactPreference(contactPreference: ContactPreference): String {
        return gson.toJson(contactPreference)
    }

    @TypeConverter
    fun toContactPreference(contactPreferenceString: String): ContactPreference {
        return gson.fromJson(contactPreferenceString, ContactPreference::class.java)
    }

    @TypeConverter
    fun fromCreatives(creatives: List<Creative>): String {
        return gson.toJson(creatives)
    }

    @TypeConverter
    fun toCreatives(creativesString: String): List<Creative> {
        val type = object : TypeToken<List<Creative>>() {}.type
        return gson.fromJson(creativesString, type)
    }

    @TypeConverter
    fun fromLocations(locations: List<Location>): String {
        return gson.toJson(locations)
    }

    @TypeConverter
    fun toLocations(locationsString: String): List<Location> {
        val type = object : TypeToken<List<Location>>() {}.type
        return gson.fromJson(locationsString, type)
    }

    @TypeConverter
    fun fromContentV3(contentV3: ContentV3): String {
        return gson.toJson(contentV3)
    }

    @TypeConverter
    fun toContentV3(contentV3String: String): ContentV3 {
        return gson.fromJson(contentV3String, ContentV3::class.java)
    }
}
