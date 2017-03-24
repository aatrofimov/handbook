package model

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
    var interval: Double = .0
        set(value) {
            validateNonNegative(value)
            field = value

    /**
     * Получение способа начисления зарплаты
     */
    abstract fun getSalaryType() : SalaryType

    /**
     * Расчет зарплаты за заданный интервал времени
     */
    abstract fun getSalaryInternal(duration: Double) : Double

    /**
     * Расчет зарплаты за заданный интервал времени
     */
    fun getSalary(duration: Double = interval): Double {
        validateIsPositive(duration)
        return getSalaryInternal(duration)
    }

}