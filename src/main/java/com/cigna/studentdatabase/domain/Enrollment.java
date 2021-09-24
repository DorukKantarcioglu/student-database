package com.cigna.studentdatabase.domain;

import java.util.UUID;

public class Enrollment
{
    private final UUID studentId;
    private final String courseCode;

    public Enrollment(UUID studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
