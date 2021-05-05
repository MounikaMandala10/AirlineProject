package com.mydept.dao;


import java.util.List;

import DepartmentAlreadyExistException.DepartmentAlreadyExistException;
import DepartmentAlreadyExistException.DepartmentNotFoundException;

public interface DepartmentDAO
{
	void addDepartment(Department dRef) throws DepartmentAlreadyExistException;	
	Department findDepartment(int dno) ;
	List<Department> findDepartments();			
	void modifyDepartment(Department dRef)	throws DepartmentNotFoundException;	
	void removeDepartment(Department dRef);  


}
