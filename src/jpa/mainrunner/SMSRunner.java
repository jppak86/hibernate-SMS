package jpa.mainrunner;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner {


	public static void main(String[] args) {
		
		StudentService ss = new StudentService();
		CourseService cs = new CourseService();
		Scanner menu = new Scanner(System.in);
		Student studentUser = new Student();
		
		
		
		
		System.out.println("\nAre you a(n) \n\n1.Student \n2.quit \n\nPlease, enter 1 or 2. ");
		String choice = menu.next();
		int cho = Integer.parseInt(choice);
		
		switch(cho) {
		case 1:
			System.out.println("Enter Your Email: ");
			String email =menu.next();
			
			System.out.println("Eneter Your Password: ");
			String password = menu.next();
			
			
			if(ss.validateStudent(email, password)) {
				List<Course> courses = ss.getStudentCourses(email);
				System.out.println("MyClasses:\n");
				System.out.printf("%-4s%-28s %-16s\n", "#", "COURSE NAME", "INSTRUCTOR NAME");
				System.out.println();
				
				for (Course course : courses) {
					System.out.printf("%-4s%-28s %-16s\n", course.getCid(), course.getcName(), course.getcInstructorName());
				}
				System.out.println("\n1.Register to Class \n2.Logout\n");
				String selection = menu.next();
				int sel = Integer.parseInt(selection);
				studentUser = ss.getStudentByEmail(email);
				
				switch(sel) {
				case 1:
					List<Course> myCourses = cs.getAllCourses();
					System.out.println("ALL Courses: \n");
					System.out.printf("%-4s%-30s %-20s\n\n", "ID", "COURSE NAME", "INSTRUCTOR NAME");
					for (Course course : myCourses) {
						System.out.printf("%-4s%-30s %-20s\n", course.getCid(), course.getcName(), course.getcInstructorName());
					}
					System.out.println();
					System.out.println("Enter course ID you want to register: ");
					int courseId = menu.nextInt();
					Course newCourse = ss.getCourseById(courseId);
					
					if(newCourse != null) {
						ss.registerStudentToCourse(studentUser.getsEmail(), courseId);
						
						List<Course> finalAllCourses = ss.getStudentCourses(studentUser.getsEmail());
						
						System.out.println("My Classes: ");
						System.out.println();
						System.out.printf("%-16s%-18s %-16s\n\n", "COURSE ID", "COURSE NAME", "INSTRUCTOR NAME");
						for (Course course : finalAllCourses) {
							System.out.printf("%-18s%-16s %-16s\n", course.getCid(), course.getcName(), course.getcInstructorName());
						}
						System.out.println();
						System.out.println("Registration complete!! \n\nYou have been signed out!");
						
					}
					break;
					
				case 2:
					default:
						System.out.println("\nYou have been signed out!");
					
				}
				
				
				
			} else {
				System.out.println("Validation failed");
			}
			break;
		case 2:
			System.out.println("Studen management system closed!");
			break;
			
		
		}
		
		
	}


}
