package com.tms.model;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

	boolean isActive;
	public Integer createdBy;
	public Integer updatedBy;
	public Date createdOn;
	private Date updatedOn;

<<<<<<< HEAD
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

=======
>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

<<<<<<< HEAD
=======
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
<<<<<<< HEAD
=======

>>>>>>> 78df98457f45ae270b01f8b64bac7067018ce2fd
}
