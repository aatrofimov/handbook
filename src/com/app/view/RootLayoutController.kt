package com.app.view

import com.app.*
import javafx.fxml.*
import javafx.scene.control.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 25.03.2017<br>
 * Time: 16:12<br>
 * Контроллер базового макета приложения
 */
class RootLayoutController {

    /**
     * Ссылка на основной класс приложения
     * @see MainApp
     */
    lateinit var mainApp: MainApp

    /**
     * Создание новой таблицы. Данные предыдущей затираются
     */
    @FXML
    private fun handleNew() {
        mainApp.workersData.clear()
        mainApp.setFilePath(null)
    }

    /**
     * Справка о программе
     */
    @FXML
    private fun handleAbout() {
        val dialog = Alert(Alert.AlertType.INFORMATION)
        dialog.title = "О программе"
        dialog.headerText = "Лабораторные работы №2, 4, 5"
        dialog.contentText = "Выполнил Трофимов А.А., гр. 513\n25.03.2017"
        dialog.showAndWait()
    }

    /**
     * Закрытие приложения
     */
    @FXML
    private fun handleExit() {
        System.exit(0)
    }
}