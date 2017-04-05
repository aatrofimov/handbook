package com.app.view

import com.app.*
import com.app.model.*
import javafx.fxml.*
import javafx.scene.control.*
import javafx.stage.*
import java.util.concurrent.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 25.03.2017<br>
 * Time: 18:33<br>
 * Контроллер диалога создания работника
 */
class WorkerEditDialogController {

    /**
     * Комбобокс для выбора типа зарплат
     * @see SalaryType
     */
    @FXML
    lateinit var salaryTypeCombobox: ComboBox<String>

    /**
     * Тествое поле ввода коэффициента зарплаты
     */
    @FXML
    lateinit var rateInput: TextField

    /**
     * Текстовое поле ввода трудозатрат
     */
    @FXML
    lateinit var effortIntervalInput: TextField

    /**
     * Кнопка для получения случайных значений на форме
     */
    @FXML
    lateinit var randomDataButton: Button

    /**
     * Ссылка на контейнер
     */
    lateinit var dialogStage: Stage

    /**
     * Показывает, какая кнопка была нажата
     * @return true если была нажата кнопка "Ok"
     */
    var okClicked = false
        private set

    /**
     * * Инициализация контроллера. Этот метод вызывается автоматически после загрузки fxml
     */
    @FXML
    private fun initialize() {
        salaryTypeCombobox.items.addAll(SalaryType.values().map { it.toString() })
        randomDataButton.isVisible = DEBUG_MODE
    }

    /**
     * Обработчик кнопки "Ок"
     */
    @FXML
    private fun handleSave() {
        if (isValid()) {
            okClicked = true
            dialogStage.close()
        }
    }

    /**
     * Обработчик кнопки "Отмена"
     */
    @FXML
    private fun handleCancel() {
        okClicked = false
        dialogStage.close()
    }

    /**
     * Получение случайных значений
     */
    @FXML
    private fun handleGetRandomData() {
        val rnd = ThreadLocalRandom.current()
        rateInput.text = rnd.nextInt(1000).toString()
        effortIntervalInput.text = rnd.nextInt(1000).toString()
        val types = SalaryType.values()
        salaryTypeCombobox.value = types[rnd.nextInt(types.size)].toString()
    }

    /**
     * Валидация данных
     */
    private fun isValid(): Boolean {
        var isValid = true
        var errorMsg = ""
        val rate = rateInput.text.toDoubleOrNull()
        if (rate == null || rate <= 0) {
            isValid = false
            errorMsg += "невалидное значение коэффициента зарплаты"
        }
        val effort = effortIntervalInput.text.toDoubleOrNull()
        if (effort == null || effort < 0) {
            isValid = false
            errorMsg += "\nневалидное значение трудозатрат"
        }
        if (!isValid) {
            val dialog = Alert(Alert.AlertType.WARNING)
            dialog.title = "Ошибка"
            dialog.headerText = "На форме есть ошибки"
            dialog.contentText = errorMsg
            dialog.showAndWait()
        }
        return isValid
    }

    /**
     * @return Тип выбранной зарплаты
     * @see AbstractWorker.getSalaryType
     */
    fun getSalaryType(): SalaryType = SalaryType.toEnum(salaryTypeCombobox.value)!!

    /**
     * @return введенная ставка
     * @see AbstractWorker.getRate
     */
    fun getRate(): Double = rateInput.text.toDoubleOrNull()!!

    /**
     * @return трудозатраты
     * @see AbstractWorker.effortInterval
     */
    fun getEffortInterval(): Double = effortIntervalInput.text.toDoubleOrNull()!!
}