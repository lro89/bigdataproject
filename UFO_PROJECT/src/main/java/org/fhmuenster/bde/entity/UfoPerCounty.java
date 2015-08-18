package org.fhmuenster.bde.entity;

public class UfoPerCounty {

	String county;

	Integer numberOfUfos;

	public UfoPerCounty(String county, Integer numberOfUfos) {
		this.county = county;
		this.numberOfUfos = numberOfUfos;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Integer getNumberOfUfos() {
		return numberOfUfos;
	}

	public void setNumberOfUfos(Integer numberOfUfos) {
		this.numberOfUfos = numberOfUfos;
	}

}
