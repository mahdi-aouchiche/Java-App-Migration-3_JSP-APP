package com.demo.model;

import java.util.Objects;

public class EmpDept {
	private int eId;
	private int dId;
	/**
	 * @param eId
	 * @param dId
	 */
	public EmpDept(int eId, int dId) {
		super();
		this.eId = eId;
		this.dId = dId;
	}
	
	/**
	 * @return the eId
	 */
	public int geteId() {
		return eId;
	}
	
	/**
	 * @param eId the eId to set
	 */
	public void seteId(int eId) {
		this.eId = eId;
	}
	
	/**
	 * @return the dId
	 */
	public int getdId() {
		return dId;
	}
	
	/**
	 * @param dId the dId to set
	 */
	public void setdId(int dId) {
		this.dId = dId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dId, eId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpDept other = (EmpDept) obj;
		return dId == other.dId && eId == other.eId;
	}
	
	@Override
	public String toString() {
		return "EmpDept [eId=" + eId + ", dId=" + dId + "]";
	}
}
