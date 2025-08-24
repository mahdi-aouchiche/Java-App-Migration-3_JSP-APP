<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Get Minimum Number Of Employee</title>
		
		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
		<script 
			src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js' type="text/javascript">
		</script>
		<script 
			src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js' type="text/javascript">
		</script>
		
		<style>
			body { 
				display: grid;
				place-items: center;
				min-height: 100vh;
				margin: 0;
			}
			
			td {
				border: 1px solid black;
				padding: 1px;
				text-align: center;
				height: 10px;
			}
			
			table td input {
				height: 100%;
				width: 100%;
				box-sizing: border-box;
				padding: 1px;
			}
			
			tr {
				height:40px;
			}
			
			input {
				height:40px;
				text-align: center;
			}
		</style>
	</head>
	<body>
		<form method='post' action='ViewDepartmentsWithAtLeastANumberOfEmployees'>
			<table>
				<thead>
					<tr>
						<th>
							<%=request.getAttribute("message").toString()%>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<input type='number' name='numEmployees' placeholder='Number of Employees' min='0' required>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th style='text-align: center;'>
							<input type='submit' value='Enter' style='width: 100%;' min='0' required>
						</th>
					</tr>
				</tfoot>
			</table>
			<br> 
			<a href='OptionMenu' class='btn btn-primary'>Go Back to Menu</a>
		</form>
	</body>
</html>