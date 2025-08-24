<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='en'>
	<head>
		<meta charset="UTF-8">
		<title>Employee Management Menu</title>
		
		<meta name='viewport' content='width=device-width, initial-scale=1.0'>
		
		<!-- Tail wind CSS for styling -->
		<script src='https://cdn.tailwindcss.com'></script>
		<!-- Google Fonts: Inter -->
		<link rel='preconnect' href='https://fonts.googleapis.com'>
		<link rel='preconnect' href='https://fonts.gstatic.com'>
		<link href='https://fonts.googleapis.com/css2?family=Inter:wght@400;
					500;600;700&display=swap' rel='stylesheet'>	
		<style>
			/* Use the Inter font family */
			body {
		       	font-family: 'Inter', sans-serif;
		    }
		</style>		
	</head>
	<body class='bg-gray-100 flex items-center justify-center min-h-screen'>
		<!-- Main Menu Container -->
		<div class='bg-white rounded-xl shadow-2xl p-8 max-w-4xl w-full m-4'>
			<h1 class='text-3xl font-bold text-gray-800 mb-4 text-center'>
				Employee Management System
			</h1>
			
		    <br/>    
			<!-- Menu Buttons Grid -->
			<nav>
				<div class='grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4'>
					
					<!-- View Actions -->
					<a href='ViewAffiliatedEmployees' 
						class='text-center p-4 rounded-lg bg-blue-500 text-white 
							font-semibold shadow-md hover:bg-blue-600 focus:outline-none 
							focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75 
							transition-all duration-200 transform hover:-translate-y-1'>
						Affiliated Employees
					</a> 
					
					<a href='ViewNonAffiliatedEmployees'
						class='text-center p-4 rounded-lg bg-blue-500 text-white 
							font-semibold shadow-md hover:bg-blue-600 focus:outline-none 
							focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75 
							transition-all duration-200 transform hover:-translate-y-1'>
						Non-Affiliated Employees
					</a>
					 
		            <a href='ViewEmployeeCountByDepartment' 
		            	class='text-center p-4 rounded-lg bg-blue-500 text-white 
		            		font-semibold shadow-md hover:bg-blue-600 
		            		focus:outline-none focus:ring-2 focus:ring-blue-400 
		            		focus:ring-opacity-75 transition-all duration-200 
		            		transform hover:-translate-y-1'>
		            	Employee Count By Department
		            </a>
		             
		            <a href='ViewAverageSalary' 
		            	class='text-center p-4 rounded-lg bg-blue-500 text-white 
		            		font-semibold shadow-md hover:bg-blue-600 focus:outline-none 
		            		focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75 
		            		transition-all duration-200 transform hover:-translate-y-1'>
		            	Average Salary
		            </a> 
		            
		            <a href='ViewMinimumAndMaximumSalaries' 
		            	class='text-center p-4 rounded-lg bg-blue-500 text-white 
		            		font-semibold shadow-md hover:bg-blue-600 focus:outline-none 
		            		focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75 
		            		transition-all duration-200 transform hover:-translate-y-1'>
		            	Minimum and Maximum Salaries
		            </a> 
		            
		            <a href='ViewSecondHighestSalary' 
		            	class='text-center p-4 rounded-lg bg-blue-500 text-white 
		            		font-semibold shadow-md hover:bg-blue-600 focus:outline-none 
		            		focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75 
		            		transition-all duration-200 transform hover:-translate-y-1'>
		            	Second Highest Salary
		            </a> 
		            
		            <a href='ViewAverageSalaryByDepartment' 
		            	class='text-center p-4 rounded-lg bg-blue-500 text-white 
		            		font-semibold shadow-md hover:bg-blue-600 focus:outline-none 
		            		focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75 
		            		transition-all duration-200 transform hover:-translate-y-1'>
		            	Average Salary by Department
		            </a> 
		            
		            <a href='ViewEmployeesEarningSecondHighestSalary' 
		            	class='text-center p-4 rounded-lg bg-blue-500 text-white 
		            		font-semibold shadow-md hover:bg-blue-600 focus:outline-none 
		            		focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75 
		            		transition-all duration-200 transform hover:-translate-y-1'>
		            	Earners of 2nd Highest Salary
		            </a> 
		            
		            <a href='ViewDepartmentsWithAtLeastANumberOfEmployees' 
		            	class='text-center p-4 rounded-lg bg-blue-500 text-white 
		            		font-semibold shadow-md hover:bg-blue-600 focus:outline-none 
		            		focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75 
		            		transition-all duration-200 transform hover:-translate-y-1'>
		            	Departments by Number Of Employees
		            </a> 
		            
					<!-- Create Actions -->
		            <a href='AddNewEmployee' 
		            	class='text-center p-4 rounded-lg bg-green-500 text-white 
		            		font-semibold shadow-md hover:bg-green-600 focus:outline-none 
		            		focus:ring-2 focus:ring-green-400 focus:ring-opacity-75 
		            		transition-all duration-200 transform hover:-translate-y-1'>
		            	Add New Employee
		            </a> 
		            
		            <a href='CreateNewDepartment' 
		            	class='text-center p-4 rounded-lg bg-green-500 text-white 
		            		font-semibold shadow-md hover:bg-green-600 focus:outline-none 
		            		focus:ring-2 focus:ring-green-400 focus:ring-opacity-75 
		            		transition-all duration-200 transform hover:-translate-y-1'>
		            	Create New Department
		            </a>              
		            
					<!-- Modify Actions -->
		            <a href='AssignEmployeeToDepartment' 
		            	class='text-center p-4 rounded-lg bg-yellow-500 text-white 
		            		font-semibold shadow-md hover:bg-yellow-600 focus:outline-none 
		            		focus:ring-2 focus:ring-yellow-400 focus:ring-opacity-75 
		            		transition-all duration-200 transform hover:-translate-y-1'>
		            	Assign Employee to Department
		            </a> 
					
					<!-- Logout -->
			 		<br>
		    		<a href='Logout' 
		    			class='text-center p-4 rounded-lg bg-red-500 text-white 
		    				font-semibold shadow-md hover:bg-yellow-600 focus:outline-none 
		    				focus:ring-2 focus:ring-yellow-400 focus:ring-opacity-75 
		    				transition-all duration-200 transform hover:-translate-y-1'>
		    			Logout
		    		</a> 
		   		</div>
		   	</nav>
		</div>	
	</body>
</html>