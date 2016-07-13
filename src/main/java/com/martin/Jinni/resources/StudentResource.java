package com.martin.Jinni.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.martin.Jinni.model.ErrorMessage;
import com.martin.Jinni.model.Student;
import com.martin.Jinni.service.StudentService;

@Path("/students")
public class StudentResource {
	
	StudentService studentSerivce = new StudentService();
    
	@GET
	@Path("/getAllStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        return studentSerivce.getAllStudents();
	}
	
	@POST
	@Path("/addStudent")
    @Produces(MediaType.APPLICATION_JSON)
	public String addSutdent(
			@QueryParam("id") long id,
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("gender") String gender,
			@QueryParam("avg") float avg){
		
		//if missing mandatory parameter id
		if (id==0){
			ErrorMessage errorMessage = new ErrorMessage("missing mandatory parameter: id", 400);
			Response response = Response.status(Status.BAD_REQUEST)
			.entity(errorMessage)
			.build();
			throw new WebApplicationException(response);
		}
		
		return studentSerivce.addStudent(id,firstName,lastName,gender,avg);
	}
	
	@GET
	@Path("/getStudent")
    @Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@QueryParam("id") long id){
		return studentSerivce.getStudent(id);
	}
	
	@DELETE
	@Path("/deleteStudent")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String removeStudent(@QueryParam("id") long id){
		return studentSerivce.removeStudent(id);
	}
}
