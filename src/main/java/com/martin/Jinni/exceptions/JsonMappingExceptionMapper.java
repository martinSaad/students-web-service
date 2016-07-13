package com.martin.Jinni.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.martin.Jinni.model.ErrorMessage;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

	@Override
	public Response toResponse(JsonMappingException e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getPathReference(), 400);
		return Response.status(Status.BAD_REQUEST)
				.entity(errorMessage)
				.build();
	}

}
