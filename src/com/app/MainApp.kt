package com.app

import com.app.model.*
import com.app.view.*
import javafx.application.*
import javafx.collections.*
import javafx.fxml.*
import javafx.scene.*
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.stage.*
import java.io.*
import java.util.prefs.*
import javax.xml.bind.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 25.03.2017<br>
 * Time: 15:39<br>
 * Основной класс приложения. Является точкой входа для приложения с UI на JavaFX.
 * Здесь же хранится список работников для таблицы.
 */
class MainApp : Application() {
    /**
     * Основной контейнер
     */
    lateinit var primaryStage: Stage

    /**
     * Базовый макет приложения
     */
    private lateinit var rootLayout: BorderPane

    /**
     * Данные таблицы работников
     */
    val workersData = FXCollections.observableArrayList<AbstractWorker>()!!

    companion object {
        @JvmStatic
        fun main(arg: Array<String>) {
            launch(MainApp::class.java)
        }
    }

    override fun start(primaryStage: Stage) {
        this.primaryStage = primaryStage
        setFilePath(null)
        initRootLayout()
        showWorkersOverview()
    }

    /**
     * Инициализация базового макета
     */
    private fun initRootLayout() {
        try {
            val loader = FXMLLoader()
            loader.location = MainApp.javaClass.getResource("/com/app/view/RootLayout.fxml")
            rootLayout = loader.load<Parent?>() as BorderPane
            primaryStage.scene = Scene(rootLayout)
            loader.getController<RootLayoutController>().mainApp = this
            primaryStage.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Открытие таблицы с работниками
     */
    private fun showWorkersOverview() {
        try {
            val loader = FXMLLoader()
            loader.location = MainApp.javaClass.getResource("/com/app/view/WorkersOverview.fxml")
            rootLayout.center = loader.load<Parent?>() as AnchorPane
            loader.getController<WorkersOverviewController>().mainApp = this
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Открытие дилога создания работника
     * @return AbstractWorker
     * @see AbstractWorker
     * @see WorkerFactory.create
     */
    fun showWorkerEditDialog(): AbstractWorker? {
        val loader = FXMLLoader()
        loader.location = MainApp.javaClass.getResource("/com/app/view/WorkerEditDialog.fxml")
        val dialogStage = Stage()
        dialogStage.title = "Добавить работника"
        dialogStage.initModality(Modality.WINDOW_MODAL)
        dialogStage.initOwner(primaryStage)
        dialogStage.scene = Scene(loader.load())
        val controller = loader.getController<WorkerEditDialogController>()
        controller.dialogStage = dialogStage
        dialogStage.showAndWait()
        return controller.createWorker()
    }

    /**
     * Открытие окна формы поиска
     */
    fun showSearchForm() {
        val loader = FXMLLoader()
        loader.location = MainApp.javaClass.getResource("/com/app/view/SearchForm.fxml")
        val dialogStage = Stage()
        dialogStage.title = "Поиск"
        dialogStage.initModality(Modality.WINDOW_MODAL)
        dialogStage.initOwner(primaryStage)
        dialogStage.scene = Scene(loader.load())
        val controller = loader.getController<SearchFormController>()
        controller.mainApp = this
        loader.getController<SearchFormController>().dialogStage = dialogStage
        dialogStage.showAndWait()
    }

    /**
     * Загрузка данных из файла
     */
    fun loadPersonDataFromFile(file: File) {
        try {
            val context = JAXBContext
                    .newInstance(WorkersListWrapper::class.java)
            val um = context.createUnmarshaller()
            val wrapper = um.unmarshal(file) as WorkersListWrapper
            workersData.clear()
            workersData.addAll(wrapper.workers)
            setFilePath(file)
        } catch (e: Exception) {
            val dialog = Alert(Alert.AlertType.ERROR)
            dialog.title = "Ошибка"
            dialog.headerText = "Невозможно загрузить информацию из файла:\n${file.path}"
            dialog.contentText = e.message
            dialog.showAndWait()
        }
    }

    /**
     * Сохранение данных таблицы в файл
     */
    fun saveWorkersDataToFile(file: File) {
        try {
            val context = JAXBContext
                    .newInstance(WorkersListWrapper::class.java)
            val m = context.createMarshaller()
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            val wrapper = WorkersListWrapper()
            wrapper.workers = workersData
            m.marshal(wrapper, file)
            setFilePath(file)
        } catch (e: Exception) {
            val dialog = Alert(Alert.AlertType.ERROR)
            dialog.title = "Ошибка"
            dialog.headerText = "Невозможно сохранить информацию в файл:\n${file.path}"
            dialog.contentText = e.message
            dialog.showAndWait()
        }

    }

    /**
     * Установка пути к файлу
     */
    fun setFilePath(file: File?) {
        val prefs = Preferences.userNodeForPackage(MainApp::class.java)
        if (file != null) {
            prefs.put("filePath", file.path)
            primaryStage.title = "Список работников - " + file.name
        } else {
            prefs.remove("filePath")
            primaryStage.title = "Список работников"
        }
    }

    /**
     * Получение пути к файлу
     */
    fun getFilePath(): File? {
        val filePath = Preferences.userNodeForPackage(MainApp::class.java).get("filePath", null)
        if (filePath != null) {
            return File(filePath)
        } else {
            return null
        }
    }
}