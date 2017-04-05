package com.app.junit

import org.junit.*
import org.junit.Assert.*
import org.junit.runner.*
import org.junit.runners.*
import com.app.model.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 28.03.2017<br>
 * Time: 18:45<br>
 * unit tests
 */
@RunWith(Parameterized::class)
class WorkerFactoryTest(val type: SalaryType, val rate: Double, val effortInterval: Double, val message: String, val worker: AbstractWorker) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testCases() = listOf(
                arrayOf(SalaryType.Hour, 10, -1, "HourWorker", HourWorker(10.0)),
                arrayOf(SalaryType.Salary, 10, -1, "SalaryWorker", SalaryWorker(10.0)),
                arrayOf(SalaryType.Wage, 10, -1, "WageWorker", WageWorker(10.0))
        )
    }
    @Test
    fun testIsValidWorkers() = assertEquals(message, worker, WorkerFactory.create(type, rate, effortInterval))

}