package com.accolite.mini_au;

import java.time.LocalDate;

public class License {
	int nipr;
	int license_id;
	String state_code;
	LocalDate effective_date;
	char resident;
	String lic_class;
	LocalDate exp_date;
	char lic_status;
	
	
	
	@Override
	public String toString() {
		return "" + nipr + "," + license_id + "," + state_code
				+"," + resident + "," +lic_class
				+ "," + effective_date +  "," +  exp_date + "," + lic_status + "\n";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effective_date == null) ? 0 : effective_date.hashCode());
//		result = prime * result + ((exp_date == null) ? 0 : exp_date.hashCode());
//		result = prime * result + lic_class_code;
//		result = prime * result + lic_status;
		result = prime * result + license_id;
		result = prime * result + nipr;
//		result = prime * result + resident;
		result = prime * result + ((state_code == null) ? 0 : state_code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		License other = (License) obj;
		if (effective_date == null) {
			if (other.effective_date != null)
				return false;
		} else if (!effective_date.equals(other.effective_date))
			return false;
//		if (exp_date == null) {
//			if (other.exp_date != null)
//				return false;
//		} else if (!exp_date.equals(other.exp_date))
//			return false;
//		if (lic_class_code != other.lic_class_code)
//			return false;
//		if (lic_status != other.lic_status)
//			return false;
		if (license_id != other.license_id)
			return false;
		if (nipr != other.nipr)
			return false;
//		if (resident != other.resident)
//			return false;
		if (state_code == null) {
			if (other.state_code != null)
				return false;
		} else if (!state_code.equals(other.state_code))
			return false;
		return true;
	}
	
	
}
