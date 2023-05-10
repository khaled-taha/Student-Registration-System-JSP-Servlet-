<!DOCTYPE html>
<html>

<head>
	<title>Update Student</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Student Registration System</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Student</h3>
		
		<form action="/Student_Registration_System/StudentControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="studentId" value="${studentObject.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" required pattern = "[a-zA-Z]{3,20}" title = "All letters must be in English and not less than 3 letters"
								   value="${studentObject.firstName}" /></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" required pattern = "[a-zA-Z]{3,20}" title = "All letters must be in English and not less than 3 letters"
								   value="${studentObject.lastName}" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email"  required pattern = "^\w+([\.-]?\w+)*@gmail\.com$" title = "Invalid gmail"
								   value="${studentObject.email}" /></td>
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











