<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
		<head>
		<title>Students</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
		</head>
		

		<body>
		    
		    <div id = "wrapper">
		    	<div id = "header">
		    		<h2>Student Registration System </h2>
		    	</div>
		    </div>
		    
		    
		
		<div id = "container">
			<div id = "content">
			
			<!-- put new button: Add Student -->
			<c:url var = "addStudent" value = "/StudentControllerServlet">
						<c:param name="command" value="Navigate-to-add-form"></c:param>
			</c:url>
			
			<input type="button" value="Add Student" 
				   onclick="window.location.href='${addStudent}';"
				   class="add-student-button"
			/>
			
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
				
				<!-- Iterate over the list of student -->	
				<c:forEach var = "student" items = "${Student_List}">
				
				<!-- Set up an Update link for each student -->
				<c:url var = "UpdateLink" value = "/StudentControllerServlet">
						<c:param name="command" value="LOAD"></c:param>
						<c:param name="studentId" value="${student.id}"></c:param>
				</c:url>
				
				<!-- Set up a Delete link for each student -->
				<c:url var = "deleteLink" value = "/StudentControllerServlet">
						<c:param name="command" value="DELETE"></c:param>
						<c:param name="studentId" value="${student.id}"></c:param>
				</c:url>
				
					<tr>
						<td> ${student.firstName} </td>
						<td> ${student.lastName} </td>
						<td> ${student.email} </td>
						<td> <a href="${UpdateLink}">Update</a> 
						   | <a href="${deleteLink}"
						        onclick=" if (!(confirm('Are you sure?'))) return false"
						     >Delete</a>
						</td>
					</tr>
				</c:forEach>
			
				</table>
			</div>
		</div>
				
		</body>
</html>