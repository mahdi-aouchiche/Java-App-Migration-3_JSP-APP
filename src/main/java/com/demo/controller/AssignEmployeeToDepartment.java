package com.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.model.Department;
import com.demo.model.Employee;
import com.demo.service.DepartmentService;
import com.demo.service.EmployeeService;

/**
 * Servlet implementation class AssignEmployeeToDepartment
 */
@WebServlet("/AssignEmployeeToDepartment")
public class AssignEmployeeToDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DepartmentService departmentService = null;
	EmployeeService employeeService = null;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.departmentService = new DepartmentService();
		this.employeeService = new EmployeeService();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Check if the user is logged in to be able to have access*/
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("username") == null) {
			response.sendRedirect("Login");
			return;
		}
		
		List<Employee> employees = this.employeeService.getEmployeeList();
		List<Department> departments = this.departmentService.getDepartmentList();
		
		request.setAttribute("message", "");
		request.setAttribute("employees", employees);
		request.setAttribute("departments", departments);
		request.getRequestDispatcher("assign-existing-employee-to-department.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Check if the user is logged in to be able to have access*/
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("username") == null) {
			response.sendRedirect("Login");
			return;
		}
		
		int numUpdatedRecords = -1;
		String message = "";
				
		try {	
			// Retrieve the selected IDs from the form
			int departmentId = Integer.parseInt(request.getParameter("departmentId"));
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));

			numUpdatedRecords = departmentService.addEmployeeToDepartment(departmentId, employeeId);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		switch (numUpdatedRecords) {
			case 0: // FAILURE: No records were updated.
				message = "<p style='color:red; font-weight:bold; text-align: center'> " + 
						  "Error!!! <br>Invalid Input.</p>";
				break;
				
			case 1: // SUCCESS: One record was updated.
				message = "<p style='color:green; font-weight:bold; text-align: center'>" + 
						  "Employee Assigned To The Department Successfully.</p>";
				break;
				
			case 2: // SUCCESS: Record exists already.
				message = "<p style='color:blue; font-weight:bold; text-align: center'>" + 
						  "Employee Is Already Assigned To This Department.</p>";
				break;
			
			default:
				message = "<p style='color:orange; font-weight:bold; text-align: center'>" + 
						  "Incomplete!!! <br>" + 
						  "Both 'Employee Name' And 'Department Name' Are Required.</p>";
				break;
		}
				
		List<Employee> employees = this.employeeService.getEmployeeList();
		List<Department> departments = this.departmentService.getDepartmentList();
		
		request.setAttribute("message", message);
		request.setAttribute("employees", employees);
		request.setAttribute("departments", departments);
		request.getRequestDispatcher("assign-existing-employee-to-department.jsp").forward(request, response);
	}
}
