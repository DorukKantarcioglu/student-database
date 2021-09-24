package com.cigna.studentdatabase.service;

import com.cigna.studentdatabase.domain.Course;
import com.cigna.studentdatabase.domain.Student;
import com.cigna.studentdatabase.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService
{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(UUID id) {
        return studentRepository.getStudent(id);
    }

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }

    public int addStudent(Student student) {
        return studentRepository.addStudent(student);
    }

    public int removeStudent(UUID id) {
        return studentRepository.removeStudent(id);
    }

    public List<Course> getCourses(UUID id) {
        return studentRepository.getCourses(id);
    }
}
