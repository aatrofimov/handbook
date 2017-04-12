package com.app.junit

import com.app.model.*
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.*
import org.junit.runners.*
import java.time.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 28.03.2017<br>
 * Time: 18:45<br>
 * unit tests
 */
@RunWith(Parameterized::class)
class WorkerFactoryTest(
        val type: SalaryType,
        val name: String,
        val surname: String,
        val beginDate: LocalDate,
        val endDate: LocalDate?,
        val rate: Double,
        val workTime: Double,
        val workTimeNorm: Double?,
        val salary: Double,
        val message: String,
        val worker: AbstractWorker) {

    companion object {
        val hourWorkerParams = arrayOf(
                SalaryType.Hour, "user1", "user1", LocalDate.now(), null, 200.0, 160.0, .0, Math.floor(100 * 200.0 * 160.0) / 100, "create HourWorkerTest")
        val wageWorkerParams = arrayOf(
                SalaryType.Wage, "user2", "user2", LocalDate.now(), null, 200.0, 150.0, 170.0, Math.floor(100 * 200.0 * 150.0 / 170.0) / 100, "create HourWorkerTest")

        @JvmStatic
        @Parameterized.Parameters
        fun testCases() = listOf(
                hourWorkerParams + HourWorker(
                        hourWorkerParams[1] as String,
                        hourWorkerParams[2] as String,
                        hourWorkerParams[3] as LocalDate,
                        hourWorkerParams[5] as Double,
                        hourWorkerParams[6] as Double),
                wageWorkerParams + WageWorker(
                        wageWorkerParams[1] as String,
                        wageWorkerParams[2] as String,
                        wageWorkerParams[3] as LocalDate,
                        wageWorkerParams[5] as Double,
                        wageWorkerParams[6] as Double,
                        wageWorkerParams[7] as Double))
    }

    @Test
    fun testIsValidWorkers() = assertEquals(message, worker, WorkerFactory.create(type, name, surname, beginDate, endDate, rate, workTime, workTimeNorm))

    /**
     *  @see AbstractWorker.endDate
     */
    @Test(expected = IllegalArgumentException::class)
    fun testIsInvalidEndDate() {
        WorkerFactory.create(type, name, surname, beginDate, beginDate, rate, workTime, workTimeNorm)
    }

    /**
     *  @see HourWorker.hourRate
     *  @see WageWorker.wage
     */
    @Test(expected = IllegalArgumentException::class)
    fun testIsInvalidRate() {
        WorkerFactory.create(type, name, surname, beginDate, endDate, -1.0, workTime, workTimeNorm)
    }

    /**
     *  @see HourWorker.workTime
     *  @see WageWorker.workTime
     */
    @Test(expected = IllegalArgumentException::class)
    fun testIsInvalidWorkTime() {
        WorkerFactory.create(type, name, surname, beginDate, endDate, rate, 251.0, workTimeNorm)
    }

    /**
     *  @see WageWorker.workTimeNorm
     */
    @Test(expected = IllegalArgumentException::class)
    fun testIsInvalidWorkTimeNorm() {
        WorkerFactory.create(SalaryType.Wage, name, surname, beginDate, endDate, rate, workTime, 193.0)
    }

    /**
     *  @see AbstractWorker.name
     */
    @Test(expected = IllegalArgumentException::class)
    fun testIsInvalidName() {
        WorkerFactory.create(type, "", surname, beginDate, endDate, rate, workTime, workTimeNorm)
    }

    /**
     *  @see AbstractWorker.surname
     */
    @Test(expected = IllegalArgumentException::class)
    fun testIsInvalidSurname() {
        WorkerFactory.create(type, name, "", beginDate, endDate, rate, workTime, workTimeNorm)
    }

    /**
     * @see AbstractWorker.getSalary
     */
    @Test
    fun isValidGetSalary() {
        assertEquals("getSalaryTest", salary, worker.getSalary(), 0.0)
    }
}