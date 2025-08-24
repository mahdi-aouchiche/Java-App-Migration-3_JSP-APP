package com.demo.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.service.EmployeeService;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
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
		
		if(session == null || session.getAttribute("username")== null) {
			String needToLogin = "<p style='color:blue; font-weight:bold;'>Please enter your credentials to access menu.</p>";
			
			request.setAttribute("message", needToLogin);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		int employeeID = -1;
		String returnURL = "";
		
		try {
			employeeID = Integer.parseInt(request.getParameter("id"));
			returnURL = request.getParameter("returnURL");
			
			System.out.println("id: " + employeeID);
			System.out.println("url: " + returnURL);
			
			if(!employeeService.deleteEmployee(employeeID)) {
				System.out.println("Delete employee id= " + employeeID + " unsuccessful!");
			}
			request.getRequestDispatcher(returnURL).forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
