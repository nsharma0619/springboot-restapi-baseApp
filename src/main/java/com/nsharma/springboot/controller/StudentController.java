package com.nsharma.springboot.controller;

import com.nsharma.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1, "Neeraj", "Sharma");

//        return new ResponseEntity<>(student, HttpStatus.OK);
//        return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header", "new-header").body(student);
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Neeraj", "Sharma"));
        students.add(new Student(2, "Neeraj", "Sharma"));
        students.add(new Student(3, "Neeraj", "Sharma"));
        students.add(new Student(4, "Neeraj", "Sharma"));
        return ResponseEntity.ok(students);
    }

    // Spring boot rest API with path variable
    // {id} - URI template variable
    @GetMapping("/{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId){
        Student student = new Student(studentId, "Neeraj", "sharma");
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API with Request Param
    // http://localhost:8080/students/query?id=1
    @GetMapping("/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id){
        Student student = new Student(id, "Neeraj", "sharma");
        return ResponseEntity.ok(student);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int studentId, @RequestBody Student student){
        System.out.println(studentId);
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("student deleted");
    }
}
