package com.jspiders.springrest.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.TransactionException;
import org.springframework.stereotype.Repository;

import com.jspiders.springrest.dto.Student;

@Repository
public class StudentDAO implements StudentDAOInterface {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	private static void openConnections() {
		entityManagerFactory = Persistence.createEntityManagerFactory("student");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	private static void closeConnections() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (entityTransaction != null) {
			try {
				entityTransaction.rollback();
			} catch (TransactionException e) {
				System.out.println("Transaction already commited.");
			}
		}
	}

	@Override
	public Student insert(Student student) {
		openConnections();
		entityTransaction.begin();
		entityManager.persist(student);
		entityTransaction.commit();
		closeConnections();
		return student;
	}

	@Override
	public Student search(int id) {
		openConnections();
		entityTransaction.begin();
		Student student = entityManager.find(Student.class, id);
		closeConnections();
		return student;
	}

}