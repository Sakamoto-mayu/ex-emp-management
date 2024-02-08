package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {
    /**
     * SQL
     */

    private static final String FIND_ALL = """
            SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count
            FROM employees
            ORDER BY hire_date
                """;
    private static final String LOAD = """
            SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count
            FROM employees
            WHERE id = :id;
                        """;
    private static final String UPDATE = """
            UPDATE employees
            SET name=:name,image=:image,gender=:gender,hire_date=:hireDate,mail_address=:mailAddress,zip_code=:zipCode,address=:address,telephone=:telephone,salary=:salary,characteristics=:characteristics,dependents_count=:dependentsCount
            WHERE id = :id
                """;

    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getObject("hire_date", LocalDate.class));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        return employee;
    };

    @Autowired
    NamedParameterJdbcTemplate template;

    public List<Employee> findAll() {
        List<Employee> employeeList = null;
        try {
            employeeList = template.query(FIND_ALL, EMPLOYEE_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public Employee load(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(LOAD, params, EMPLOYEE_ROW_MAPPER);
    }

    public void update(Employee employee) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(employee);
        template.update(UPDATE, params);
    }
}
