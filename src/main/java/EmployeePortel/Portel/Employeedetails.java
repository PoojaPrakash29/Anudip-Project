package EmployeePortel.Portel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employeedetails {
	
	@Id //used as a primary-key
	private int employeeid;
	private String First_Name;
	private String Last_Name;
	private String dob;
	private double Salary;
	private String department;
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public double getSalary() {
		return Salary;
	}
	public void setSalary(double salary) {
		Salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employeedetails [employeeid=" + employeeid + ", First_Name=" + First_Name + ", Last_Name=" + Last_Name
				+ ", dob=" + dob + ", Salary=" + Salary + ", department=" + department + "]";
	}
	
	
}
