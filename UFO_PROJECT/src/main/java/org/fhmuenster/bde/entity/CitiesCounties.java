package org.fhmuenster.bde.entity;

public class CitiesCounties {

	private String city;
	private String county;

	public CitiesCounties(String city, String county) {
		super();
		this.city = city;
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Override
	public String toString() {
		return "CitiesCounties [city=" + city + ", county=" + county + "]";
	}

}
