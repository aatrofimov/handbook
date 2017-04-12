package com.app.model

import java.time.*

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
     *  @name Имя рабонтика
     *  @surname Фамилия работника
     *  @beginDate дата начала работы
     *  @endDate дата окончания работы
     *  @rate ставка или почасовая ставка
     *  @workTime количество отработанных часов
     *  @workTimeNorm норма часов в месяц
     *  @return AbstractWorker
     */
    fun create(
            type: SalaryType,
            name: String,
            surname: String,
            beginDate: LocalDate,
            endDate: LocalDate? = null,
            rate: Double,
            workTime: Double,
            WorkTimeNorm: Double? = null): AbstractWorker {
        val worker: AbstractWorker
        when (type) {
            SalaryType.Hour -> worker = HourWorker(name, surname, beginDate, rate, workTime)
            SalaryType.Wage -> worker = WageWorker(name, surname, beginDate, rate, workTime, WorkTimeNorm!!)
        }
        worker.endDate = endDate
        return worker
    }
}