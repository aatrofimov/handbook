package com.app.model

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:10<br>
 * Способ начисления зарплаты
 */
enum class SalaryType(private val type: String) {

    /**
     * Почасовая
     */
    Hour("Почасовая"),

    /**
     * Ставка
     */
    Wage("Оклад");

    companion object {
        /**
         * Получение SalaryType по строке
         */
        fun toEnum(type: String) =
                when (type) {
                    "Почасовая" -> Hour
                    "Оклад" -> Wage
                    else -> null
                }
    }

    override fun toString() = type
}