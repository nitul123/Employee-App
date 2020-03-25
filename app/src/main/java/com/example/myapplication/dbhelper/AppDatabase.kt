package com.example.myapplication.dbhelper

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(User::class)], version = 1)
 public abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

}