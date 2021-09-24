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
public class EnrollmentRepository
{
    private final JdbcTemplate jdbcTemplate;

    static class EnrollmentRowMapper implements RowMapper<Enrollment>
    {
        @Override
        public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Enrollment(rs.getObject("id", UUID.class), rs.getString("code"));
        }
    }

    @Autowired
    public EnrollmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Enrollment> getEnrollments(UUID id) {
        return jdbcTemplate.
                query("select * from enrollment where id = ?", new EnrollmentRowMapper(), id);
    }

    public void enrollStudent(UUID id, String code) {
        jdbcTemplate.update("insert into enrollment values(?, ?)", id, code);
    }

    public void delistStudent(UUID id, String code) {
        jdbcTemplate.update("delete from enrolLment where id = ? and code = ?", id, code);
    }

}
