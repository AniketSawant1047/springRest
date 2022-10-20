package com.jspiders.springrest.dao;

import com.jspiders.springrest.dto.Student;

public interface StudentDAOInterface {

	Student insert(Student student);
	
	Student search(int id);
}