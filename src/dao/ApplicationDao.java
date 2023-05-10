package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import beans.Student;

public class ApplicationDao {
	private DataSource dataSource;
	
	public ApplicationDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * @return list of all Students in Database
	 */
 public List<Student> selectAllStudents(){
		// set the list for all students
		List<Student> list = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		ResultSet set= null;
		
   try{
		// get a connection to DB
	    con = this.dataSource.getConnection();
		
		// create a sql statement
		String sql = "SELECT * FROM STUDENT";
	    statement = con.createStatement();
		
		// Execute SQL query
	    set = statement.executeQuery(sql);
		
		// process the result
		while(set.next()) {
			list.add(new Student(set.getInt("STUDENT_ID"), set.getString("FIRSTNAME"), set.getString("LastName"), set.getString("Email")));
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			this.close(con, statement, set);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		return list;
	}
 
 public int addStudent(Student student){
	   
	   Connection con = null;
	   PreparedStatement statement = null;
	   ResultSet set = null;
	   int rows = 0;
	   
	   try {
		   //get a connection to DB
		   con = this.dataSource.getConnection();
		   
		   // create a sql statement
		   String sql = "INSERT INTO STUDENT (FIRSTNAME, LASTNAME, EMAIL) VALUES (?, ?, ?)";
		   statement = con.prepareStatement(sql);
		   statement.setString(1, student.getFirstName());
		   statement.setString(2, student.getLastName());
		   statement.setString(3, student.getEmail());
		   
		   // Execute sql statement
		   rows = statement.executeUpdate();
	   }catch (SQLException e) {    
	   }finally {
			try {
				this.close(con, statement, set);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	   
	   return rows;
   }
 
 public Student getStudent(int studentId) {
	 Student st = null;
	 Connection con = null;
	 PreparedStatement statement = null;
	 ResultSet set = null;
	 
	 try {
		 
	     // get connection
	     con = this.dataSource.getConnection();
	     
	     // create SQL statement
	     String sql = "SELECT * FROM STUDENT WHERE STUDENT_ID = ?";
		 statement = con.prepareStatement(sql);
		 statement.setInt(1, studentId);
		 
		 // Execute SQL Statement
		 set = statement.executeQuery();
		 
		 // Process the result set
		 
		 if(set.next()) {
			 st = new Student();
			 st.setId(set.getInt(1));
			 st.setFirstName(set.getString(2));
			 st.setLastName(set.getString(3));
			 st.setEmail(set.getString(4));
		 }else {
			 throw new Exception("Could not find student with id: " + studentId);
		 }
		 
	 }catch (SQLException e) {  
	 }catch (Exception e) {  
	 }finally {
			try {
				this.close(con, statement, set);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	 
	 return st;
	 
 }
 
 
 public int updateStudent(Student st) {
	 Connection con = null;
	 PreparedStatement statement = null;
	 ResultSet set = null;
	 int rows = 0;
	 
	 try {
		 
	     // get connection
	     con = this.dataSource.getConnection();
	     
	     // create SQL statement
	     String sql = "UPDATE STUDENT SET FIRSTNAME = ?, LASTNAME = ?, EMAIL = ?"
	     		+ " WHERE STUDENT_ID = ?";
	     
		 statement = con.prepareStatement(sql);
		 statement.setString(1, st.getFirstName());
		 statement.setString(2, st.getLastName());
		 statement.setString(3, st.getEmail());
		 statement.setInt(4, st.getId());
		 
		 // Execute SQL Statement
		 rows = statement.executeUpdate();

		 
	 }catch (SQLException e) {  
	 }catch (Exception e) {  
	 }finally {
			try {
				this.close(con, statement, set);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	 return rows;
 }
 
 public int deleteStudent(int id) {
	 Connection con = null;
	 PreparedStatement statement = null;
	 ResultSet set = null;
	 int rows = 0;
	 
	 try {
		 
	     // get connection
	     con = this.dataSource.getConnection();
	     
	     // create SQL statement
	     String sql = "DELETE FROM STUDENT WHERE STUDENT_ID = ?";
		 statement = con.prepareStatement(sql);
		 statement.setInt(1, id);

		 
		 // Execute SQL Statement
		 rows = statement.executeUpdate();

		 
	 }catch (SQLException e) {  
	 }catch (Exception e) {  
	 }finally {
			try {
				this.close(con, statement, set);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	 return rows;
 }
 
 
    private void close(Connection con, Statement statement, ResultSet set) throws SQLException{
    	if(con != null) con.close();
    	if(statement != null) statement.close();
    	if(set !=null) set.close();
    }

}
