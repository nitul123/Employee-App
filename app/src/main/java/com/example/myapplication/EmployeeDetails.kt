package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.myapplication.dbhelper.AppDatabase
import kotlinx.android.synthetic.main.activity_employee_details.*

class EmployeeDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)
        setTitle("Employee Details")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        var elementID = intent.getStringExtra("elementID")
       // Toast.makeText(this,elementID, Toast.LENGTH_SHORT).show()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "userDB"
        ).build()


        Thread{
           val empDetails=  db.userDao().loadSingle(elementID)

            val name = empDetails.get(0).employeeName
            val age = empDetails.get(0).employeeAge
            val sallary = empDetails.get(0).employeeSallary

            textView.setText("Name - ${name}")
            textView1.setText("Age - ${age}")
            textView2.setText("Sallary - ${sallary}")
        }.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
