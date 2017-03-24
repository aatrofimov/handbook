package model

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
}