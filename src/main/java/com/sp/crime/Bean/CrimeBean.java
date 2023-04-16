package com.sp.crime.Bean;

public class CrimeBean extends BaseBean{
	
	public String getCrime_name() {
		return crime_name;
	}
	public void setCrime_name(String crime_name) {
		this.crime_name = crime_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String crime_name;
	private String description;

}
