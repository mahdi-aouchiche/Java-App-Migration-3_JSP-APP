package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.demo.model.Department;
import com.demo.utility.DBConnectionUtility;

public class DepartmentDAO {
	
	private Connection connection = null;
	
	public DepartmentDAO() {
		this.connection = new DBConnectionUtility().getJDBC_Connection();
	}
	
	/**
	 * Get average salary for each department 
	 * Ordered by Highest average salary to lowest average salary
	 * 
	 * @param list which will hold the table header column labels
	 * @param map which will hold a mapping of each department to the average salary
	 */
	public void getAverageSalaryByDepartment(
			List<String> columnLabel,
			LinkedHashMap<Department, Double> records) 
	{
			String query = 
				"SELECT d.id AS 'Department ID', " +
						"d.name AS 'Department Name', " +
						"AVG(e.salary) AS 'Department Average Salary' " +
				"FROM department d LEFT JOIN emp_dept ed ON d.id = ed.dId " +
						"LEFT JOIN employee e ON ed.eid = e.id " +
				"GROUP BY d.id " +
				"ORDER BY AVG(e.salary) DESC;";
		
		try(Statement statement = this.connection.createStatement()) {
			// Execute query
			ResultSet rs = statement.executeQuery(query);
			
			// Get the table header
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnLabel.add(rsmd.getColumnLabel(i));
			}
			
			// Get the records 
			while(rs.next()) {
				records.put(new Department(rs.getInt(1), rs.getString(2)), rs.getDouble(3));
			}
					
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a list of departments which has a at least given number of employees
	 * Ordered by highest number of employees to lowest then by department Id
	 * 
	 * @param list which will hold the table header column labels
	 * @param map which will hold a mapping of each department to the employee count
	 */
	public void getDepartmentsWithAtLeastACertainNumberOfEmployees(
		List<String> columnLabel,
		LinkedHashMap<Department, Integer> records, long numEmployees)
	{			
		String query = "SELECT id AS 'Department ID', " +
							  "name AS 'Department Name', " +
							  "Count(dId) AS 'Employee Count' " +
						"FROM department LEFT JOIN emp_dept ON id = dId " +
						"GROUP BY id " +
						"HAVING COUNT(dId) >= ? " +
						"ORDER BY COUNT(dId), id;";
		
		try(PreparedStatement ps = this.connection.prepareStatement(query)) {
			ps.setLong(1, numEmployees);
			ResultSet rs = ps.executeQuery();
			
			// Get the table header 
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnLabel.add(rsmd.getColumnLabel(i));
			}
			
			// Get the records of the query
			while(rs.next()) {
				records.put(new Department(rs.getInt(1), rs.getString(2)), rs.getInt(3));
			}
			
			// Close the result set
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	 * Create a new department in the database
	 * 
	 * @param name of a new department
	 * @return 2 if department exists
	 * @return 1 if one department added
	 * @return 0 if no department added,
	 */
	public int createDepartment(String departmentName) {
		
		// Check if department exists already
		String checkQuery = "SELECT COUNT(id) FROM department WHERE name = ?;";		
		try(PreparedStatement ps = this.connection.prepareStatement(checkQuery);) {	
			ps.setString(1, departmentName);
			ResultSet rs = ps.executeQuery(); 
			if(rs.next() && rs.getInt(1) > 0) {
				rs.close();
				return 2;
			}		
			rs.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 
		String insertQuery = "INSERT INTO department(name) VALUES(?);";
		try(PreparedStatement ps = this.connection.prepareStatement(
				insertQuery, Statement.RETURN_GENERATED_KEYS))
		{
			ps.setString(1, departmentName);
			return ps.executeUpdate(); // return number of updated records		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * Add an existing employee to a department
	 * 
	 * @param Department id
	 * @param Employee id
	 * @return 0 unsuccessful, employee associated to the department already
	 * @return 1 successfully added employee to department
	 * @return 2 employee is already assigned to the department
	 */
	public int addEmployeeToDepartment(int departmentId, int employeeId) {
			
		// Check if employee belongs to the the same department already
		String checkRelationshipQuery = "SELECT COUNT(*) " +
										"FROM emp_dept " +
										"WHERE dId = ? AND eId = ?";
		
		try(PreparedStatement ps = 
				this.connection.prepareStatement(checkRelationshipQuery))
		{
			ps.setInt(1, departmentId);
			ps.setInt(2, employeeId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() && rs.getInt(1) > 0) {
				rs.close();
				return 2; // employee is associated to the department already
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		// Associate the employee to the department
		String insertQuery = "INSERT INTO emp_dept(eId, dId) VALUES(?,?)";
		
		try(PreparedStatement ps = this.connection.prepareStatement(insertQuery);) {
			ps.setInt(1, employeeId);
			ps.setInt(2, departmentId);
			return ps.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Get the list of all departments
	 *  
	 * @return list of departments
	 */
	public List<Department> getDepartments() {
		List<Department> departmentList = new ArrayList<Department>();
		String deptQuery = "SELECT id, name " +
						   "FROM department " +
						   "ORDER BY name;";
		
		try(PreparedStatement ps = this.connection.prepareStatement(deptQuery)) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				departmentList.add(new Department(rs.getInt(1), rs.getString(2)));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return departmentList;
	}

	/**
	 * Get employee count grouped by department ordered by name
	 * 
	 * @param columnLabel list to hold the table column header 
	 * @param records list to hold the count of employees for each department
	 */
	public void getEmployeeCountByDepartment(
			List<String> columnLabel, 
			LinkedHashMap<Department, Integer> records) 
	{
		String query = "SELECT id 			AS 'Department ID', " +
							  "name 		AS 'Department Name', " +
							  "COUNT(dId) 	AS 'Number of Employees' " + 
						"FROM department LEFT JOIN emp_dept ON id = dId " +
						"GROUP BY id " +
						"ORDER BY name;";
		try(Statement statement = this.connection.createStatement()) {
			ResultSet rs = statement.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			// Get column labels
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnLabel.add(rsmd.getColumnLabel(i));
			}
			
			// Get count of employees for each department
			while(rs.next()) {
				records.put(new Department(rs.getInt(1), rs.getString(2)), rs.getInt(3));
			}
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
