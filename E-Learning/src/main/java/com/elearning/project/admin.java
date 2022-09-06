package com.elearning.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter  out  = response.getWriter();
        out.println("Request Accepted");
        String username = request.getParameter("txtname");
        String password = request.getParameter("txtpass");
        String[]  hob = request.getParameterValues("c1");

        out.print("Welcome" + username + "response generated");

        request.setAttribute("name", username);
        request.setAttribute("password", password);
        //request.setAttribute("hobbies", hob);
        
        if(username.equals("admin")&&password.equals("admin")) { 

        RequestDispatcher disp = request.getRequestDispatcher("data");
        disp.forward(request, response);
	}
        else{
       	 out.println("wrong password");
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
