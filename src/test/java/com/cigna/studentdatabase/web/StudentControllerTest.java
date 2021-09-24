package com.cigna.studentdatabase.web;

import com.cigna.studentdatabase.domain.Student;
import com.cigna.studentdatabase.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    void testGetStudent() throws Exception {
        UUID id = UUID.randomUUID();
        List<Student> testList = Collections.singletonList(new Student(id, "Test"));
        when(studentService.getStudent(id)).thenReturn(testList);
        mockMvc.perform(get("/api/v1/students/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(new ObjectMapper().writeValueAsString(testList)));
    }

    @Test
    void testGetStudents() throws Exception {
        List<Student> testList = Arrays
                .asList(new Student(UUID.randomUUID(), "Test 1"), new Student(UUID.randomUUID(), "Test 2"), new Student(UUID.randomUUID(), "Test 3"));
        when(studentService.getStudents()).thenReturn(testList);
        mockMvc.perform(get("/api/v1/students")).
                andExpect(status().isOk()).andExpect(content().string(new ObjectMapper().writeValueAsString(testList)));
    }

    @Test
    void testAddStudent() throws Exception {
        mockMvc.perform(post("/api/v1/students?name=Test")).andExpect(status().isOk());
    }

    @Test
    void testRemoveStudent() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(delete("/api/v1/students/" + id)).andExpect(status().isOk());
    }
}
