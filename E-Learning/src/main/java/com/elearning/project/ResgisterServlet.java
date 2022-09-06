package com.elearning.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResgisterServlet
 */
@WebServlet("/ResgisterServlet")
public class ResgisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
        String URL="jdbc:mysql://localhost:3306/elearning";
        
        PrintWriter out = response.getWriter();
        
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver");  //load the driver
        
        Connection conn = DriverManager.getConnection(URL,"root","Vikas@2001");
        
        
       
        Statement s = conn.createStatement();
        Statement s1 = conn.createStatement();
        
    
        response.setContentType("text/html");
        
        
        String username=request.getParameter("username");

        String password=request.getParameter("password"); 
        
        String email=request.getParameter("email");
        
        String confirm=request.getParameter("confirmPass");
        if(confirm.equals(password)) {

        String sql= "insert into signup values('" + username + "',"+"md5('" + password +"'),'"+email+"')";
        s.executeUpdate(sql);
        out.println("register SuccessFully<br>Please Login");
        RequestDispatcher rd=request.getRequestDispatcher("login.html");

        rd.include(request,response);
        
    

	}
        else{
        	
        	out.println(" <h3>Password and Confirm password must be same<br>Plese try again</h3>");
        	 RequestDispatcher rd=request.getRequestDispatcher("Registration.html");

             rd.include(request,response);
        	}
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            
        }
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
		
		response.setContentType("text/html");

        PrintWriter out=response.getWriter();
        
	

       // RequestDispatcher rd=request.getRequestDispatcher("index.html");

        //rd.include(request,response);
	}

}