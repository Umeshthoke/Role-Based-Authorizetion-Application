package com.umesh.RoleBasedAuthorization.controller;

import com.umesh.RoleBasedAuthorization.entity.Student;
import com.umesh.RoleBasedAuthorization.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

     @Autowired
     private StudentRepo studentRepo;

      @GetMapping("/students")
    public List<Student>getAll(){

         return studentRepo.findAll();

    }


    @PostMapping ("/students")

    public Student createStudent(@RequestBody Student student){

          return studentRepo.save(student);

    }
    @PutMapping("/students/{id}")

    public Student updateStudent(@PathVariable int id,@RequestBody Student student){

          Student existingStudent=studentRepo.findById(id).get();
          existingStudent.setName(student.getName());
          existingStudent.setMarks(student.getMarks());
          studentRepo.save(existingStudent);
          return existingStudent;
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return "Student with ID " + id + " has been deleted.";
        } else {
            return "Student with ID " + id + " does not exist.";
        }
    }

}
