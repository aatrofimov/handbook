package com.app.view

import com.app.*
import com.app.model.*
import javafx.fxml.*
import javafx.scene.control.*
import javafx.stage.*
import java.time.*
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
     * Тествое поле ввода имени работника
     */
    @FXML
    lateinit var nameInput: TextField

    /**
     * Тествое поле ввода фамилии
     */
    @FXML
    lateinit var surnameInput: TextField

    /**
     * Тествое поле ввода коэффициента зарплаты
     */
    @FXML
    lateinit var rateInput: TextField

    /**
     * Текстовое поле ввода трудозатрат
     */
    @FXML
    lateinit var workTime: TextField

    /**
     * Текстовое поле ввода нормы рабочего времени
     */
    @FXML
    lateinit var workTimeNorm: TextField

    /**
     * Поле ввода даты начала работы
     */
    @FXML
    lateinit var beginDate: DatePicker

    /**
     * Поле ввода даты окончания работы
     */
    @FXML
    lateinit var endDate: DatePicker

    /**
     * label для поля ставки/почасовая ставки
     */
    @FXML
    lateinit var rateLabel: Label

    /**
     * label для поля нормы рабочего времени
     */
    @FXML
    lateinit var workTimeNormLabel: Label

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
     * Тестовые имена работников мужского пола
     */
    private val testMaleNames = listOf("Александр", "Сергей", "Алексей", "Дмитрий", "Андрей")

    /**
     * Тестовые имена работников женского пола
     */
    private val testFemaleNames = listOf("Елена", "Ольга", "Анна", "Ирина", "Мария")

    /**
     * Тестовые фамилии
     */
    private val testSurnames = listOf("Иванов", "Смирнов", "Кузнецов", "Попов", "Соколов")

    /**
     * * Инициализация контроллера. Этот метод вызывается автоматически после загрузки fxml
     */
    @FXML
    private fun initialize() {
        salaryTypeCombobox.items.addAll(SalaryType.values().map { it.toString() })
        randomDataButton.isVisible = DEBUG_MODE
        setVisible(false)
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
        workTime.text = rnd.nextInt(200).toString()
        val types = SalaryType.values()
        salaryTypeCombobox.value = types[rnd.nextInt(types.size)].toString()
        beginDate.value = LocalDate.of(2017, 1, 9)
        surnameInput.text = testSurnames[rnd.nextInt(4)]
        if (rnd.nextBoolean()) {
            nameInput.text = testMaleNames[rnd.nextInt(4)]
        } else {
            nameInput.text = testFemaleNames[rnd.nextInt(4)]
            surnameInput.text += "а"
        }
        if (salaryTypeCombobox.value == SalaryType.Wage.toString()) {
            workTimeNorm.text = rnd.nextInt(140, 192).toString()
        }
    }

    @FXML
    private fun onSalaryTypeChanged() {
        if (salaryTypeCombobox.value == SalaryType.Hour.toString()) {
            rateLabel.text = "Часовая ставка"
            setVisible(false)
        } else if (salaryTypeCombobox.value == SalaryType.Wage.toString()) {
            rateLabel.text = "Cтавка"
            setVisible(true)
        }
    }

    private fun setVisible(isVisible: Boolean) {
        workTimeNormLabel.isVisible = isVisible
        workTimeNorm.isVisible = isVisible
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
        val workTime = workTime.text.toDoubleOrNull()
        if (workTime == null || workTime < 0) {
            isValid = false
            errorMsg += "\nневалидное значение трудозатрат"
        }
        val begin = beginDate.value
        if (begin == null) {
            isValid = false
            errorMsg += "\nдолжна быть введена дата начала работы"
        }
        val end = endDate.value
        if (begin != null && end != null && beginDate.value.isAfter(endDate.value)) {
            isValid = false
            errorMsg += "\nдата окончания работы не может быть раньше даты начала работы"
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
    private fun getSalaryType(): SalaryType = SalaryType.toEnum(salaryTypeCombobox.value)!!

    /**
     * @return введенная ставка
     * @see AbstractWorker.getRate
     */
    private fun getRate(): Double = rateInput.text.toDoubleOrNull()!!

    /**
     * @return трудозатраты
     * @see AbstractWorker.effortInterval
     */
    private fun getWorkTime(): Double = workTime.text.toDoubleOrNull()!!

    fun createWorker(): AbstractWorker? {
        var worker: AbstractWorker? = null
        if (okClicked) {
            try {
                worker = WorkerFactory.create(
                        getSalaryType(),
                        nameInput.text,
                        surnameInput.text,
                        beginDate.value,
                        endDate.value,
                        getRate(),
                        getWorkTime(),
                        workTimeNorm.text.toDoubleOrNull()
                )
            } catch (ex: Exception) {
                val dialog = Alert(Alert.AlertType.ERROR)
                dialog.title = "Ошибка"
                dialog.headerText = "Ошибка при создании нового работника"
                dialog.contentText = ex.message
                dialog.showAndWait()
            }
        }
        return worker
    }
}