package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.demo.model.Employee;
import com.demo.utility.DBConnectionUtility;

public class EmployeeDAO {
	private Connection connection = null;
	
	public EmployeeDAO() {
		this.connection = new DBConnectionUtility().getJDBC_Connection();
	}
	
	/**
	 * Get the list of employees associated to a department
	 * 
	 * @param List to hold column labels
	 * @param List of employees who are associated to a department 
	 */
	public void getEmployeesAssociatedToDepartment(
			List<String> columnLabels, 
			List<Employee> employeesAssociatedToDepartment)
	{
		ResultSet rs = null;
		
		String query = 
				"SELECT DISTINCT id AS 'Employee ID', " +
					   "name AS 'Employee Name', " +
					   "age AS 'Age', " +
					   "salary AS 'Salary' " +
				"FROM emp_dept LEFT JOIN employee ON eId = id " + 
				"ORDER BY name;";
			
		try(Statement statement = this.connection.createStatement()) {
			rs = statement.executeQuery(query);			
			
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnLabels.add(rsmd.getColumnLabel(i));
			}
			
			while(rs.next()) {
				employeesAssociatedToDepartment.add( new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getDouble(4)
				));
			}
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the list of employees NOT associated to a department
	 * 
	 * @param List to hold column labels
	 * @param List of employees not affiliated to a department
	 */
	public void getEmployeesNotAssociatedToDepartment(
			List<String> columnLabels, 
			List<Employee> employeesNotAssociatedToDepartment) 
	{
		ResultSet rs = null;
		
		String query = "SELECT id AS 'Employee ID'," + 
							 " name AS 'Employee Name'," +
							 " age AS 'Age'," +
							 " salary AS 'Salary' " +
						"FROM employee " +
						"WHERE id NOT IN (SELECT eId FROM emp_dept) " +
						"ORDER BY name;";
		
		try(Statement statement = this.connection.createStatement()) {
			rs = statement.executeQuery(query);			
			
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnLabels.add(rsmd.getColumnLabel(i));
			}
			
			while(rs.next()) {
				employeesNotAssociatedToDepartment.add( new Employee(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getInt(3),
						rs.getDouble(4)
				));
			}
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Get the average salary of all employees
	 * 
	 * @return double average salary.
	 */
	public double getAverageSalaryOfAllEmployees() {
		ResultSet rs = null;
		double averageSalary = 0;
		
		String query = "SELECT AVG(salary) AS 'Average Salary' FROM employee;";
		
		try(Statement statement = this.connection.createStatement()) {
			rs = statement.executeQuery(query);
			
			rs.next();
			averageSalary = rs.getInt(1);
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return averageSalary;
	}
	
	/**
	 * Get maximum and minimum salaries
	 * 
	 * @return [minimum salary, maximum salary]
	 */
	public List<Double>  getMinAndMaxSalary() {
		List<Double> minAndMaxSalary = new ArrayList<>();
		String query = "SELECT MIN(salary), MAX(salary) FROM employee;";
		
		try(Statement statement = this.connection.createStatement()) {
			
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				minAndMaxSalary.add(resultSet.getDouble(1));
				minAndMaxSalary.add(resultSet.getDouble(2));
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return minAndMaxSalary;
	}
	
	/**
	 * Get Second maximum salary
	 * 
	 * @return Second maximum salary
	 */
	public double getSecondMaximumSalary() {
		double secondMaxSalary = 0;
				
		String query = "SELECT MAX(salary) AS 'Second Maximum Salary' " + 
				"FROM employee WHERE salary < (SELECT MAX(salary) FROM employee);";
		
		try(Statement statement = this.connection.createStatement()) {	
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()) {
				secondMaxSalary = rs.getDouble(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return secondMaxSalary;
	}
	
	/** 
	 * Get the list of employee earning the second highest salary
	 * 
	 * @param list which will hold the column labels
	 * @param list which will hold the employees earning second max salary
	 */
	public void getEmployeesEarningSecondMaximumSalary(
			List<String> columnLabels,
			List<Employee> employeesEarningSecondMaximumSalary) 
	{
		ResultSet rs = null;
		
		String query = "SELECT id     AS 'Employee ID', " +
							  "name   AS 'Employee Name', " +
							  "age    AS 'Age', " +
							  "salary AS 'Salary' " +
						"FROM employee " +
						"WHERE employee.salary = (" +
									"SELECT MAX(salary) " +
									"FROM employee " +
									"WHERE salary < (SELECT MAX(salary) FROM employee)) " +
						"ORDER BY name ASC;";
		try(Statement statement = this.connection.createStatement()) {
			rs = statement.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnLabels.add(rsmd.getColumnLabel(i));
			}
 			
			while (rs.next()) {
				employeesEarningSecondMaximumSalary.add(
						new Employee(
								rs.getInt(1), 
								rs.getString(2),
								rs.getInt(3),
								rs.getDouble(4)
				));
				
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		};
	}	
	
	
	/** 
	 * Add an employee to the employee table
	 * 
	 * @param fullName
	 * @param age
	 * @param salary
	 * @return 2 if employee exists already in the database
	 * @return 1 if employee added successfully.
	 * @return 0 otherwise.
	 */
	public int addEmployee(String fullName, int age, double salary) {
		// Check if employee already exists
		String queryCheck = "SELECT COUNT(*) FROM employee WHERE name = ? AND age = ?;";
				
		try(PreparedStatement ps = this.connection.prepareStatement(queryCheck);) {
			ps.setString(1, fullName);
			ps.setInt(2, age);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(rs.getInt(1) > 0) {
				rs.close();
				return 2;
			}			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// if employee doesn't exist add to the database
		String queryAdd = "INSERT INTO employee(name, age, salary) Values(?,?,?);";
		
		try(PreparedStatement ps = this.connection.prepareStatement(
				queryAdd, Statement.RETURN_GENERATED_KEYS))
		{		
			ps.setString(1, fullName);
			ps.setInt(2, age);
			ps.setDouble(3, salary);
			return ps.executeUpdate();	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	/**
	 * Delete an employee from the employee and from emp_dept tables
	 * 
	 * @param employeeID to delete
	 * @return true if employee is deleted. false otherwise.
	 */
	public Boolean deleteEmployee(int employeeID) {
		String deleteEmp_DeptQueryString = "DELETE FROM emp_dept WHERE eId = ?;";
		String deleteEmployeeQuery = "DELETE FROM employee WHERE id = ?;";
		
		try (PreparedStatement ps = this.connection.prepareStatement(deleteEmp_DeptQueryString)){
			ps.setInt(1, employeeID);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	
		try (PreparedStatement ps = this.connection.prepareStatement(deleteEmployeeQuery)){
			ps.setInt(1, employeeID);
			int recordDeleted = ps.executeUpdate();
			if (recordDeleted > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Update an employee info
	 * 
	 * @param employeeID
	 * @param name
	 * @param age
	 * @param salary
	 * @return number of employees updated
	 */
	public int updateEmployee(int employeeID, String name, int age, double salary) {
		// Check if more than one employee has the same name and age
		String query = "SELECT COUNT(*) FROM employee WHERE id != ? AND name = ? AND age = ?;";
				
		try(PreparedStatement ps = this.connection.prepareStatement(query);) {
			ps.setInt(1, employeeID);
			ps.setString(2, name);
			ps.setInt(3, age);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(rs.getInt(1) > 0) {
				rs.close();
				return 0;
			}			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		query = "UPDATE employee SET name = ?, age = ?, salary = ? WHERE id = ?;";
		
		try (PreparedStatement ps = this.connection.prepareStatement(query)){
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setDouble(3, salary);
			ps.setInt(4, employeeID);
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	/**
	 * Get all employees from database
	 * 
	 * @return list of all employees
	 */
	public List<Employee> getEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		String employeeQuery = "SELECT * FROM employee ORDER BY name;";
		
		try(PreparedStatement ps = this.connection.prepareStatement(employeeQuery);) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				employeeList.add(new Employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return employeeList;
	}	
}
