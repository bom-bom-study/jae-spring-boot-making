package me.jae57.woodywoody.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler({ScentNotFoundException.class, FamilyNotFoundException.class})
    public ResponseEntity<ErrorDetail> notFound(RuntimeException re) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setMessage(re.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler(DuplicateScentIdException.class)
    public ResponseEntity<ErrorDetail> duplicateId(RuntimeException re) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setMessage(re.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetail);
    }

    @ExceptionHandler(NoChangeException.class)
    public ResponseEntity<ErrorDetail> noChange(RuntimeException re) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setMessage(re.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(errorDetail);
    }
}
