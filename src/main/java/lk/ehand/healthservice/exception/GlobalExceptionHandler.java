package lk.ehand.healthservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lk.ehand.healthservice.exception.ApiException;
import lk.ehand.healthservice.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiException> handleValidationError(ResourceNotFoundException ex) {
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

       ApiException err = new ApiException(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Resource Not Found" ,
                details);
        return new ResponseEntity<>(err, err.getStatus());
    }


    @Override
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                     HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        ApiException err =new ApiException(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "VALIDATION FAILED",details
        );
        return new ResponseEntity<>(err, err.getStatus());

    }
}
