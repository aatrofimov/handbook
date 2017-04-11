package com.app.view

import com.app.*
import javafx.fxml.*
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
     * Ссылка на объект основного класса
     */
    var mainApp: MainApp? = null
        set(value) {
            field = value
            workersTable.items = value!!.workersData
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
        mainApp?.showWorkerEditDialog()?.let { mainApp?.workersData?.add(it) }
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
}