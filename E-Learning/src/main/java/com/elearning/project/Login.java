package com.elearning.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
        String URL="jdbc:mysql://localhost:3306/elearning";
        
        PrintWriter out = response.getWriter();
        boolean status=false;
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver");  //load the driver
        
        Connection conn = DriverManager.getConnection(URL,"root","Root@2606");
        
        
       
        Statement s = conn.createStatement();
        Statement s1 = conn.createStatement();
        
    
        response.setContentType("text/html");
        
        String username=request.getParameter("txtname");

        String password=request.getParameter("txtpass");

        PreparedStatement preparedStatement = conn.prepareStatement("select * from signup where username ='"+username+"'  and password ="+"md5('" + password +"')" );
        ResultSet rs = preparedStatement.executeQuery();
        status = rs.next();
        if(status==true) {
        String sql= "insert into login values('" + username + "',"+"md5('" + password +"'))";
        s.executeUpdate(sql);
        out.println("Login SuccessFully");
        RequestDispatcher rd=request.getRequestDispatcher("course.html");

        rd.include(request,response);
        
        }
        else {
        	out.println("<h1>You are not a valid user please register<h1>");
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

        //RequestDispatcher rd=request.getRequestDispatcher("index.html");

        //rd.include(request,response);
	}

}
