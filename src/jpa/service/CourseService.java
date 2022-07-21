package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class CourseService implements CourseDAO {
	
	private static SessionFactory factory() {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		return factory;
		
	}
	
	public List<Course> getAllCourses() {
		
		SessionFactory factory = factory();
		
		try {
			
			Session session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			List<Course> allCourses = session.createQuery("from Course", Course.class).getResultList();
			
			session.getTransaction().commit();
			
			return allCourses;
			
			
		}
		
		finally {
			factory.close();
		}
		
	}

}
