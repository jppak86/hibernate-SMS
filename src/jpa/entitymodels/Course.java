package jpa.entitymodels;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "course")
public class Course {
// define our fields
// define constructors // define getter setters // define tostring
// annotate fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int cId;

	@Column(name = "course_name")
	private String cName;

	@Column(name = "instructor_name")
	private String cInstructorName;
	
	@ManyToMany(fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType. MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="course_student", joinColumns=@JoinColumn(name="course_id"), inverseJoinColumns=@JoinColumn(name="student_id") )
	private List<Student> students;
	
	
	


	public List<Student> getStudents() {
		return students;
	}



	public void setStudents(List<Student> students) {
		this.students = students;
	}



	public Course() {
	}

	

	public Course(String cName, String cInstructorName) {

		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}



	public int getCid() {
		return cId;
	}



	public String getcName() {
		return cName;
	}



	public String getcInstructorName() {
		return cInstructorName;
	}



	public void setCid(int cId) {
		this.cId = cId;
	}



	public void setcName(String cName) {
		this.cName = cName;
	}



	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}



	@Override
	public String toString() {
		return "Course [cId=" + cId + ", cName=" + cName + ", cInstructorName=" + cInstructorName + "]";
	}



	
}
