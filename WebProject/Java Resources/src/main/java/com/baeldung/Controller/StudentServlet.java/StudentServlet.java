package com.baeldung.Controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baeldung.model.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

	//@WebServlet(name = "StudentServlet", urlPatterns = "/student-record")
	public class StudentServlet extends HttpServlet {
		static final String DB_URL = "jdbc:mysql://localhost:3306/student_record";
		   static final String USER = "root";
		   static final String PASS = "Prathi@!23";
		   //static final String QUERY = "select * from Student where id=122;";
		   static final String QUERY = "select * from Student where id=?;";
		   

	   //private final StudentService studentService = new StudentService();

	    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        /*String studentID = request.getParameter("id");
	        if (studentID != null) {
	            int id = Integer.parseInt(studentID);
	            studentService.getStudent(id)
	              .ifPresent(s -> request.setAttribute("studentRecord", s));
	        }*/
	    	
	    	String sidstr=request.getParameter("sid");
	    	int sid = Integer.parseInt(sidstr);
	    	try {
				Student s=m1(sid);
				request.setAttribute("s1", s);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentRecord.jsp");
		        dispatcher.forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println();
	    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        processRequest(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        processRequest(request, response);
	    }
	    
	    public static Student m1(int sid) throws ClassNotFoundException {
			   {
			    	Student s=new Student();
				      // Open a connection
					   Connection conn = null;
					   System.out.println("******* SQL demo");
				      try {
				    	  Class.forName("com.mysql.cj.jdbc.Driver");
				    	  com.mysql.cj.jdbc.Driver dr;
				    	  		conn = DriverManager.getConnection(DB_URL, USER, PASS);
					         //Statement stmt = conn.createStatement();
				    	  		PreparedStatement stmt = conn.prepareStatement(QUERY);
				    	  		stmt.setInt(1,sid);
					         ResultSet rs = stmt.executeQuery();
				         // Extract data from result set
				         while (rs.next()) {
				            // Retrieve by column name
				        	 
				        	int studentid=rs.getInt("id");
				        	String studentname=rs.getString("name");
				        	String location=rs.getString("Location");
				            System.out.println("ID: " + studentid );
				            System.out.println("Desc: " + studentname);
				            System.out.println("price: " +location );
					    	s.setId(studentid);
					    	s.setFirstName(studentname);
					    	s.setLastName(location);
				         }
				         
				         /*System.out.println("******* Stored proc demo");
				         CallableStatement outsampleproc = conn.prepareCall("{ CALL \"SampleSchema\".prodoutdemo(?) }");
				         outsampleproc.registerOutParameter(1, Types.VARCHAR);
				         outsampleproc.execute();
				         String name = outsampleproc.getString(1);
				         System.out.println(name);
				         outsampleproc.close();*/

				         
				      } catch (SQLException e) {
				         e.printStackTrace();
				      } finally {
				    	  if(conn != null) {
				    		  
				    		  try {
								conn.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    	  }
				      }
			   
			   return s;
			   }
				   
		   }
	    
	}
	

