package com.martin.Jinni.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3426099082467428061L;
	private long id;
	private String firstName;
	private String lastName;
	private String gender;
	private float avg;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(long id, String firstName, String lastName, String gender, float avg){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.avg = avg;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	
}
