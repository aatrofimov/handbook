package com.app.model

import com.google.common.base.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:30<br>
 * Абстрактный работник
 */
abstract class AbstractWorker {
    /**
     * Интервал времени для расчета
     */
    var effortInterval: Double = .0
        set(value) {
            validateNonNegative(value)
            field = value
        }

    /**
     * Получение способа начисления зарплаты
     */
    abstract fun getSalaryType() : SalaryType

    /**
     * Расчет зарплаты за заданный интервал времени
     */
    abstract fun getSalaryInternal(duration: Double) : Double

    /**
     * Коэффициент зарплаты
     */
    abstract fun getRate() : Double

    /**
     * Расчет зарплаты за заданный интервал времени
     */
    fun getSalary(duration: Double = effortInterval): Double {
        validateIsPositive(duration)
        return getSalaryInternal(duration)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AbstractWorker || !this.javaClass.equals(other.javaClass)) {
            return false
        }
        return effortInterval.equals(other.effortInterval)
    }

    override fun hashCode(): Int {
        return Objects.hashCode(effortInterval)
    }
}