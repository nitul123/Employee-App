package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.myapplication.dbhelper.AppDatabase
import com.example.myapplication.dbhelper.User
import com.example.myapplication.helpers.Common
import com.example.myapplication.helpers.JsonPlaceHolderApi
import com.example.myapplication.model.Data
import com.example.myapplication.model.EmployeeM
import com.example.myapplication.model.User_Model
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var mService : JsonPlaceHolderApi
    val list: MutableList<Int> = ArrayList()
    val listName: MutableList<String> = ArrayList()
    val listID: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Employee List")
              /*  mService = Common.retrofitService
                getAllData1()*/

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "userDB"
        ).build()




       Thread {

           db.userDao().getAll().forEach {
               list.add(it.uid)
           }

       }.start()
           val retrofit = Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .baseUrl("http://dummy.restapiexample.com/api/v1/")
               .build()

           val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
           val myCall: Call<EmployeeM> =jsonPlaceHolderApi.getEmployeeLists();
           myCall.enqueue(object :Callback<EmployeeM>
           {
               override fun onFailure(call: Call<EmployeeM>, t: Throwable) {
                   //println("error---- "+t.message.toString())
               }

               override fun onResponse(
                   call: Call<EmployeeM>,
                   response: Response<EmployeeM>
               ) {
                   var employees = User()
                   Thread{
                   //println("sucess "+ response.body()?.data?.size )
                   for (name in response.body()?.data!!)
                   {
                       if(name.id in list)
                       {}else
                       {
                           employees.employeeName = name.employeeName
                           employees.employeeAge = name.employeeAge
                           employees.uid = name.id
                           employees.employeeSallary = name.employeeSalary

                           db.userDao().insrtuser(employees)
                       }

                   }

               }.start()
               }

           })

        Thread{
            db.userDao().getAll().forEach {
                listName.add(it.employeeName.toString())
                listID.add(it.uid)
            }
        }.start()

        var arrayAdapter: ArrayAdapter<String>
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, listName)
        userlist.adapter = arrayAdapter

        userlist.setOnItemClickListener { parent, view, position, id ->
            val element = listName.get(position) // The item that was clicked
            val elementID = listID.get(position)
            //Toast.makeText(this,element+"  "+elementID,Toast.LENGTH_SHORT).show()


            val intent = Intent(this@MainActivity, EmployeeDetails::class.java)
            intent.putExtra("elementID",""+elementID)
            startActivity(intent)
        }
    }



   /*private fun getAllData1() {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val myCall: Call<List<EmployeeM>> =jsonPlaceHolderApi.getEmployeeLists();
        myCall.enqueue(object :Callback<List<EmployeeM>>
        {
            override fun onFailure(call: Call<List<EmployeeM>>, t: Throwable) {
                println("error"+t.message.toString())
            }

            override fun onResponse(
                call: Call<List<EmployeeM>>,
                response: Response<List<EmployeeM>>
            ) {
                val Employeess: List<EmployeeM> =response.body()!!

                for (emp in Employeess){
                    println("name is-------------- ${emp.status}")
                }
            }

        })

    }*/

   private fun getAllData() {

       val retrofit = Retrofit.Builder()
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl("http://dummy.restapiexample.com/api/v1/")
           .build()

       val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
       val myCall: Call<EmployeeM> =jsonPlaceHolderApi.getEmployeeLists();
       myCall.enqueue(object :Callback<EmployeeM>
       {
           override fun onFailure(call: Call<EmployeeM>, t: Throwable) {
               println("error---- "+t.message.toString())
           }

           override fun onResponse(
               call: Call<EmployeeM>,
               response: Response<EmployeeM>
           ) {
               //println("sucess "+ response.body()?.data?.size )
               for (name in response.body()?.data!!)
               {

                   print("Name "+name.employeeName)
                   print(" Age "+name.employeeAge)
                   print(" Sallary "+name.employeeSalary)
                   println(" ID "+name.id)
               }
           }

       })

   }




    //method for read records from database in ListView
   /* fun viewRecord(view: View){
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        val empArraySallary = Array<String>(emp.size){"null"}
        val empArrayAge = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArraySallary[index] = e.userSallery
            empArrayAge[index] = e.userAge
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = MyListAdapter(this,empArrayId,empArrayName,empArraySallary,empArrayAge)
       // listView.adapter = myListAdapter
    }*/
}
