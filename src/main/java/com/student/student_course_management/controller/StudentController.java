package com.student.student_course_management.controller;

import com.student.student_course_management.dto.StudentResponseDTO;
import com.student.student_course_management.model.Student;
import com.student.student_course_management.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE STUDENT
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // GET ALL STUDENTS
    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }



    // GET STUDENT BY ID
    @GetMapping("/{id}")
    public StudentResponseDTO getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }


    @PostMapping("/{studentId}/courses/{courseId}")
    public Student assignCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return studentService.assignCourseToStudent(studentId, courseId);
    }



}
