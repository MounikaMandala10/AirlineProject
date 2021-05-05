package com.emp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Employee empObj = new Employee();
		empObj.setEmployeeNumber(7839);
		empObj.setEmployeeName("KING");
		empObj.setEmployeeJob("PRESIDENT");
		
		request.setAttribute("emp", empObj);
		RequestDispatcher rd = request.getRequestDispatcher("CoreTags.jsp");
		rd.forward(request, response);
		// These changes are seen in github....
		// All the changes are made will be seen....
		
		
	}

}
