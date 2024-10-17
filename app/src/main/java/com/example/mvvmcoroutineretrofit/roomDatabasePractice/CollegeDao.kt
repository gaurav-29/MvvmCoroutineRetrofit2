package com.example.mvvmcoroutineretrofit.roomDatabasePractice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CollegeDao {

    @Insert
    suspend fun insertCollege(college: CollegeEntity)

    @Query("Select * from colleges")
    suspend fun gelAllColleges(): List<CollegeEntity>

    @Update
    suspend fun updateCollege(users: CollegeEntity)

    @Delete
    suspend fun deleteCollege(users: CollegeEntity)
}