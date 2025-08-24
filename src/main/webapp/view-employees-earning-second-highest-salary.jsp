<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.demo.model.Employee"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Average Salary Of Each Department</title>
		
		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
		<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>
		<style>
			table th, td { text-align:center; }
		</style>
	</head>
	<body>
		<div class='container'>
			<br>
			<table class='table table-bordered table-striped table-hover'>
			<%	
				@SuppressWarnings("unchecked")
				List<String> columnLabel = (List<String>) request.getAttribute("columnLabel");
			
				@SuppressWarnings("unchecked")
				List<Employee> records = (List<Employee>) request.getAttribute("records");				
			%>	
				<tr>
				<% 
					for (String label : columnLabel) { 
				%>
					<th><%= label %></th>
				<% 
					} 
				%>
				</tr>
				<%
					// print the table rows
					for(Employee employee : records) {
				%>
				<tr>
					<td><%= employee.getId() 	%></td>
					<td><%= employee.getName() %></td>
					<td><%= employee.getAge()   %></td>	
					<td><%=String.format("$%,.2f", employee.getSalary()) %></td>
				</tr>					
				<%
					}
				%>
			</table>
		<%-- Go Back To Menu Link --%>
		<br>
		<a href='OptionMenu' class='btn btn-primary'>Go Back to Menu</a>
		</div>
	</body>
</html>