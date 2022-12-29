package dao;

import model.Class;
import model.Student;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDAOImpl implements StudentDAO {

    private JdbcTemplate jdbcTemplate;

    public StudentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student getStudentById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Students WHERE studentID=?", new Object[]{id},
                    new BeanPropertyRowMapper<>(Student.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return jdbcTemplate.query("SELECT * FROM Students", new BeanPropertyRowMapper<>(Student.class));
    }
}
