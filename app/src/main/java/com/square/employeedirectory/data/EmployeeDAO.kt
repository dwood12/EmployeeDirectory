package com.square.employeedirectory.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.square.employeedirectory.data.model.Employee

@Dao
interface EmployeeDAO {
    @Query("SELECT * FROM employee")
    fun getAll(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(employees: List<Employee>)
}
