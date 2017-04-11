package com.app.view

import com.app.*
import com.app.model.*
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
class SearchFormController : WorkersTableController() {
    /**
     * Текстовое поле поиска
     */
    @FXML
    private lateinit var searchField: TextField

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
     * Обработчик кнопки поиска
     */
    @FXML
    private fun handleSearch() {
        val searchVal = searchField.text
        workersTable.items.setAll(FXCollections.observableArrayList<AbstractWorker>(mainApp!!.workersData).filter {
                    it.name.contains(searchVal, true) ||
                    it.surname.contains(searchVal, true) ||
                    it.beginDate.toString().contains(searchVal, true) ||
                    it.endDate?.toString()?.contains(searchVal, true) ?: false ||
                    it.getRate().toString().contains(searchVal, true) ||
                    it.getSalaryType().toString().contains(searchVal, true) ||
                    it.getSalary().toString().contains(searchVal, true) ||
                    (it as? WageWorker)?.workTimeNorm?.toString()?.contains(searchVal, true) ?: false
        })
    }

    /**
     * Закрытие окна
     */
    @FXML
    fun handleClose() {
        dialogStage.close()
    }
}