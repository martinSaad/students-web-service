package com.martin.Jinni.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.BadRequestException;

import org.json.JSONObject;

import com.martin.Jinni.exceptions.NoDataException;
import com.martin.Jinni.model.Student;

@SuppressWarnings("unchecked")
public class StudentService {
	
	private static Map<Long, Student> students = new HashMap<Long, Student>();
	private ExecutorService executor = Executors.newCachedThreadPool();
	private final static String FILE_DIR = "students.ser";
	private final static int MAX_SIZE = 1000;

	//upload students file to cache
	static{
		if (new File(FILE_DIR).isFile()){
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_DIR))){
				students = (HashMap<Long,Student>) in.readObject();
			}catch(IOException e){
				e.printStackTrace();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}	
		}
	}
	
	public List<Student> getAllStudents(){
		return new ArrayList<Student>(students.values());
	}
	
	public Student getStudent(long id){
		if (!checkIfStudentExits(id)){
			throw new NoDataException("student id "+id+" doesnt exits");
		}
		return students.get(id);
	}
	
	
	public String addStudent(long id, String firstName, String lastName, String gender, float avg){
		
		if (checkIfStudentExits(id)){
			throw new NoDataException("student id "+id+" already exits");
		}
		
		//check if DB is full
		if (students.size() == MAX_SIZE){
			throw new BadRequestException("DB is full");
		}
		students.put(id, new Student(id, firstName, lastName, gender, avg));
		
		//save new student to file
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				saveStudentsToFile();
				
			}
		});
		JSONObject json = new JSONObject();
		json.put("message", "student id "+id+" was added sucessfuly");
		return json.toString();
	}
	
	public String removeStudent(long id){
		Student student = students.remove(id);
		if (student == null){
			throw new NoDataException("student id "+id+" don't exits");
		}
		
		//remove student from file
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				saveStudentsToFile();
				
			}
		});
		JSONObject json = new JSONObject();
		json.put("message", "student id "+id+" was removed sucessfuly");
		return json.toString();
	}
	
	public void saveStudentsToFile(){
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_DIR))){
			out.writeObject(students);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean checkIfStudentExits(long id){
		return students.containsKey(id);
	}
}
