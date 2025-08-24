<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, 
				 java.util.LinkedHashMap,
				 java.util.Map"%>
<%@ page import="com.demo.model.Department"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Average Salary Of Each Department</title>
		
		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
		<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>
		<style>
			th { text-align:center; }
		</style>
	</head>
	<body>
		<div class='container'>
			<br>
			<table class='table table-bordered table-striped table-hover'>
			<%	
				@SuppressWarnings("unchecked")
				ArrayList<String> columnLabel = (ArrayList<String>) request.getAttribute("columnLabel");
			
				@SuppressWarnings("unchecked")
				LinkedHashMap<Department, Integer> records = (LinkedHashMap<Department, Integer>) request.getAttribute("records");				
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
					for(Map.Entry<Department, Integer> entry : records.entrySet()) {
				%>
				<tr>
					<td style='text-align:center'>
						<%= entry.getKey().getId() %>
					</td>
					<td style='text-indent: 20%'>
						<%= entry.getKey().getName()%>
					</td>	
					<td style='text-indent: 40%'>
						<%= String.format("$%,.2f", entry.getValue()) %>
					</td>
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