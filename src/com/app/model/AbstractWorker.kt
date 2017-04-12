package com.app.model

import com.google.common.base.*
import java.time.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:30<br>
 * Абстрактный работник
 */
abstract class AbstractWorker() {
    /**
     * Имя работника
     */
    var name: String = ""
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Имя не может быть пустым")
            }
            field = value
        }

    /**
     * Фамилия работника
     */
    var surname: String = ""
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Фамилия не может быть пустой")
            }
            field = value
        }

    /**
     * Дата начала работы
     */
    var beginDate: LocalDate = LocalDate.now()

    constructor(name: String, surname: String, beginDate: LocalDate) : this() {
        this.name = name
        this.surname = surname
        this.beginDate = beginDate
    }

    /**
     * дата окончания работы (
     */
    var endDate: LocalDate? = null
        set(value) {
            if (value != null && value.isBefore(beginDate) || value == beginDate) {
                throw IllegalArgumentException("Дата окончания работы не может быть раньше даты начала")
            }
        }

    /**
     * Количество отработанных часов
     */
    var workTime: Double = .0
        set(value) {
            validateWorkTime(value)
            field = value
        }

    /**
     * Получение способа начисления зарплаты
     */
    abstract fun getSalaryType(): SalaryType

    /**
     * Расчет зарплаты за заданный интервал времени
     */
    abstract fun getSalary(): Double

    /**
     * Ставка для текущей зарплаты
     */
    abstract fun getRate(): Double

    override fun equals(other: Any?): Boolean {
        if (other !is AbstractWorker || this.javaClass != other.javaClass) {
            return false
        }
        return name == other.name && surname == other.surname && beginDate == other.beginDate && endDate == other.endDate
    }

    override fun hashCode(): Int {
        return Objects.hashCode(name, surname, beginDate, endDate)
    }
}