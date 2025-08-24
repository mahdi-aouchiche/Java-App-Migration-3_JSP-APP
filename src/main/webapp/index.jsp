<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP Welcome Page Demo</title>

	<style>
		h1 {
			color: blue;
			font-size: 24px;
			font-family: verdana;
			text-align: center;
		}

		img {
			max-width: 70%;
			height: auto;
			padding: 20px;
			display: block; /* Images are inline elements by default, convert to block */
		  	margin-left: auto;
		  	margin-right: auto;
		  	margin-bottom : auto;
		}

		h2 {
			text-align: center;
		}
	</style>
</head>
<body>
	<h1>Welcome to Java Server Pages (JSP) Demo</h1>

	<h2>
		Login to continue to menu.
		<br>
		<a href='/Java-App-Migration-3_jsp/Login'>User Login</a>
	</h2>

	<img src="images/jsp.jpg" alt="JSP" />
</body>
</html>