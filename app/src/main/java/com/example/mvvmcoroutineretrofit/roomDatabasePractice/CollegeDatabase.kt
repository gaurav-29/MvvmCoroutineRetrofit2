package com.example.mvvmcoroutineretrofit.roomDatabasePractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CollegeEntity::class], version = 1)
abstract class CollegeDatabase: RoomDatabase() {

    abstract fun collegeDao(): CollegeDao

    companion object{
        @Volatile   private var inastance: CollegeDatabase? =null
        private val LOCK = Any()

        operator  fun invoke(context: Context): CollegeDatabase {
            return inastance ?: synchronized(LOCK){
                inastance ?: Builddatabase(context).also {
                    inastance=it
                }
            }
        }
        private fun Builddatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,CollegeDatabase::class.java,"collegeDB").build()
    }
}