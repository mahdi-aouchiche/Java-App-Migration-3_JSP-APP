package com.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
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
 * Servlet implementation class ViewEmployeesEarningSecondHighestSalary
 */
@WebServlet("/ViewEmployeesEarningSecondHighestSalary")
public class ViewEmployeesEarningSecondHighestSalary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeService employeeService = null;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
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
		
		// Get info from service layer
		List<Employee> employeesEarningSecondMaximumSalary = new ArrayList<Employee>();
		List<String> columnLabels = new ArrayList<String>();
		this.employeeService.employeesEarningSecondMaximumSalary(columnLabels,employeesEarningSecondMaximumSalary);
		
		// Send data to JSP
		request.setAttribute("columnLabel", columnLabels);
		request.setAttribute("records", employeesEarningSecondMaximumSalary );
		request.getRequestDispatcher("view-employees-earning-second-highest-salary.jsp").forward(request, response);
	}
}
