package com.practice.spring.SpringBootPracticeLab.controladvice;

import com.practice.spring.SpringBootPracticeLab.exception.UserNotfoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalControllerAdvice {
//    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
//        logger.info("at handleConstraintViolationException: GlobalControllerAdvice");
//        logger.error(ex.getMessage(), ex);
        String requestSent = request.getDescription(false);
        StringBuilder content = new StringBuilder();
        content.append("Request :" + requestSent).append(",\n");
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            content.append("Invalid value provided :" + violation.getInvalidValue() + "," + "\n");
            content.append("message :" + violation.getMessage() + "\n");
        }

        return new ResponseEntity<String>(content.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotfoundException.class)
    public ResponseEntity<String> handleUserNotfoundException(UserNotfoundException ex, WebRequest request) {
        String requestSent = request.getDescription(false);
        StringBuilder content = new StringBuilder();
        content.append("Request :" + requestSent).append(",\n");
        content.append("Message : " + ex.getMessage());
        return new ResponseEntity<String>(content.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex, WebRequest request) {
        String requestSent = request.getDescription(false);
        StringBuilder content = new StringBuilder();
        content.append("Request :" + requestSent).append(",\n");
        content.append("Message : " + ex.getMessage());
        return new ResponseEntity<String>(content.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
