package com.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.service.EmployeeService;

/**
 * Servlet implementation class AddNewEmployee
 */
@WebServlet("/AddNewEmployee")
public class AddNewEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		
		String messageString = "Enter New Employee Details";
		request.setAttribute("message", messageString);
		request.getRequestDispatcher("get-new-employee-information.jsp").forward(request, response);
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
		
		String firstname = "";
		String lastname = "";
		String fullname = "";
		String message = "";
		int age = 0;
		double salary = 0;	
		int	numEmployeesAdded = -1;
		
		/* Get employee details from user */
		try {
			
			firstname = request.getParameter("firstname").trim();
			lastname = request.getParameter("lastname").trim();
			age = Integer.parseInt(request.getParameter("age"));
			salary = Double.parseDouble(request.getParameter("salary"));		
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		/* Add new employee using service layer */
		if(firstname.length() < 1) {
			message = "First Name Cannot Be Empty!";
		} else if (lastname.length() < 1) {
			message = "Last Name Cannot Be Empty!";
		} else if (age < 18 || age > 100) {
			message = "Age Must Be Between 18 to 100";
		} else if (salary < 0) {
			message = "Salary Cannot Be Less Than Zero!";
		} else {
			fullname = firstname + " " + lastname;
			EmployeeService employeeService = new EmployeeService();
			numEmployeesAdded = employeeService.addNewEmployee(firstname, lastname, age, salary);
		}
		
		/* Display transaction result to the user */		
		switch (numEmployeesAdded) {
			case 0: // Unsuccessful 
				message = "<p style='color:red; font-weight:bold;'>" + 
						  		"Unsuccessful! Employee NOT Added!" + 
						  "</p>";
				break;
				
			case 1:	// Employee Added Successfully
				message = "<p style='color:green; font-weight:bold; text-align:center;'>" + 
								"Employee \"" + fullname + "\" Added Successfully!" + 
								"<br>Enter New Employee Details To Add Another Employee" +
						  "</p>";
				break;
				
			case 2:	// Employee Exists based on full name and age
				message = "<p style='color:blue; font-weight:bold; text-align:center;'>" +
								"Employee \"" + fullname + "\" Exists Already!" + 
								"<br>Update Employee Instead." +
								"<br>Enter New Employee Details To Add An Employee" +
						  "</p>";
				break;

			default:
				message = "<p style='color:orange; font-weight:bold; text-align:center;'>" + 
							 	message +
						  "</p>";
				if(message.length() < 1) { 
					message = "<p style='color:orange; font-weight:bold; text-align:center;'>" + 
								 "Wrong Input" +
							  "</p>";
				}
				break;
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher("get-new-employee-information.jsp").forward(request, response);	
	}
}
