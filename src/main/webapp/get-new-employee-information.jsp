<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Get Employee Information</title>
	
	<link rel='stylesheet'
		href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
	<script
		src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'>
	</script>
	<script
		src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'>
	</script>
	
	<style>
		body {
			display: grid;
			place-items: center;
			min-height: 100vh;
			margin: 0;
		}
		
		table {
			border:none;
			width: 500px;
			margin: 0;
		}
		
		th {
			text-align: center;
		}
		
		tr {
			height: 40px;
		}
		
		td {
			border: 2px solid #ccc;
			padding: 1px;
			text-align: center;
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

	<form method='post' action='AddNewEmployee'>
		<table>
			<thead>
				<tr>
					<th><%=request.getAttribute("message").toString()%></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type='text' name='firstname'
						placeholder='First Name' required></td>
				</tr>
				<tr>
					<td><input type='text' name='lastname' placeholder='Last Name'
						required></td>
				</tr>
				<tr>
					<td><input type='number' name='age'
						placeholder='Age (between 18 and 100)' min='18' max='100' required>
					</td>
				</tr>
				<tr>
					<td><input type='number' name='salary' placeholder='Salary'
						oninput='doubleValue()' step='0.01' min='0' required></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<th><input type='submit' value='Add Employee'></th>
				</tr>
			</tfoot>
		</table>
		<br> 
		<a href='OptionMenu' class='btn btn-primary'>Go Back to Menu</a>
	</form>
</body>
</html>