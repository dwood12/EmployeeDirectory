package com.square.employeedirectory.di

import android.content.Context
import androidx.room.Room
import com.square.employeedirectory.data.AppDatabase
import com.square.employeedirectory.data.EmployeeRepository
import com.square.employeedirectory.network.EmployeeApi
import com.square.employeedirectory.ui.EmployeeViewModel
import com.square.employeedirectory.usecase.GetEmployeeUseCase
import org.kodein.di.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://s3.amazonaws.com/sq-mobile-interview/"
private const val DATABASE_NAME = "app_db"

val networkModule = DI.Module("network") {
    bindSingleton<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    bindSingleton {
        instance<Retrofit>().create(EmployeeApi::class.java)
    }
}

val dataModule: (Context) -> DI.Module = {
    DI.Module("data") {
        bindSingleton {
            Room.databaseBuilder(
                it,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
        bindSingleton { instance<AppDatabase>().employeeDao() }
        bindSingleton { EmployeeRepository(employeeDAO = instance(), employeeApi = instance()) }
    }
}

val useCaseModule = DI.Module("useCase") {
    bindProvider {
        GetEmployeeUseCase(employeeRepository = instance())
    }
}

val viewModelModule = DI.Module("viewModel") {
    bindProvider { EmployeeViewModel(instance()) }
}