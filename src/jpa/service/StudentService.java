package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO{
	
	private static SessionFactory factory() {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		return factory;
		
	}
	
	
	
	public List<Student> getAllStudents() {
		
		SessionFactory factory = factory();
		
		try {
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		
		List<Student> theStudents = session.createQuery("from Student", Student.class).getResultList();
		session.getTransaction().commit();
		return theStudents;
		
		
		}
		finally {
			factory.close();
		}
		
		
		
	}
	
	public Student getStudentByEmail(String sEmail) {
		
		Student tempStudent = new Student();
		
		List<Student> students = getAllStudents();
		
		for(Student stByEmail : students) {
			
			if(stByEmail.getsEmail().equals(sEmail)) {
				
				tempStudent = stByEmail;
			}
		}
		return tempStudent;
		
	}
	
	public boolean validateStudent(String sEmail, String sPass) {
		
		boolean valid = false;
		
		List<Student> students = getAllStudents();
		
		for(Student validStudent : students) {
			
			if(validStudent.getsEmail().equals(sEmail)) {
				
				if(validStudent.getsPass().equals(sPass)) {
					valid = true;
				}
			}
		}
		return valid;
	}
	
	public Course getCourseById(int cId) {
		
		SessionFactory factory = factory();
		
		try {
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		Course tempCourse = session.get(Course.class, cId);
				
		session.getTransaction().commit();
		
		return tempCourse;
		}
		finally {
			factory.close();
		}
		
	}
	
	public void registerStudentToCourse(String sEmail, int cId) {
		SessionFactory factory = factory();
		
		
		
		try { 
			Session session = factory.getCurrentSession();
		
			session.beginTransaction();
		
		
			Student theStudent = getStudentByEmail(sEmail);
			
			Course theCourse = getCourseById(cId);
			
//			String s = theCourse.toString();
			
			boolean found = false;
			
			List<Course> courses = theStudent.getsCourses();
			
			for(Course result : courses ) {
				if(result.getCid() == theCourse.getCid()) {
					
					found = true;
					break;
				}
			}
			
			if(found) {
				
				
				System.out.println("This course is already registered");
				System.out.println("Try register different course again");
				
			}
			else {
				List<Course> scourses = theStudent.getsCourses();
				scourses.add(getCourseById(cId));
				session.update(theStudent);
				session.getTransaction().commit();
		
			}
			
		} finally
		{
			factory.close();
		}
			
		} 
		
	
	public List<Course> getStudentCourses(String sEmail){
		
		
		
		SessionFactory factory = factory();
		
		try {
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		Student tempStudent = getStudentByEmail(sEmail);
		
		Student theStudent = session.get(Student.class, tempStudent.getId());
		
	
		 List<Course> stCourses =theStudent.getsCourses();
		
		return stCourses;
		}
		finally {
			factory.close();
		}
	}

}
