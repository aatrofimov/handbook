package com.app.model

import com.google.common.base.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:53<br>
 * Работник со ставкой
 */
class WageWorker() : AbstractWorker() {
    constructor(wage: Double) : this() {
        this.wage = wage
    }

    /**
     * Ставка
     */
    var wage: Double = .0
        set(value) {
            validateIsPositive(value)
            field = value
        }

    override fun getSalaryType(): SalaryType {
        return SalaryType.Wage
    }

    override fun getSalaryInternal(duration: Double): Double {
        return duration.times(wage)
    }

    override fun getRate(): Double = wage

    override fun equals(other: Any?): Boolean {
        if (other !is WageWorker || !this.javaClass.equals(other.javaClass)) {
            return false
        }
        return super.equals(other) && wage.equals(other.wage)
    }

    override fun hashCode(): Int {
        return Objects.hashCode(effortInterval, wage)
    }
}