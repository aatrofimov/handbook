package com.app.utils;

import javax.xml.bind.annotation.adapters.*;
import java.time.*;

/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 11.04.2017<br>
 * Time: 6:49<br>
 * xml адаптер для типа LocalDate
 */
public class LocalDateAdapter
	extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}

}
