//package learn.guidr.controllers;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    // TODO: Add other exceptions to handle
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
//        return new ResponseEntity<>(
//                new ErrorResponse("Sorry, something went wrong."), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
