package com.cigna.studentdatabase.service;

import com.cigna.studentdatabase.domain.Enrollment;
import com.cigna.studentdatabase.domain.EnrollmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {
    @Mock
    EnrollmentRepository enrollmentRepository;

    @InjectMocks
    EnrollmentService enrollmentService;

    @Test
    void testEnrollStudent() {
        UUID id = UUID.randomUUID();
        Enrollment enrollment = new Enrollment(id, "Test Code");
        enrollmentService.enrollStudent(id, enrollment.getCourseCode());
        verify(enrollmentRepository, times(1)).
                enrollStudent(id, enrollment.getCourseCode());
    }

    @Test
    void testDelistStudent() {
        UUID id = UUID.randomUUID();
        enrollmentService.delistStudent(id, "Test Code");
        verify(enrollmentRepository, times(1)).delistStudent(id, "Test Code");
    }
}
