package model

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:10<br>
 * Способ начисления зарплаты
 */
enum class SalaryType {

    /**
     * Почасовая
     */
    Hour,

    /**
     * Оклад
     */
    Salary,

    /**
     * Ставка
     */
    Wage
}