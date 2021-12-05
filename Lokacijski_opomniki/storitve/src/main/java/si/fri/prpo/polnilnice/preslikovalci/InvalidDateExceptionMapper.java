package si.fri.prpo.polnilnice.preslikovalci;

import si.fri.prpo.polnilnice.izjeme.InvalidDateException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidDateExceptionMapper implements ExceptionMapper<InvalidDateException> {

    @Override
    public Response toResponse(InvalidDateException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("{\"napaka\":\"" + exception.getMessage() + "\"}")
                .build();
    }
}
