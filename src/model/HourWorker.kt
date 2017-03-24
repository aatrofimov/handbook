package model

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:48<br>
 * Работник с почасовой ставкой
 */
class HourWorker() : AbstractWorker() {
    constructor(hourRate: Double) : this() {
        this.hourRate = hourRate
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

    override fun getSalaryInternal(duration: Double): Double {
        return duration.times(hourRate)
    }
}