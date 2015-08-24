package org.fhmuenster.bde.entity;

/**
 * Klasse um, die Ufos korreliert mit der Temperatur abzubilden.
 * 
 */
public class UfoAndTemperatureCorrelation {

	/**
	 * Datum mit Monat und Jahr in der Form: yyyy/mm
	 */
	private String yearMonth;

	/**
	 * Anzahl der UFO-Sichtungen
	 */
	private Integer value;

	/**
	 * Bezeichnung der County
	 */
	private Integer temperature;

	public UfoAndTemperatureCorrelation(String year, String month,
			Integer value, Integer temperature) {
		this.yearMonth = year + "/" + month;
		this.value = value;
		this.temperature = temperature;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

}
