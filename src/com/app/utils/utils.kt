package com.app.utils

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:38<br>
 * Утилиты
 */

/**
 * Проверка на положительное значение
 */
fun validateNonNegative(value: Double, errorMes: String = "Значение должно быть больше или равно нулю!") {
    if (value < 0) {
        throw IllegalArgumentException(errorMes)
    }
}

/**
 * Проверка на неотрицательность
 */
fun validateIsPositive(value: Double, errorMes: String = "Значение должно быть больше нуля!") {
    if (value <= 0) {
        throw IllegalArgumentException(errorMes)
    }
}

fun validateWorkTime(value: Double, errorMes: String = "Величина отработанного времени не должна быть больше 250") {
    validateIsPositive(value)
    if (value > 250) {
        throw IllegalArgumentException(errorMes)
    }
}

fun validateWorkTimeNorm(value: Double, errorMes: String = "Величина нормы рабочего времени не должна быть больше 192") {
    validateIsPositive(value)
    if (value > 192) {
        throw IllegalArgumentException(errorMes)
    }
}
