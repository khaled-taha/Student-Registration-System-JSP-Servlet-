package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import beans.Student;
import dao.ApplicationDao;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/Student_Registration_System")
	private DataSource dataSource;
	private ApplicationDao dao;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		// create an object from ApplicationDao to use beansDBUtil
	    dao = new ApplicationDao(dataSource);
	    
	}
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getParameter("command");
		if(command == null) command = "SHOW";
		
		try {
				switch (command) {
				    case "Navigate-to-add-form" -> {request.getRequestDispatcher("html/add-student-form.jsp").forward(request, response);}
					case "LOAD"   -> loadFromDB(request, response);
					case "ADD"    -> addStudent(request, response);
					case "UPDATE" -> updateStudent(request, response);
					case "DELETE" -> deleteStudent(request, response);
					default       -> getAllStudent(request, response);
					}
			}catch (ServletException | IOException e) {
		}
		
		
		
	}
	
	
	private void getAllStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// collect all students from DB in ArrayList
		ArrayList<Student> list = (ArrayList<Student>) dao.selectAllStudents();
		
		// send the list with request 
		request.setAttribute("Student_List", list);
		
		// send the request to Student_list.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("html/Student_list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		
		// collect the data from fields to Student Object
		Student student = new Student();
		student.setFirstName(request.getParameter("firstName"));
		student.setLastName(request.getParameter("lastName"));
		student.setEmail(request.getParameter("email"));
		
		// add student to DB
		int rows = this.dao.addStudent(student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("html/add-student-form.jsp");
		if(rows != 1) {
			writer.println("No row has been added");
			//  try again
			dispatcher.include(request, response);
		}else {
			// add new Student if you want to do that.
			dispatcher.forward(request, response);
		}
		
		// close the printWriter 
		writer.close();
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		
		// collect the data from fields to Student Object
		Student student = new Student();
		
		try {
			student.setId(Integer.parseInt(request.getParameter("studentId")));
			}catch (NumberFormatException e) { }
		
		student.setFirstName(request.getParameter("firstName"));
		student.setLastName(request.getParameter("lastName"));
		student.setEmail(request.getParameter("email"));
		
		// update the student
		int rows = this.dao.updateStudent(student);
		
		if(rows == 0) {
			writer.println("No row has been Updated");
			//  try again
	        RequestDispatcher dispatcher = request.getRequestDispatcher("html/Student_list.jsp");
			dispatcher.include(request, response);
		}else {
			// send them back to the list student page
			getAllStudent(request, response);
		}
		
		// close the printWriter
		writer.close();
	}

	private void loadFromDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// get the unique id of this student
		int studentId = 0;
		
		try {
		studentId = Integer.parseInt( request.getParameter("studentId"));
		}catch (NumberFormatException e) { }
		
		// get this student from the Database
		Student st = this.dao.getStudent(studentId);
		
		// add this student to the request
		if(st != null) request.setAttribute("studentObject", st);
		
		// forward the request to add-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("html/update-student-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		
		// collect the data from fields to Student Object
		int studentId = 0;
		try {
			studentId = Integer.parseInt(request.getParameter("studentId"));
			}catch (NumberFormatException e) { }
		
		
		// delete the student
		int rows = this.dao.deleteStudent(studentId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("html/Student_list.jsp");
		
		if(rows == 0) {
			writer.println("No row has been deleted");
			//  try again
			dispatcher.include(request, response);
		}else {
			// send them back to the list student page
			getAllStudent(request, response);
		}
		
		// close the printWriter
		writer.close();
	}
	
	
	
	
	

}
