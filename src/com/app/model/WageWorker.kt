package com.app.model

import com.google.common.base.*
import java.time.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:53<br>
 * Работник со ставкой
 */
class WageWorker(name: String, surname: String, beginDate: LocalDate) :
        AbstractWorker(name, surname, beginDate) {

    constructor(name: String, surname: String, beginDate: LocalDate, wage: Double, workTime: Double, workTimeNorm: Double) :
            this(name, surname, beginDate) {
        this.wage = wage
        this.workTime = workTime
        this.workTimeNorm = workTimeNorm
    }

    /**
     * Норма рабочего времени в текущем месяце
     */
    var workTimeNorm = .0
        set(value) {
            validateWorkTimeNorm(value)
            field = value
        }

    /**
     * Оклад
     */
    var wage: Double = .0
        set(value) {
            validateIsPositive(value)
            field = value
        }

    override fun getSalary(): Double = Math.floor(100 * wage * workTime / workTimeNorm) / 100

    override fun getSalaryType(): SalaryType {
        return SalaryType.Wage
    }

    override fun getRate(): Double = Math.floor(100 * wage) / 100

    override fun equals(other: Any?): Boolean {
        if (other !is WageWorker || this.javaClass != other.javaClass) {
            return false
        }
        return super.equals(other) && workTimeNorm == other.workTimeNorm && workTime == other.workTime && wage == other.wage
    }

    override fun hashCode(): Int {
        return Objects.hashCode(workTimeNorm, workTime, wage)
    }
}