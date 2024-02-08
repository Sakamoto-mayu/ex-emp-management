package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {
    /**
     * SQL
     */
    private static final String LOAD = """
            SELECT id,name,mail_address,password
            FROM administrators
            WHERE id = :id;
                        """;
    private static final String INSERT = """
            INSERT INTO administrators(name,mail_address,password)
            VALUES (:name, :mailAddress, :password );
                """;
    private static final String FIND_BY_MAIL_PASSWORD = """
            SELECT id,name,mail_address,password
            FROM administrators
            WHERE mail_address = :mailAddress AND password = :password
                """;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, rowNum) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    @Autowired
    NamedParameterJdbcTemplate template;

    /**
     * INSERT
     */
    public void insert(Administrator administrator) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(administrator);
        template.update(INSERT, params);
    }

    /**
     * idで1件取得
     * 
     * @param id
     * @return
     */
    public Administrator load(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(LOAD, params, ADMINISTRATOR_ROW_MAPPER);
    }

    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress).addValue("password", password);
        List<Administrator> administratorList = template.query(FIND_BY_MAIL_PASSWORD, params, ADMINISTRATOR_ROW_MAPPER);
        if (administratorList.size() == 0) {
            return null;
        }
        return administratorList.get(0);
    }
}
