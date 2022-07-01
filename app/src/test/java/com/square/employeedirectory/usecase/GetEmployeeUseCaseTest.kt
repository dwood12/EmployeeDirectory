@file:OptIn(ExperimentalCoroutinesApi::class)

package com.square.employeedirectory.usecase

import com.square.employeedirectory.MainCoroutineRule
import com.square.employeedirectory.data.EmployeeRepository
import com.square.employeedirectory.data.testEmployee
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetEmployeeUseCaseTest {
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var respositoryMock: EmployeeRepository
    private lateinit var getEmployeeUseCase: GetEmployeeUseCase

    @Before
    fun setup() {
        respositoryMock = mockk()
        getEmployeeUseCase = GetEmployeeUseCase(respositoryMock, coroutineRule.testDispatcher)
    }

    @Test
    fun getEmployeeDirectory() = runTest {
        val employees = listOf(testEmployee)
        coEvery { respositoryMock.getEmployees() } returns employees

        val res = getEmployeeUseCase(EmployeeDirectoryType.EMPLOYEE)

        coVerify { respositoryMock.getEmployees() }

        assert(res.isSuccess)
        val ed = res.getOrNull()
        assert(ed != null && ed.isNotEmpty())
    }

    @Test
    fun getEmptyDirectory() = runTest {
        coEvery { respositoryMock.getEmptyEmployees() } returns listOf()

        val res = getEmployeeUseCase(EmployeeDirectoryType.EMPTY)

        coVerify { respositoryMock.getEmptyEmployees() }

        assert(res.isSuccess)
        val ed = res.getOrNull()
        assert(ed != null && ed.isEmpty())
    }

    @Test
    fun getMalformedDirectory() = runTest {
        coEvery { respositoryMock.getMalformedEmployees() } throws KotlinNullPointerException()

        val res = getEmployeeUseCase(EmployeeDirectoryType.MALFORMED)

        coVerify { respositoryMock.getMalformedEmployees() }
        assert(res.isFailure)
        val ed = res.getOrNull()
        assert(ed == null)
    }
}