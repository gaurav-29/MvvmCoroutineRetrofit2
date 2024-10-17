package com.example.mvvmcoroutineretrofit.roomDatabasePractice

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "colleges")
data class CollegeEntity(
    @PrimaryKey(autoGenerate = true)
    val collegeId: Long?,
    val collegeName: String,
    val degreeName: String,
    val city: String
)
