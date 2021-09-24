package com.cigna.studentdatabase.service;

import com.cigna.studentdatabase.domain.Enrollment;
import com.cigna.studentdatabase.domain.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnrollmentService
{
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getEnrollments(UUID id) {
        return enrollmentRepository.getEnrollments(id);
    }

    public void enrollStudent(UUID id, String code) {
        enrollmentRepository.enrollStudent(id, code);
    }

    public void delistStudent(UUID id, String code) {
        enrollmentRepository.delistStudent(id, code);
    }
}
