package com.demo.service;

import java.util.List;

import com.demo.dao.EmployeeDAO;
import com.demo.model.Employee;

public class EmployeeService {
	EmployeeDAO employeeDAO;

	public EmployeeService() {
		this.employeeDAO = new EmployeeDAO();
	}

	/**
	 * Find all employees affiliated to a department
	 * 
	 * @param List of column labels
	 * @param List of employees associated to a department
	 */
	public void listOfEmployeesAssociatedToDepartment(
			List<String> columnLabels, 
			List<Employee> employeesAssociatedToDepartment)
	{
		this.employeeDAO.getEmployeesAssociatedToDepartment(
				columnLabels, 
				employeesAssociatedToDepartment
		);
	}

	/**
	 * Find all employees NOT affiliated to a department
	 * 
	 * @param List of column labels
	 * @param List of employees NOT associated to a department
	 */
	public void listOfEmployeesNotAssociatedToDepartment(
			List<String> columnLabels, 
			List<Employee> employeesNotAssociatedToDepartment) 
	{
		this.employeeDAO.getEmployeesNotAssociatedToDepartment(
				columnLabels, 
				employeesNotAssociatedToDepartment
		);
	}

	/**
	 * Find the Average salary of all employees
	 * 
	 * @return The average salary of all employees
	 */
	public double averageEmployeesSalary() {
		return this.employeeDAO.getAverageSalaryOfAllEmployees();
	}

	/**
	 * Find the Maximum and Minimum salaries of employees
	 * 
	 * @return [minimum salary, maximum salary] 
	 */
	public List<Double>  getMinAndMaxSalary() {
		return this.employeeDAO.getMinAndMaxSalary();
	}

	/**
	 * Find the second Max salary
	 * 
	 * @return The second max salary
	 */
	public double secondMaxEmployeesSalary() {
		return this.employeeDAO.getSecondMaximumSalary();
	}

	/**
	 * Find the employees earning the second maximum salary
	 * 
	 * @param List of column labels
	 * @param List of employees earning the second maximum salary
	 */
	public void employeesEarningSecondMaximumSalary(
			List<String> columnLabels, 
			List<Employee> employeesEarningSecondMaximumSalary)
	{
		this.employeeDAO.getEmployeesEarningSecondMaximumSalary(
				columnLabels,
				employeesEarningSecondMaximumSalary
		);
	}

	/**
	 * Add a new employee
	 * 
	 * @param firstname employee's first name
	 * @param lastname employee's last name
	 * @param age      employee's age
	 * @param salary   employee's salary
	 * @return 1 if the employee is added, 2 if employee exists, 0 otherwise.
	 */
	public int addNewEmployee(String firstname, String lastname, int age, double salary) {	
		String fullname = firstname + " " + lastname;
		String[] splitName = fullname.trim().split(" ");
		
		String name = "";
		for(String str : splitName) {
			str = str.toLowerCase();
			name += str.substring(0, 1).toUpperCase() + str.substring(1) + " "; 
		}
		name = name.trim();
					 
		int employeesAdded = this.employeeDAO.addEmployee(name, age, salary);
		return employeesAdded;
	}

	/**
	 * Delete an employee from the database
	 * 
	 * @param employeeID
	 * @return true if employee is deleted, false otherwise.
	 */
	public Boolean deleteEmployee(int employeeID) {
		return this.employeeDAO.deleteEmployee(employeeID);
	}

	/**
	 * Return the number of employees updated
	 * 
	 * @param employeeID
	 * @param name
	 * @param age
	 * @param salary
	 * @return number of updated records in the DB. 
	 */
	public int updateEmployee(int employeeID, String name, int age, double salary) {
		
		String[] splitName = name.trim().split(" ");
		
		String fullname = "";
		for(String str : splitName) {
			str = str.toLowerCase();
			fullname += str.substring(0, 1).toUpperCase() + str.substring(1) + " "; 
		}
		fullname = fullname.trim();
		
		return this.employeeDAO.updateEmployee(employeeID, fullname, age, salary);
	}

	/**
	 * Get all employees
	 * 
	 * @return list of employees
	 */
	public List<Employee> getEmployeeList() {
		return this.employeeDAO.getEmployees();
	}
}
