package com.martin.Jinni.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.martin.Jinni.model.ErrorMessage;

@Provider
public class NoDataExceptionMapper implements ExceptionMapper<NoDataException> {

	@Override
	public Response toResponse(NoDataException e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404);
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
