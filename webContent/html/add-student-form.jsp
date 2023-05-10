
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
	<title>Add Student</title>
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Student Registration System</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Student</h3>
		
		<form action="/Student_Registration_System/StudentControllerServlet" method="GET">
		<input type="hidden" name="command" value="ADD" />
					
			<table>
				<tbody>
					
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" required pattern = "[a-zA-Z]{3,20}" title = "All letters must be in English and not less than 3 letters"/></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" required pattern = "[a-zA-Z]{3,20}" title = "All letters must be in English and not less than 3 letters"/></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" required pattern = "^\w+([\.-]?\w+)*@gmail\.com$" title = "Invalid gmail"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="/Student_Registration_System/StudentControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











