package com.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.model.Department;
import com.demo.service.DepartmentService;

/**
 * Servlet implementation class ViewDepartmentsWithAtLeastANumberOfEmployees
 */
@WebServlet("/ViewDepartmentsWithAtLeastANumberOfEmployees")
public class ViewDepartmentsWithAtLeastANumberOfEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DepartmentService departmentService = null;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.departmentService = new DepartmentService();
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
		
		String message = "Enter The Least Number Of Employees Per Department";
		request.setAttribute("message", message);
		request.getRequestDispatcher("get-number-of-employees.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long numEmployees = 0;
		List<String> columnLabel = new ArrayList<String>();
		LinkedHashMap<Department, Integer> records = new LinkedHashMap<>();
		
		/* Get the least number of employees */
		try {
			numEmployees = Long.parseLong(request.getParameter("numEmployees"));
		} catch (Exception e) {
			/* Ask user for correct input*/
			String message = "<p style='color:orange; font-weight:bold; text-align:center;'>" + 
				 					"Please Enter  A Valid Minimum Number Of Employees" +
				 			 "</p>";
			request.setAttribute("message", message);	
			request.getRequestDispatcher("get-number-of-employees.jsp").forward(request, response);
			System.out.println(e.getMessage());
		}
	
		/* Send info to service layer */
		this.departmentService.listOfDepartmentsWithAtLeastACertainNumberOfEmployees(columnLabel, records, numEmployees);
		
		/* Dispatch to view-jsp*/
		request.setAttribute("departmentList", records);
		request.setAttribute("columnLabel", columnLabel);
		request.getRequestDispatcher("view-departments-by-number-of-employees.jsp").forward(request, response);
	}
}
