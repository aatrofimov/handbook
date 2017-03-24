package model

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

}