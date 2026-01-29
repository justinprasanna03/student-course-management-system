package com.student.student_course_management.service;

import com.student.student_course_management.dto.StudentResponseDTO;
import com.student.student_course_management.exception.CourseNotFoundException;
import com.student.student_course_management.exception.StudentNotFoundException;
import com.student.student_course_management.model.Student;
import com.student.student_course_management.model.Course;
import com.student.student_course_management.repository.StudentRepository;
import com.student.student_course_management.repository.CourseRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository,
                          CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // CREATE STUDENT
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // GET ALL STUDENTS
    public List<StudentResponseDTO> getAllStudents() {

        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> response = new ArrayList<>();

        for (Student student : students) {
            response.add(mapToDTO(student));
        }

        return response;
    }


    // GET STUDENT BY ID
    public StudentResponseDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));

        return mapToDTO(student);
    }


    // ASSIGN COURSE TO STUDENT
    public Student assignCourseToStudent(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id" + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id" + courseId ));

        student.getCourses().add(course);
        course.getStudents().add(student); // good practice

        return studentRepository.save(student);
    }

    private StudentResponseDTO mapToDTO(Student student) {

        List<String> courseNames = new ArrayList<>();

        for (Course course : student.getCourses()) {
            courseNames.add(course.getCourseName());
        }

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                courseNames
        );
    }


}
