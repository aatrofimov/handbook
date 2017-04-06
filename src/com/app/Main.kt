//package com.app
//
//import com.app.model.*
//
///**
// * Created by IntelliJ IDEA.<br>
// * User: Alexey<br>
// * Date: 25.03.2017<br>
// * Time: 1:10<br>
// * Версия с консольным интерфейсом
// */
//object Main {
//
//    /**
//     * Точка входа в программу
//     */
//    @JvmStatic
//    fun main(args: Array<String>) {
//        testWorker(SalaryType.Hour);
//        testWorker(SalaryType.Salary);
//        testWorker(SalaryType.Wage);
//    }
//
//    /**
//     * Сообщения о начале тестирования для каждого типа работников
//     */
//    private val greets = mapOf(
//            SalaryType.Hour to "Тестирование работника с почасовой ставкой.",
//            SalaryType.Salary to "Тестирование работника с окладом.",
//            SalaryType.Wage to "Тестирование работника со ставкой."
//    )
//
//    /**
//     * Запросы значений для каждого типа работников
//     */
//    private val prompts = mapOf(
//            SalaryType.Hour to "Введите почасовую ставку: ",
//            SalaryType.Salary to "Введите оклад: ",
//            SalaryType.Wage to "Введите ставку: "
//    )
//
//    /**
//     * Чтение double с клавиатуры
//     */
//    private fun readDouble(prompt: String): Double {
//        println(prompt)
//        var result: Double? = null
//        while (result == null)
//            try {
//                result = readLine()!!.toDouble()
//            } catch (e: Exception) {
//                println("Неправильный ввод!")
//                println(prompt)
//            }
//        return result
//    }
//
//    /**
//     * тест расчетов
//     */
//    private fun testWorker(type: SalaryType) {
//        println(greets[type])
//        val value = readDouble(prompts[type] as String)
//        val interval = readDouble("Введите интервал для начисления зарплаты: ")
//        val worker = WorkerFactory.create(type, value, interval)
//        println("Зарплата: ${worker.getSalary()}")
//    }
//}