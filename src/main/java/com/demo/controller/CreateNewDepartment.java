package com.demo.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.service.DepartmentService;

/**
 * Servlet implementation class CreateNewDepartment
 */
@WebServlet("/CreateNewDepartment")
public class CreateNewDepartment extends HttpServlet {
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
		
		request.setAttribute("message", "Create A New Department");
		request.getRequestDispatcher("get-new-department-name.jsp").forward(request, response);
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
		
		
		int numDepartmentsAdded = -1;
		String departmentName = "";
		String message = "";
		
		/* Get department name from user */
		try {
			// Retrieve the department entered by the user
			departmentName = request.getParameter("departmentName").trim();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		/* Add department name to database */
		if(departmentName.length() > 0) {
			numDepartmentsAdded = this.departmentService.createNewDepartment(departmentName);
		}
		
		/* Display the result */
		
		switch (numDepartmentsAdded) {
			case 0: // FAILURE: No records were updated.
				message = "<p style='color:red; font-weight:bold;'>" +
						  	"Error! <br>" +
						  	"The Department Could Not Be Added." +
						  "</p>";
				break;
			
			case 1: // SUCCESS: One record was updated.
				message = "<p style='color:green; font-weight:bold;'>" + 
							"Department Added Successfully" +
						  "</p>";
				break;
			
			case 2: // Department Exists Already
				message = "<p style='color:blue; font-weight:bold;'>" +
							"The Department Name \"" + departmentName + 
							"\" Exists Already! <br>" +
							"Add A Different Department" + 
						  "</p>";
				break;

			default:
				message = "<p style='color:orange; font-weight:bold;'>" + 
							"Invalid Input! Name Cannot Be Empty." +
						  "</p>";
				break;
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher("get-new-department-name.jsp").forward(request, response);
	}
}
