package com.app.view

import com.app.*
import com.app.model.*
import javafx.beans.property.*
import javafx.collections.*
import javafx.fxml.*
import javafx.scene.control.*
import javafx.stage.*


/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 27.03.2017<br>
 * Time: 10:51<br>
 * Контроллер формы поиска
 */
class SearchFormController {
    /**
     * Текстовое поле поиска
     */
    @FXML
    private lateinit var searchField: TextField

    /**
     * Таблица с результатами поиска
     */
    @FXML
    private lateinit var workersTable: TableView<AbstractWorker>

    /**
     * Колонка "Тип зарплаты"
     * @see AbstractWorker.getSalaryType
     */
    @FXML
    private lateinit var salaryTypeCol: TableColumn<AbstractWorker, String>

    /**
     * Колонка "Коэффициент зарплаты"
     * @see AbstractWorker.getRate
     */
    @FXML
    private lateinit var rateCol: TableColumn<AbstractWorker, Number>

    /**
     * Колонка "Интервал"
     * @see AbstractWorker.effortInterval
     */
    @FXML
    private lateinit var effortIntervalCol: TableColumn<AbstractWorker, Number>

    /**
     * Колонка "Зарплата"
     * @see AbstractWorker.getSalary
     */
    @FXML
    private lateinit var salaryCol: TableColumn<AbstractWorker, Number>

    /**
     * Ссылка на контейнер для текущей формы поиска
     */
    lateinit var dialogStage: Stage

    /**
     * Ссылка на основной класс приложения
     * @see MainApp
     */
    var mainApp: MainApp? = null
        set(value) {
            field = value
            workersTable.items.setAll(FXCollections.observableArrayList<AbstractWorker>(value!!.workersData))
        }

    /**
     * Инициализация контроллера. Этот метод вызывается автоматически после загрузки fxml
     */
    @FXML
    private fun initialize() {
        salaryTypeCol.setCellValueFactory { SimpleStringProperty(it.value.getSalaryType().toString()) }
        //rateCol.setCellValueFactory { SimpleDoubleProperty(it.value.getRate()) }
       // effortIntervalCol.setCellValueFactory { SimpleDoubleProperty(it.value.effortInterval) }
        salaryCol.setCellValueFactory { SimpleDoubleProperty(it.value.getSalary()) }
    }

    /**
     * Обработчик кнопки поиска
     */
    @FXML
    private fun handleSearch() {
        val searchVal = searchField.text
//        workersTable.items.setAll(FXCollections.observableArrayList<AbstractWorker>(mainApp!!.workersData).filter {
//            it.effortInterval.toString().contains(searchVal, true) ||
//                    it.getRate().toString().contains(searchVal, true) ||
//                    it.getSalaryType().toString().contains(searchVal, true) ||
//                    it.getSalary().toString().contains(searchVal, true)
//        })
    }

    /**
     * Закрытие окна
     */
    @FXML
    fun handleClose() {
        dialogStage.close()
    }
}