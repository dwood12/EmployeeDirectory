package com.square.employeedirectory.data

import com.square.employeedirectory.MainCoroutineRule
import com.square.employeedirectory.network.EmployeeApi
import com.square.employeedirectory.network.EmployeesDTO
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EmployeeRepositoryTest {
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var employeeApiMock: EmployeeApi
    private lateinit var employeeRepository: EmployeeRepository

    @Before
    fun setUp() {
        employeeApiMock = mockk()
        employeeRepository = EmployeeRepository(employeeApiMock)
    }

    @Test
    fun getEmployees() = runTest {
        coEvery { employeeApiMock.getEmployees() } returns testEmployeesDTO

        val employees = employeeRepository.getEmployees()

        coVerify { employeeApiMock.getEmployees() }
        assert(employees == listOf(testEmployee))
    }

    @Test(expected = KotlinNullPointerException::class)
    fun getMalformedEmployees() = runTest {
        coEvery { employeeApiMock.getMalformedEmployees() } throws KotlinNullPointerException()
        employeeRepository.getMalformedEmployees()
    }

    @Test
    fun getEmptyEmployees() = runTest {
        coEvery { employeeApiMock.getEmptyEmployees() } returns EmployeesDTO(emptyList())

        val employees = employeeRepository.getEmptyEmployees()

        coVerify { employeeApiMock.getEmptyEmployees() }
        assert(employees.isEmpty())
    }
}