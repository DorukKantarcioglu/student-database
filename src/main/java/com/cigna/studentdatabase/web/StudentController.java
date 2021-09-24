package com.cigna.studentdatabase.web;

import com.cigna.studentdatabase.domain.Course;
import com.cigna.studentdatabase.domain.Student;
import com.cigna.studentdatabase.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/students")
public class StudentController
{
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public List<Student> getStudent(@PathVariable UUID id) {
        return studentService.getStudent(id);
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}/courses")
    public List<Course> getCourses(@PathVariable UUID id) {
        return studentService.getCourses(id);
    }

    @PostMapping
    public int addStudent(@RequestParam String name) {
        return studentService.addStudent(new Student(UUID.randomUUID(), name));
    }

    @DeleteMapping("/{id}")
    public int removeStudent(@PathVariable UUID id) {
        return studentService.removeStudent(id);
    }
}
