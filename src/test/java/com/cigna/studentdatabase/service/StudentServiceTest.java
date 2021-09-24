package com.cigna.studentdatabase.service;

import com.cigna.studentdatabase.domain.Student;
import com.cigna.studentdatabase.domain.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @Test
    void testGetStudent() {
        UUID id = UUID.randomUUID();
        when(studentRepository.getStudent(id)).thenReturn(Collections.singletonList(new Student(id, "Test")));
        assertEquals(id, studentService.getStudent(id).stream().findFirst().get().getId());
        assertEquals("Test", studentService.getStudent(id).stream().findFirst().get().getName());
    }

    @Test
    void testGetStudents() {
        List<Student> testStudents = Arrays.
                asList(new Student(UUID.randomUUID(), "Test 1"), new Student(UUID.randomUUID(), "Test 2"));
        when(studentRepository.getStudents()).thenReturn(testStudents);
        assertEquals(2, studentService.getStudents().size());
        verify(studentRepository, times(1)).getStudents();
    }

    @Test
    void testAddStudent() {
        final Student student = new Student(UUID.randomUUID(), "Test Name");
        studentService.addStudent(student);
        verify(studentRepository, times(1)).addStudent(student);
    }

    @Test
    void testRemoveStudent() {
        UUID id = UUID.randomUUID();
        studentService.removeStudent(id);
        verify(studentRepository, times(1)).removeStudent(id);
    }
}
