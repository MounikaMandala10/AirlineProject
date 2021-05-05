package com.mydept;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.mydept.dao.Department;
//import com.mydept.dao.DepartmentAlreadyExistException;
import com.mydept.dao.DepartmentDAOImpl;

import DepartmentAlreadyExistException.DepartmentAlreadyExistException;
import DepartmentNotFoundException.DepartmentNotFoundException;

//import com.dept.dao.Department;
//import com.dept.dao.DepartmentDAOImpl;


public class DepartmentServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
	DepartmentDAOImpl ddi = new DepartmentDAOImpl();
   
    public DepartmentServlet() {
        super();
        System.out.println("Depatment Servlet....");
    
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("\t init called...");
	
	}

	public void destroy() {
		System.out.println("\t destroy....");
	}

	public void service(ServletRequest request, ServletResponse response ) throws ServletException, IOException {
		System.out.println("Department Servlet....services()....");
		
		String buttonValue=request.getParameter("submit");
	    PrintWriter pw= response.getWriter();
	    pw.println("Button clicked Value is "+buttonValue);
	    if(buttonValue.equals("Add Dept"))
	    {
		int dno=Integer.parseInt(request.getParameter("v_deptno"));
		String dnm=request.getParameter("v_deptname");
		String dloc=request.getParameter("v_loc");
		
		Department deptObj = new Department(); 
		deptObj.setDepartmentNumber(dno);
		deptObj.setDepartmentName(dnm);
		deptObj.setDepartmentLocation(dloc); 
		try 
		{
			ddi.addDepartment(deptObj);
			pw.println("<h4>Depatment is added to the database</h4>");
		}
		catch(DepartmentAlreadyExistException e)
		{
		pw.println("<h4> Department add issuse :"+e.getMessage()+"</h4>");
	    }
	    }

	else if (buttonValue.equals("Find Dept"))
	{
		
			int dno = Integer.parseInt(request.getParameter("v_deptno"));  
			Department deptObj = ddi.findDepartment(dno);
			
			//ddi.findDepartment(deptObj);
			
	    
		
		pw.println("<table border=5 cellpadding=10 cellspacing=10>");
		pw.println("<tr>");
			//Department deptObj;
			
			pw.println("<td>"+deptObj.getDepartmentNumber()+"</td>");
			pw.println("<td>"+deptObj.getDepartmentName()+"</td>");
			pw.println("<td>"+deptObj.getDepartmentLocation()+"</td>");
		pw.println("</tr>");
		pw.println("</table>");
	}

	else if(buttonValue.equals("Find All Depts")) 
	{
		pw.println("<table border=1 cellspacing=10 cellpadding=10>");
		pw.println("<th>");		pw.println("DEPT NO");	pw.println("</th>");
		pw.println("<th>");		pw.println("DEPT NAME");pw.println("</th>");
		pw.println("<th>");		pw.println("LOCATION"); pw.println("</th>");
		List<Department> deptList = ddi.findDepartments();
		Iterator<Department>deptIter = deptList.iterator();
		
			while(deptIter.hasNext()) {
				Department deptObj = deptIter.next();
				pw.println("<tr>");
				
						pw.println("<td>");
							pw.println(deptObj.getDepartmentNumber());
						pw.println("</td>");
				
						pw.println("<td>");
							pw.println(deptObj.getDepartmentName());
						pw.println("</td>");

						pw.println("<td>");
							pw.println(deptObj.getDepartmentLocation());
						pw.println("</td>");
						
				pw.println("</tr>");
			}
		pw.println("</table>");
	}
	else if (buttonValue.equals("Modify Dept"))
	{
		int dno = Integer.parseInt(request.getParameter("v_deptno")); //to read html variable name's values 
		String dnm = request.getParameter("v_dname");
		String dloc = request.getParameter("v_loc");
		Department deptObj = new Department(); //blank one
		deptObj.setDepartmentNumber(dno);
		deptObj.setDepartmentName(dnm);
		deptObj.setDepartmentLocation(dloc);
		try {
		ddi.modifyDepartment(deptObj);
		pw.println("modified department details of:"+dno);
		} catch( DepartmentNotFoundException e)
		{
			pw.println("<h2>"+e.getMessage()+"!!!</h2>");
		}
	}
	
	else if (buttonValue.equals("Delete Dept"))
	{
		int dno = Integer.parseInt(request.getParameter("v_deptno")); 
		
		Department deptObj = new Department();
		deptObj.setDepartmentNumber(dno);
		ddi.removeDepartment(deptObj);
		pw.println("<h4> Department is deleted</h4>");	
	}
	
	pw.println("<a href='http://localhost:8080/DepartmentProject/'> Back to Welcome </a>");
		
	}
} 



