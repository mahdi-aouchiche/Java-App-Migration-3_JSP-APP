<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.demo.model.Employee"%>
<%@ page import="com.demo.service.EmployeeService"%>
<%!@SuppressWarnings("unchecked")%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>View And Edit Employees</title>
	
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
    </script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js">
    </script>
    
    <style type="text/css">
   		.action-icons img {
			margin: 0 5px;
			cursor: pointer;
			width: 30px;
			height: 25px;
		}
		.action-icons {
			display: flex;
			justify-content: space-around;
			align-items: center;
		}
 		.edit-form-row {
			display: none;
		}
    </style>
</head>
<body>
	<div class="container">
		<br>
		<h2 style="text-align: center"><%=request.getAttribute("informationType") %></h2>

		<table class="table table-bordered table-striped table-hover">
			<%-- Table Header --%>
			<thead>
				<tr>
					<%
					for (String label : (List<String>) request.getAttribute("tableHeader")) {
						out.println("<th style='text-align: center'>" + label + "</th>");
					}
					%>
				</tr>
			</thead>

			<%-- Table Body --%>
			<tbody>
				<%
				for (Employee employee : (List<Employee>) request.getAttribute("associatedEmployees")) {
				%>
				<%-- Row with Employee Data --%>
				<tr>
					<td style='text-align: center'><%=employee.getId()%></td>
					<td style='text-indent: 25%'><%=employee.getName()%></td>
					<td style='text-align: center'><%=employee.getAge()%></td>
					<td style='text-indent: 25%'><%=String.format("$%,.2f" ,employee.getSalary())%></td>
					<td>
						<div class="action-icons">
							<%-- Delete Icon --%>
							<%
							String deleteEmployee = "window.location.href='DeleteEmployee?id=" 
													+ employee.getId() + "&returnURL="
													+ request.getAttribute("returnURL");
							%>
							<img src='images/delete.png' title='delete' onclick="<%=deleteEmployee%>'">
	
							<%-- Edit Icon --%>
							<img src="images/edit.png" title="edit" onclick="showUpdateForm(<%=employee.getId()%>)">
						</div>
					</td>
				</tr>

				<%-- Hidden Row with Edit Form --%>
				<tr class="edit-form-row" id="edit-row-<%=employee.getId()%>">

					<%
					//TODO: Find a way to keep the same webpage display and move the form tag to satisfy HTML rules
					String actionURL = "UpdateEmployee?id=" + employee.getId() + "&returnURL=" + request.getAttribute("returnURL");
					%>
					<form action="<%= actionURL %>" method="post">
						<td style='text-align: center'><input type="hidden" name="id" 
							value="<%=employee.getId()%>"><%=employee.getId()%></td>
						<td><input type="text" name="name" class="form-control"
							value="<%=employee.getName()%>" placeholder="Full Name"
							style="text-indent: 25%" required></td>
						<td><input type="number" name="age" class="form-control"
							value="<%=employee.getAge()%>" placeholder="Age" min="18"
							max="100" style='text-align: center' required></td>
						<td><input type="number" name="salary" class="form-control"
							oninput='doubleValue()' min='0' step="0.01"
							value="<%=String.format("%.2f", employee.getSalary())%>"
							step="0.01" placeholder="Salary" style="text-indent: 25%"
							required></td>
						<td style='display: flex; justify-content: space-around; align-items: center;'>
							<button type="button" class="btn btn-warning"
								onclick="showUpdateForm(<%=employee.getId()%>)">Cancel</button>
							<button type="submit" class="btn btn-success">Update</button>	
						</td>
					</form>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%-- Go Back To Menu Link --%>
		<br> <a href="OptionMenu" class="btn btn-primary">Go Back to Menu</a>
	</div>
	<hr>
		
	<script>
		function showUpdateForm(id) {
			var element = document.getElementById('edit-row-' + id);
			if (element.style.display === 'none' || element.style.display === '') {
				element.style.display = 'table-row';
			} else {
				element.style.display = 'none';
			}
		}
	</script>
</body>
</html>