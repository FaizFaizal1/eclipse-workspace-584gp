package courier.model;

import java.io.Serializable;

public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;
	private int staffID; // use adminID? example given by miss uses different ID name.
	private String adminRole;

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}