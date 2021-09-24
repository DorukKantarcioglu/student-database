package com.cigna.studentdatabase.web;

import com.cigna.studentdatabase.domain.Enrollment;
import com.cigna.studentdatabase.service.EnrollmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EnrollmentController.class)
public class EnrollmentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    EnrollmentService enrollmentService;

    @Test
    void testGetEnrollments() throws Exception {
        // given
        UUID id = UUID.randomUUID();
        List<Enrollment> testList = Arrays
                .asList(new Enrollment(id, "Test Course 1"), new Enrollment(id, "Test Course 2"));
        when(enrollmentService.getEnrollments(id)).thenReturn(testList);
        // then
        mockMvc.perform(get("/api/v1/enrollments/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(new ObjectMapper().writeValueAsString(testList)));
    }

    @Test
    void testEnrollStudent() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(post("/api/v1/enrollments/" + id + "?code=Test Code"))
                .andExpect(status().isOk());
    }

    @Test
    void testDelistStudent() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(delete("/api/v1/enrollments/" + id + "?code=Test Code"))
                .andExpect(status().isOk());
    }
}
