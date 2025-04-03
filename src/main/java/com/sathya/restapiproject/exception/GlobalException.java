//package com.sathya.restapiproject.exception;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//@RestControllerAdvice
//public class GlobalException {
//    
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidExceptions(MethodArgumentNotValidException exception) {
//        Map<String, String> errorsMap = new HashMap<>();
//
//        // Extract field-specific error messages
//        exception.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errorsMap.put(fieldName, errorMessage);
//        });
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .header("info", "Error Handling")
//                .body(errorsMap);
//    }
//    
//    
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Map<String, String>> handleIllegalArgumentExceptions(IllegalArgumentException exception) {
//        Map<String, String> errorsMap = new HashMap<>();
//            errorsMap.put("error",exception.getMessage() );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .header("info", "Error Handling")
//                .body(errorsMap);
//    }
//}
