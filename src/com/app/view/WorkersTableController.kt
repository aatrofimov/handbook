package com.app.view

import com.app.model.*
import javafx.beans.property.*
import javafx.fxml.*
import javafx.scene.control.*

/**
 * Таблица со списком работников
 * @author TrofimovAA, 11.04.2017 14:40
 */
open class WorkersTableController {

    /**
     * Таблица работников
     */
    @FXML
    open lateinit var workersTable: TableView<AbstractWorker>

    /**
     * Колонка "Имя"
     * @see AbstractWorker.name
     */
    @FXML
    private lateinit var nameCol: TableColumn<AbstractWorker, String>

    /**
     * Колонка "Фамилия"
     * @see AbstractWorker.surname
     */
    @FXML
    private lateinit var surnameCol: TableColumn<AbstractWorker, String>

    /**
     * Колонка "Дата начала работы"
     * @see AbstractWorker.beginDate
     */
    @FXML
    private lateinit var beginDateCol: TableColumn<AbstractWorker, String>

    /**
     * Колонка "Дата окончания работы"
     * @see AbstractWorker.endDate
     */
    @FXML
    private lateinit var endDateCol: TableColumn<AbstractWorker, String>

    /**
     * Колонка "Тип зарплаты"
     * @see AbstractWorker.getSalaryType
     */
    @FXML
    private lateinit var salaryTypeCol: TableColumn<AbstractWorker, String>

    /**
     * Колонка "Ставка"
     * @see HourWorker.hourRate
     * @see WageWorker.wage
     */
    @FXML
    private lateinit var rateCol: TableColumn<AbstractWorker, Number>

    /**
     * Колонка "Интервал"
     * @see AbstractWorker.workTime
     */
    @FXML
    private lateinit var workTimeCol: TableColumn<AbstractWorker, Number>

    /**
     * Колонка "Норма рабочего времени"
     * @see WageWorker.workTimeNorm
     */
    @FXML
    private lateinit var workTimeNormCol: TableColumn<AbstractWorker, String>

    /**
     * Колонка "Зарплата"
     * @see AbstractWorker.getSalary
     */
    @FXML
    private lateinit var salaryCol: TableColumn<AbstractWorker, Number>

    /**
     * Инициализация контроллера. Этот метод вызывается автоматически после загрузки fxml
     */
    @FXML
    open fun initialize() {
        nameCol.setCellValueFactory { SimpleStringProperty(it.value.name) }
        surnameCol.setCellValueFactory { SimpleStringProperty(it.value.surname) }
        beginDateCol.setCellValueFactory { SimpleStringProperty(it.value.beginDate.toString()) }
        endDateCol.setCellValueFactory { SimpleStringProperty(it.value.endDate?.toString() ?: "") }
        salaryTypeCol.setCellValueFactory { SimpleStringProperty(it.value.getSalaryType().toString()) }
        rateCol.setCellValueFactory { SimpleDoubleProperty(it.value.getRate()) }
        workTimeCol.setCellValueFactory { SimpleDoubleProperty(it.value.workTime) }
        workTimeNormCol.setCellValueFactory {
            SimpleStringProperty(
                    (it.value as? WageWorker)?.workTimeNorm?.toString() ?: ""
            )
        }
        salaryCol.setCellValueFactory { SimpleDoubleProperty(it.value.getSalary()) }
    }
}