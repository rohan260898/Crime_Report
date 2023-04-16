package com.sp.crime.Bean;

import java.sql.Timestamp;

public class BaseBean {
	protected long id;
	protected String createdby;
	protected String modifiedby;
	protected Timestamp createdatetime;
	protected Timestamp modifieddatetime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Timestamp getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Timestamp createdatetime) {
		this.createdatetime = createdatetime;
	}
	public Timestamp getModifieddatetime() {
		return modifieddatetime;
	}
	public void setModifieddatetime(Timestamp modifieddatetime) {
		this.modifieddatetime = modifieddatetime;
	}
}
