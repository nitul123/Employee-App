package com.example.myapplication.dbhelper

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class User {
   @PrimaryKey var uid: Int = 0
   @ColumnInfo(name = "employeeName")
    var employeeName: String? =null
    @ColumnInfo(name = "employeeAge")
    var employeeAge: String? = null
    @ColumnInfo(name = "employeeSallary")
    var employeeSallary: String? = null
}
