package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    //define @PostConstruct to load Student data only once
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Sonia", "Sing"));
        theStudents.add(new Student("Krish", "Raja"));
        theStudents.add(new Student("Beena", "Mom"));
    }

    //define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        //Creating list for EVERY request not good
        //So define a field, Load the field with data, do it only once
//        List<Student> theStudents = new ArrayList<>();
//        theStudents.add(new Student("Sonia", "Sing"));
//        theStudents.add(new Student("Krish", "Raja"));
//        theStudents.add(new Student("Beena", "Mom"));

        //just return the list, it has already been loaded by @PostConstruct
        return theStudents;
    }

    //define endpoint for "/students/{studentId}" - return student at index
    // /api/students/0
    // /api/students/1
    // /api/students/2
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        //by default path var and method param shall match

        //check studentId against list size
        if (studentId >= theStudents.size() || studentId < 0) {
            //custom exception class we made
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        //just index into list
        return theStudents.get(studentId);
        //use studentId for index
    }

    //99, -7
//    @ExceptionHandler //tells this method is an exception handler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
//        //in above, tell actual response type,exception to handle/catch
//
//        //create a StudentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        //return ResponseEntity
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); //body is error and actual status code for response
//    }
//
//    // add an exception handler to catch any exception (catch all)
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) { //handle generic exception
//        //create a StudentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST.value()); //change status code to 400
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        //return ResponseEntity
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); //body is error and actual status code for response
//    }

}
