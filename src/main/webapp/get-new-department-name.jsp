<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Create A New Department</title>
		
		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
		<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>
		
		<style>
			body {
				display: grid;
				place-items: center;
				min-height: 100vh;
				margin: 0;
			}
			
			table { 
				width: 500px;
				height: 50%;
			}
			
			tr {
				height: 40px;
			}
			
			th {
				text-align: center;
			}
			
			td {
				padding: 8px;
				text-align: left;
			}
			
			input {
				height: 40px;
				width: 100%;
				box-sizing: border-box;
				padding: 1px;
				text-align: center;
			}	
		</style>

	</head>
	<body>
		<form method="post" action="CreateNewDepartment">
			<table>
				<thead>
					<tr>
						<th colspan='2'>
						<%-- Display the result to user --%>
						<%= request.getAttribute("message").toString() %>
						</th>
					</tr>
				</thead>
				<%-- Get Department Name From User --%>
				<tbody>
					<tr style='	border: 2px solid #ccc;'>
						<td style='width: 30%;'>
							Department Name
						</td>
						<td>
							<input type='text' name='departmentName'  placeholder='Example: Customer Service' required>
						</td>
					</tr>
				<tbody>
				<%-- Submit Button --%>
				<tfoot>
					<tr>
						<th colspan='2'>
							<input type='submit' value='Add Department'>
						</th>
					</tr>
				<tfoot>
			</table>
			<%-- Go Back To Menu Link --%>
			<br>
			<a href='OptionMenu' class='btn btn-primary'>Go Back to Menu</a>
		</form>
	</body>
</html>