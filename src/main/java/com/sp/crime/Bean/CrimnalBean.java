package com.sp.crime.Bean;

import java.sql.Blob;
import java.sql.Time;
import java.time.LocalTime;
public class CrimnalBean extends BaseBean{

	private String crimnal_name;
	private String crimnal_address;
	private String gender;
	private LocalTime crime_time;
	private String policestationname;
	private Blob image;
	private String state;
	private long roleid;
	private long userid;
	
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	public String getCrimnal_name() {
		return crimnal_name;
	}
	public void setCrimnal_name(String crimnal_name) {
		this.crimnal_name = crimnal_name;
	}
	public String getCrimnal_address() {
		return crimnal_address;
	}
	public void setCrimnal_address(String crimnal_address) {
		this.crimnal_address = crimnal_address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public LocalTime getCrime_time() {
		return crime_time;
	}
	public void setCrime_time(LocalTime crime_time) {
		this.crime_time = crime_time;
	}
	public String getPolicestationname() {
		return policestationname;
	}
	public void setPolicestationname(String policestationname) {
		this.policestationname = policestationname;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCrime_time(Time time) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
