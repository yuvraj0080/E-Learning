package com.elearning.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class data
 */
@WebServlet("/data")
public class data extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public data() {
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
        String query = "select * from login";
        PrintWriter out = response.getWriter();
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver");  //load the driver
        
        Connection conn = DriverManager.getConnection(URL,"root","Root@2606");
        
        
        out.println("Connection Established");
        Statement s = conn.createStatement();
        Statement s1 = conn.createStatement();
        
        //Parameterized Query
        //PreparedStatement ps =conn.prepareStatement("insert into login values(?,?)");
        
    //    pw.println("Enter your user name to save: ");
        
        //s.executeQuery(query);
    //    ps.setString(1, un);
    //    ps.setString(2, password);
        
                
    //    ps.executeUpdate();
        //System.out.println("Record Inserted");
        
        //Display the values
        response.setContentType("text/html");
        PreparedStatement pstmt2=conn.prepareStatement(query);
        
        ResultSet rs=pstmt2.executeQuery();            
        java.sql.ResultSetMetaData rsmd=rs.getMetaData();
        int colCount=rsmd.getColumnCount();
            
        //out.println("<HTML>\n<BODY bgcolor=\"lightblue\">\n<CENTER>");    



       out.println("<HTML><BODY bgcolor=wheat><center>");
        out.println("<H3>The Student information stored in the database are:</H3><BR>");            
        out.println("<TABLE BORDER=1 WIDTH=80% bgcolor=yellow>");
        out.println("<TR>");
        
        for(int i=1; i<=colCount; i++)
        {
            out.println("<TH>" + rsmd.getColumnLabel(i)+"</TH>");
        }
        
        out.println("</TR>");



       
        
        while (rs.next())
        {
            out.println("<TR>");
            
            for(int i=1;i<=colCount;i++)
            out.println("<TD>" + rs.getString(i) + "</TD>");                
            
            out.println("</TR>");
        }
        out.println("</TABLE>");
        out.println("</center></BODY>");
        out.println("</HTML>");
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
	}

}
