 package com.demo.service;
import java.util.LinkedHashMap;
import java.util.List;

import com.demo.dao.DepartmentDAO;
import com.demo.model.Department;

public class DepartmentService {
	
	DepartmentDAO departmentDAO;
	
	public DepartmentService() {
		this.departmentDAO = new DepartmentDAO();
	}
	
	/**
	 * Find employees average salary in all department
	 * 
	 * @param list to hold the table header column labels
	 * @param map to hold the mapping of employee average salary to each department
	 */
	public void averageSalaryByDepartment(
			List<String> columnLabel, 
			LinkedHashMap<Department, Double> records) 
	{
		this.departmentDAO.getAverageSalaryByDepartment(columnLabel, records);
	}
	
	/**
	 * Get employee count per department
	 * 
	 * @param list to hold the table header column labels
	 * @param map to hold the mapping of employee count of each department
	 */
	public void employeeCountByDepartment(
			List<String> columnLabel,
			LinkedHashMap<Department, Integer> records)
	{
		this.departmentDAO.getEmployeeCountByDepartment(columnLabel, records);
	}
	
	/**
	 * Find all departments with a minimum given number of employees
	 * 
	 * @param list to hold the table header column labels
	 * @param map to hold the mapping of employee count of each department
	 * @param minimum number of employees to consider in the result 
	 * @return ReslutSet a list of all department with at least numEmployees
	 */
	public void listOfDepartmentsWithAtLeastACertainNumberOfEmployees(
			List<String> columnLabel, 
			LinkedHashMap<Department, Integer> records, 
			long numEmployees) 
	{
		this.departmentDAO.getDepartmentsWithAtLeastACertainNumberOfEmployees(
				columnLabel, records, numEmployees
		);
	}

	/**
	 * Create a new Department in the database
	 * 
	 * @param department name
	 * @return number of records updated: 0-none, 1-created a new department, 2-exists
	 */
	public int createNewDepartment(String departmentName) {
		String dName = "";
		String[] splitName = departmentName.trim().split(" ");
		
		for(String str : splitName) {
			str = str.toLowerCase();
			dName += str.substring(0, 1).toUpperCase() + str.substring(1) + " "; 
		}
		dName = dName.trim();
		return this.departmentDAO.createDepartment(dName);
	}

	/**
	 * Add an employee to a department
	 * 
	 * @param Department name
	 * @param Employee name
	 * @return 0 if failed,
	 * @return 1 if successfully associated employee to department
	 * @return 2 employee already associated to the department
	 */
	public int addEmployeeToDepartment(int departmentId, int employeeId){
		return this.departmentDAO.addEmployeeToDepartment(departmentId,employeeId);
	}

	/**
	 * Get the list of department from the database
	 * 
	 * @return list of all the departments 
	 */
	public List<Department> getDepartmentList() {		
		return this.departmentDAO.getDepartments();
	}
}
