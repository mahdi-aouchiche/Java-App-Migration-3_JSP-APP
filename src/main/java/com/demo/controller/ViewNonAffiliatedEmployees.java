package com.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.model.Employee;
import com.demo.service.EmployeeService;

/**
 * Servlet implementation class ViewNonAffiliatedEmployees
 */
@WebServlet("/ViewNonAffiliatedEmployees")
public class ViewNonAffiliatedEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	EmployeeService employeeService = null;

	public void init(ServletConfig config) throws ServletException {
		this.employeeService = new EmployeeService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(	HttpServletRequest request,
							HttpServletResponse response)
		throws ServletException, IOException
	{
		/* Check if the user is logged in to be able to have access*/
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("username") == null) {
			response.sendRedirect("Login");
			return;
		}
		
		/* Get affiliated employees */
		String message = "Non-Affiliated Employees Information";
		List<Employee> employeesAssociatedToDepartment = new ArrayList<Employee>();
		List<String> columnLabels = new ArrayList<String>();
		this.employeeService
			.listOfEmployeesNotAssociatedToDepartment(
					columnLabels,
					employeesAssociatedToDepartment
		);
		
		// add the action column to delete and edit an employee
		columnLabels.add("Action");
		
		request.setAttribute("tableHeader", columnLabels);
		request.setAttribute("associatedEmployees", employeesAssociatedToDepartment);
		request.setAttribute("returnURL", request.getServletPath());
		request.setAttribute("informationType", message);
		request.getRequestDispatcher("view-edit-employees.jsp").forward(request, response);			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(	HttpServletRequest request,
							HttpServletResponse response)
		throws ServletException, IOException
	{
		doGet(request, response);
	}
}