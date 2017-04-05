package com.app

import java.lang.management.*

/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 27.03.2017<br>
 * Time: 4:38<br>
 * Утилиты для отладки
 */
/**
 * Текущее значение режима приложения. true - если приложение в режиме отладки
 */
val DEBUG_MODE = ManagementFactory.getRuntimeMXBean().inputArguments.toString().indexOf("-agentlib:jdwp") > 0;