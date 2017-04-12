package com.app.view

import com.app.*
import com.app.model.*
import javafx.fxml.*
import javafx.scene.control.*
import javafx.stage.*
import java.io.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 25.03.2017<br>
 * Time: 17:26<br>
 * Контроллер таблицы просмотра работников
 */
class WorkersOverviewController : WorkersTableController() {
    companion object {
        /**
         * Расширение файлов приложения
         */
        const val extension = ".asd"
    }

    /**
     * Label с именем работника
     */
    @FXML
    private lateinit var nameLabel: Label

    /**
     * Label с фамилией работника
     */
    @FXML
    private lateinit var surnameLabel: Label

    /**
     * Label с датой начала работы
     */
    @FXML
    private lateinit var beginDateLabel: Label

    /**
     * Label с датой окончания работы
     */
    @FXML
    private lateinit var endDateLabel: Label

    /**
     * Label с типом зарплаты
     */
    @FXML
    private lateinit var salaryTypeLabel: Label

    /**
     * Label с размер ставки
     */
    @FXML
    private lateinit var rateLabel: Label

    /**
     * Label с отработанным времен
     */
    @FXML
    private lateinit var workTimeLabel: Label

    /**
     * Label с нормой рабочего времени
     */
    @FXML
    private lateinit var workTimeNormLabel: Label

    /**
     * Label с зарплатой
     */
    @FXML
    private lateinit var salaryLabel: Label

    /**
     * Ссылка на объект основного класса
     */
    var mainApp: MainApp? = null
        set(value) {
            field = value
            workersTable.items = value!!.workersData
        }

    @FXML
    override fun initialize() {
        super.initialize()

        showWorkerDetails(null)

        workersTable.selectionModel.selectedItemProperty().addListener {
            observable, oldValue, newValue ->
            showWorkerDetails(newValue)
        }
    }

    private fun showWorkerDetails(worker: AbstractWorker?) {
        if (worker != null) {
            nameLabel.text = worker.name
            surnameLabel.text = worker.surname
            beginDateLabel.text = worker.beginDate.toString()
            endDateLabel.text = worker.endDate?.toString() ?: ""
            salaryTypeLabel.text = worker.getSalaryType().toString()
            rateLabel.text = worker.getRate().toString()
            workTimeLabel.text = worker.workTime.toString()
            workTimeNormLabel.text = (worker as? WageWorker)?.workTimeNorm?.toString() ?: ""
            salaryLabel.text = worker.getSalary().toString()
        } else {
            nameLabel.text = ""
            surnameLabel.text = ""
            beginDateLabel.text = ""
            endDateLabel.text = ""
            salaryTypeLabel.text = ""
            rateLabel.text = ""
            workTimeLabel.text = ""
            workTimeNormLabel.text = ""
            salaryLabel.text = ""
        }
    }

    /**
     * Обработчик кнопки удаления работника {
     */
    @FXML
    private fun handleRemoveWorker() {
        workersTable.items.remove(workersTable.selectionModel.selectedItem)
    }

    /**
     * Добавление нового работника
     */
    @FXML
    private fun handleNewWorker() {
        mainApp?.showWorkerCreateDialog()?.let { mainApp?.workersData?.add(it) }
    }

    /**
     * Обработчик кнопки поиска
     */
    @FXML
    private fun handleSearch() {
        mainApp?.showSearchForm()
    }

    /**
     * Обработчик кнопки "Сохранить"
     */
    @FXML
    private fun handleSave() {
        val file = mainApp?.getFilePath();
        if (file != null) {
            mainApp?.saveWorkersDataToFile(file)
        } else {
            handleSaveAs();
        }
    }

    /**
     * Обработчки конпки "Сохранить как"
     */
    @FXML
    private fun handleSaveAs() {
        val fileChooser = FileChooser()

        FileChooser.ExtensionFilter(
                "$extension files (*$extension)", "*$extension")

        var file: File? = fileChooser.showSaveDialog(mainApp?.primaryStage)

        if (file != null) {
            if (!file.path.endsWith(extension)) {
                file = File("${file.path}$extension")
            }
            mainApp?.saveWorkersDataToFile(file)
        }
    }

    /**
     * Обработчик кнопки "Загрузка"
     */
    @FXML
    private fun handleLoad() {
        val fileChooser = FileChooser()

        val extFilter = FileChooser.ExtensionFilter(
                "$extension files (*$extension)", "*$extension")
        fileChooser.extensionFilters.add(extFilter)

        val file = fileChooser.showOpenDialog(mainApp?.primaryStage)

        if (file != null) {
            mainApp?.loadPersonDataFromFile(file)
        }
    }

    /**
     * Редактирование работника
     */
    @FXML
    private fun handleEditPerson() {
        var selectedWorker = workersTable.selectionModel.selectedItem
        if (selectedWorker != null) {
            selectedWorker = mainApp!!.showWorkerEditDialog(selectedWorker)
            showWorkerDetails(selectedWorker)
            workersTable.selectionModel.select(selectedWorker)
        }
    }
}