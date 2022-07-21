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
@Table(name="student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_id")
	private int id;
	
	@Column(name="email")
	private String sEmail;
	
	@Column(name="name")
	private String sName;
	
	@Column(name="password")
	private String sPass;
	
	@ManyToMany(fetch=FetchType.EAGER,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="course_student",
			joinColumns=@JoinColumn(name="student_id"),
			inverseJoinColumns=@JoinColumn(name="course_id")
			)	
	private List<Course> sCourses;
	
	
	public Student() {
		
	}
	
	public Student(String sEmail, String sName, String sPass) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		
	}

	

	public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.sCourses = sCourses;
	}



	public int getId() {
		return id;
	}



	public String getsEmail() {
		return sEmail;
	}



	public String getsName() {
		return sName;
	}



	public String getsPass() {
		return sPass;
	}



	public List<Course> getsCourses() {
		return sCourses;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}



	public void setsName(String sName) {
		this.sName = sName;
	}



	public void setsPass(String sPass) {
		this.sPass = sPass;
	}



	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}



	@Override
	public String toString() {
		return "Student [id=" + id + ", sEmail=" + sEmail + ", sName=" + sName + ", sPass=" + sPass + ", sCourses="
				+ sCourses + "]";
	}



	
	
	
}
