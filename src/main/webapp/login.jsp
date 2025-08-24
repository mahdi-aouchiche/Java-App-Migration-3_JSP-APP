<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login Page</title>
	
	<link rel='stylesheet'
		href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
	<script
		src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
	<script
		src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>
	
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
		
		input {
			width: 100%;
			height: 40px;
			box-sizing: border-box;
			padding: 1px;
			text-align: center;
		}
	</style>
</head>

<body class='bg-gray-100 flex items-center justify-center min-h-screen'>
	<form method='post' action='/Java-App-Migration-3_jsp/Login'>
		<table>
			<thead>
				<tr style='border:none; '>
					<th><h2>Enter your login details</h2></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type='text' name='username' placeholder='User Name' required>
					</td>
				</tr>
				<tr>
					<td>
						<input type='password' name='password' placeholder='Password' required>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
						<input type='submit' value='Login' required>
					<td>
				</tr>
			</tfoot>
		</table>

		<%
		// --- Display the message if it exists ---
		try {
			Object message = request.getAttribute("message");
			if (message != null) {
				out.println(message.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		%>

	</form>
</body>
</html>