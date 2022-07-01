package com.square.employeedirectory.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.square.employeedirectory.data.AppDatabase.Companion.VERSION
import com.square.employeedirectory.data.model.Employee

@Database(entities = [Employee::class], version = VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDAO
    companion object {
        const val VERSION = 1
    }
}