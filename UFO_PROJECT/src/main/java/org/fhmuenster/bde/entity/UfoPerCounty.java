package org.fhmuenster.bde.entity;

public class UfoPerCounty {

	/**
	 * Schlüssel um County in der Highchart-Map zu matchen<br>
	 * Beispiel: us-ca-051 -> Mono County
	 */
	private String code;

	/**
	 * Anzahl der UFO-Sichtungen
	 */
	private Integer value;

	/**
	 * Bezeichung der County
	 */
	private String county;

	public UfoPerCounty(String code, Integer value, String county) {
		this.code = code;
		this.value = value;
		this.county = county;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
}
