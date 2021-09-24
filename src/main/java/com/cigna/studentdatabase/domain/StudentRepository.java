package com.cigna.studentdatabase.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepository
{
    private final JdbcTemplate jdbcTemplate;

    static class StudentRowMapper implements RowMapper<Student>
    {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student(rs.getObject("id", UUID.class), rs.getString("name"));
        }

    }

    static class CourseRowMapper implements RowMapper<Course>
    {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Course(rs.getString("code"), rs.getString("title"));
        }
    }

    @Autowired
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> getStudents() {
        return jdbcTemplate.query("select * from student", new StudentRowMapper());
    }

    public List<Student> getStudent(UUID id) {
        return jdbcTemplate.query("select * from student where id = ?", new StudentRowMapper(), id);
    }

    public int addStudent(Student student) {
        return jdbcTemplate.update("insert into student values(?, ?)", student.getId(), student.getName());
    }

    public int removeStudent(UUID id) {
        return jdbcTemplate.update("delete from student where id = ?", id);
    }

    public List<Course> getCourses(UUID id) {
        return jdbcTemplate.
                query("select s.name, c.code, c.title from student s  inner join enrollment e on e.id = s.id inner join course c on c.code = e.code where s.id = ?", new CourseRowMapper(), id);

    }
}
