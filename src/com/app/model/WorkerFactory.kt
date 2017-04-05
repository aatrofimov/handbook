package com.app.model

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
     *  @rate Коэффициент зарплаты
     *  @effortInterval Интервал времени (опционально)
     *  @return AbstractWorker
     */
    fun create(type: SalaryType, rate: Double, effortInterval: Double = -.1): AbstractWorker {
        val worker: AbstractWorker
        when (type) {
            SalaryType.Hour -> worker = HourWorker(rate)
            SalaryType.Salary -> worker = SalaryWorker(rate)
            SalaryType.Wage -> worker = WageWorker(rate)
        }
        if (effortInterval >= 0) {
            worker.effortInterval = effortInterval
        }
        return worker
    }
}