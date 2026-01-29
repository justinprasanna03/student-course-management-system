package com.student.student_course_management.service;

import com.student.student_course_management.dto.CourseResponseDTO;
import com.student.student_course_management.model.Course;
import com.student.student_course_management.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<CourseResponseDTO> getAllCourses() {

        List<Course> courses = courseRepository.findAll();
        List<CourseResponseDTO> response = new ArrayList<>();

        for (Course course : courses) {
            response.add(mapToDTO(course));
        }

        return response;
    }


    private CourseResponseDTO mapToDTO(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getCourseName()
        );
    }

}
