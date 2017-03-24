package model

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:29<br>
 * factory для создания работников
 */
object WorkerFactory {
    /**
     *  Создаёт экземпляр работника
     *  @type Тип зарплаты
     *  @value Коэффициент зарплаты
     *  @interval Интервал времени (опционально)
     */
    fun create(type: SalaryType, value: Double, interval: Double = -.1): AbstractWorker {
        val worker: AbstractWorker
        when (type) {
            SalaryType.Hour -> worker = HourWorker(value)
            SalaryType.Salary -> worker = SalaryWorker(value)
            SalaryType.Wage -> worker = WageWorker(value)
        }
        if (interval >= 0) {
            worker.interval = interval
        }
        return worker
    }
}