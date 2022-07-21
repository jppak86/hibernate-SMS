package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jpa.entitymodels.Student;
import jpa.service.StudentService;

public class validation_test {
	
	@Test
	public void testValidation() {
		
		Student test = new Student("aiannitti7@is.gd", "Alexandra Iannitti", "TWP4hf5j" );
		
		StudentService ss = new StudentService();
		
		boolean valid = ss.validateStudent(test.getsEmail(), test.getsPass());
		
		assertEquals(true, valid, "validation failed!");
		System.out.println("test1 is done!");
		
	}
	
	@Test
	public void testValidation2() {
		
		Student test = new Student("aiannitti7@is.gd", "Alexandra Iannitti", "TWP4hf5j" );
		
		StudentService ss = new StudentService();
		
		boolean valid = ss.validateStudent(test.getsEmail(), test.getsPass());
		
		assertEquals(false, valid, "validation failed!!");
		System.out.println("test2 is done!");
		
	}

}
