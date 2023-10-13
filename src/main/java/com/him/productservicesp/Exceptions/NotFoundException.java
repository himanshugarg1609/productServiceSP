package com.him.productservicesp.Exceptions;

/*
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
*/

/*
Another way of handling exception instead of a separate ExceptionAdvices class in controller:
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Something is not found")
 */
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
