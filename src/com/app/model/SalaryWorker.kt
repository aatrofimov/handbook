package com.app.model

import com.google.common.base.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:51<br>
 * Работник с окладом
 */
class SalaryWorker() : AbstractWorker() {
    constructor(salary: Double) : this() {
        this.salary = salary
    }

    /**
     * Оклад
     */
    var salary: Double = .0
        set(value) {
            validateIsPositive(value)
            field = value
        }

    override fun getSalaryType(): SalaryType {
        return SalaryType.Salary
    }

    override fun getSalaryInternal(duration: Double): Double {
        return duration.times(salary)
    }

    override fun getRate(): Double = salary

    override fun equals(other: Any?): Boolean {
        if (other !is SalaryWorker || !this.javaClass.equals(other.javaClass)) {
            return false
        }
        return super.equals(other) && salary.equals(other.salary)
    }

    override fun hashCode(): Int {
        return Objects.hashCode(effortInterval, salary)
    }
}