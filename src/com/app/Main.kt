package com.app

import com.app.model.*
import java.time.*
import java.util.concurrent.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: Alexey<br>
 * Date: 25.03.2017<br>
 * Time: 1:10<br>
 * Версия с консольным интерфейсом
 */
object Main {

    /**
     * Точка входа в программу
     */
    @JvmStatic
    fun main(args: Array<String>) {
        testWorker(SalaryType.Hour);
        testWorker(SalaryType.Wage);
    }

    /**
     * Сообщения о начале тестирования для каждого типа работников
     */
    private val greets = mapOf(
            SalaryType.Hour to "Тестирование работника с почасовой ставкой.",
            SalaryType.Wage to "Тестирование работника с окладом."
    )

    /**
     * Запросы значений для каждого типа работников
     */
    private val ratePrompts = mapOf(
            SalaryType.Hour to "Введите почасовую ставку: ",
            SalaryType.Wage to "Введите оклад: "
    )

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
     * Чтение double с клавиатуры
     */
    private fun readDouble(prompt: String): Double {
        println(prompt)
        var result: Double? = null
        while (result == null)
            try {
                result = readLine()!!.toDouble()
            } catch (e: Exception) {
                println("Неправильный ввод!")
                println(prompt)
            }
        return result
    }

    /**
     * тест расчетов
     */
    private fun testWorker(type: SalaryType) {
        println(greets[type])
        val rate = readDouble(ratePrompts[type] as String)
        val workTime = readDouble("Введите количество отработанных часов: ")
        var workTimeNorm = .0
        if (type == SalaryType.Wage) {
            workTimeNorm = readDouble("Введите норму рабочего времени: ")
        }
        val beginDate = LocalDate.of(2017, 1, 9)

        val rnd = ThreadLocalRandom.current()
        var name = ""
        var surname = testSurnames[rnd.nextInt(4)]


        if (rnd.nextBoolean()) {
            name = testMaleNames[rnd.nextInt(4)]
        } else {
            name = testFemaleNames[rnd.nextInt(4)]
            surname += "а"
        }
        try {
            val worker = WorkerFactory.create(type, name, surname, beginDate, null, rate, workTime, workTimeNorm)
            println("Зарплата: ${worker.getSalary()}")
        } catch (ex: Exception) {
            println(ex.message)
        }

    }
}