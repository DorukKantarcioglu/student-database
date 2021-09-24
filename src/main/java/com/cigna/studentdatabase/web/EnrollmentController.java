package com.cigna.studentdatabase.web;

import com.cigna.studentdatabase.domain.Enrollment;
import com.cigna.studentdatabase.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/enrollments")
public class EnrollmentController
{
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/{id}")
    public List<Enrollment> getEnrollments(@PathVariable UUID id) {
        return enrollmentService.getEnrollments(id);
    }

    @PostMapping("/{id}")
    public void enrollStudent(@PathVariable UUID id, @RequestParam String code) {
        enrollmentService.enrollStudent(id, code);
    }

    @DeleteMapping("/{id}")
    public void delistStudent(@PathVariable UUID id, @RequestParam String code) {
        enrollmentService.delistStudent(id, code);
    }
}
