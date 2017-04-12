/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 11.04.2017<br>
 * Time: 6:52<br>
 * Регистрация xml адаптера
 */
@XmlJavaTypeAdapter(type = LocalDate.class,
	value = LocalDateAdapter.class)
package com.app.model;

import com.app.utils.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.*;
import java.time.*;