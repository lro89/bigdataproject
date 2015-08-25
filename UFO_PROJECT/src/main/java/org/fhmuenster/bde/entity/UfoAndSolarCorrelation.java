package org.fhmuenster.bde.entity;

/**
 * Klasse um, die Ufos korreliert mit der Sonnenkraft abzubilden.
 * 
 */
public class UfoAndSolarCorrelation {

	/**
	 * Datum mit Monat und Jahr in der Form: yyyy/mm
	 */
	private String yearMonth;

	/**
	 * Anzahl der UFO-Sichtungen
	 */
	private Integer value;

	/**
	 * Sonnenkraft
	 */
	private Integer solar;

	public UfoAndSolarCorrelation(String year, String month, Integer value,
			Integer solar) {
		this.yearMonth = year + "/" + month;
		this.value = value;
		this.solar = solar;
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

	public Integer getSolar() {
		return solar;
	}

	public void setSolar(Integer solar) {
		this.solar = solar;
	}

}
