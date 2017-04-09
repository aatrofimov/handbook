package com.app.model

import com.google.common.base.*
import java.time.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:48<br>
 * Работник с почасовой ставкой
 */
class HourWorker(name: String, surname: String, beginDate: LocalDate) :
        AbstractWorker(name, surname, beginDate) {

    constructor(name: String, surname: String, beginDate: LocalDate, hourRate: Double, workTime: Double) : this(name, surname, beginDate) {
        this.hourRate = hourRate
        this.workTime = workTime
    }
    /**
     * Почасовая ставка
     */
    var hourRate: Double = .0
        set(value) {
            validateIsPositive(value)
            field = value
        }

    override fun getSalaryType(): SalaryType {
        return SalaryType.Hour
    }

    override fun getSalary(): Double = Math.floor(100 * hourRate * workTime) / 100

    override fun getRate(): Double = Math.floor(100 * hourRate) / 100

    override fun equals(other: Any?): Boolean {
        if (other !is HourWorker || this.javaClass != other.javaClass) {
            return false
        }
        return super.equals(other) && hourRate == other.hourRate&& workTime == other.workTime
    }

    override fun hashCode(): Int {
        return Objects.hashCode(name, surname, beginDate, hourRate, workTime)
    }
}