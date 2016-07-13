package com.martin.Jinni.exceptions;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.martin.Jinni.model.ErrorMessage;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 400);
		return Response.status(Status.BAD_REQUEST)
				.entity(errorMessage)
				.build();
	}

}
