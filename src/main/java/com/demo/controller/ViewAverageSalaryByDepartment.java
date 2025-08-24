package com.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.Servlet;
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
 * Servlet implementation class ViewAverageSalaryByDepartment
 */
@WebServlet("/ViewAverageSalaryByDepartment")
public class ViewAverageSalaryByDepartment extends HttpServlet {
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
		
		// Get info from service layer
		List<String> columnLabel = new ArrayList<String>();
		LinkedHashMap<Department, Double> records = new LinkedHashMap<>();
		this.departmentService.averageSalaryByDepartment(columnLabel, records);
		
		// Send data to JSP
		request.setAttribute("columnLabel", columnLabel );
		request.setAttribute("records", records );
		request.getRequestDispatcher("view-average-salary-by-department.jsp").forward(request, response);
	}
}
