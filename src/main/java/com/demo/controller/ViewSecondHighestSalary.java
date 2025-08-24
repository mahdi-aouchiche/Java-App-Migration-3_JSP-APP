package com.demo.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.service.EmployeeService;

/**
 * Servlet implementation class ViewSecondHighestSalary
 */
@WebServlet("/ViewSecondHighestSalary")
public class ViewSecondHighestSalary extends HttpServlet {
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
		} else {		
			double secondHighestSalary = this.employeeService.secondMaxEmployeesSalary();
			
			request.setAttribute("secondHighestSalary", secondHighestSalary );
			request.getRequestDispatcher("view-second-highest-salary.jsp").forward(request, response);
		}			
	}
}
