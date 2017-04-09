package com.app.model;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.<br>
 * User: TrofimovAA<br>
 * Date: 27.03.2017<br>
 * Time: 6:23<br>
 * Класс - обертка для сериализации с помощью JAXB
 */
@XmlRootElement(name = "workers")
public class WorkersListWrapper {

	/**
	 * @see AbstractWorker
	 */
	private List<AbstractWorker> workers;

	/**
	 * @return workers
	 * @see AbstractWorker
	 * @see HourWorker
	 * @see WageWorker
	 */
	@XmlElements({
		@XmlElement(name = "hourWorker", type = HourWorker.class),
		@XmlElement(name = "wageWorker", type = WageWorker.class)
	})
	public List<AbstractWorker> getWorkers() {
		return workers;
	}

	/**
	 *
	 * @param workers
	 */
	public void setWorkers(List<AbstractWorker> workers) {
		this.workers = workers;
	}
}
