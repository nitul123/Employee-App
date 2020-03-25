package com.example.myapplication.dbhelper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insrtuser(vararg  user : User)

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid=:id ")
    fun loadSingle(id: String): List<User>

    @Query("SELECT COUNT(uid) FROM user ")
    fun getCount(): Int
}