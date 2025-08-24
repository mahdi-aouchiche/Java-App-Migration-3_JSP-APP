<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Minimum And Maximum Salaries</title>
		
		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
		<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>
	</head>
	<body>
		<div class='container'>
			<br>
			<table class='table table-bordered table-striped table-hover'>
				<tr>
					<th style='text-align:center'>Minimum Salary</th>
					<th style='text-align:center'>Maximum Salary</th>
				</tr>
				<tr>
					<td style='text-align:center'>
						<%=String.format("$%,.2f", (Double) request.getAttribute("minSalary")) %> 
					</td>
					<td style='text-align:center'>
						<%=String.format("$%,.2f", (Double) request.getAttribute("maxSalary")) %> 
					</td>
				</tr>		
			</table>
			<br>
			<a href='OptionMenu' class='btn btn-primary'>Go Back to Menu</a>
		</div>
	</body>
</html>