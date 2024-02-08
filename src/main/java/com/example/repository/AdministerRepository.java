package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administer;

@Repository
public class AdministerRepository {
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

    private static final RowMapper<Administer> ADMINISTER_ROW_MAPPER = (rs, rowNum) -> {
        Administer administer = new Administer();
        administer.setId(rs.getInt("id"));
        administer.setName(rs.getString("name"));
        administer.setMailAddress(rs.getString("mail_address"));
        administer.setPassword(rs.getString("password"));
        return administer;
    };

    @Autowired
    NamedParameterJdbcTemplate template;

    /**
     * INSERT
     */
    public void insert(Administer administer) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(administer);
        template.update(INSERT, params);
    }

    /**
     * idで1件取得
     * 
     * @param id
     * @return
     */
    public Administer load(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(LOAD, params, ADMINISTER_ROW_MAPPER);
    }

    public Administer findByMailAddressAndPassword(String mailAddress, String password) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress).addValue("password", password);
        List<Administer> administerList = template.query(FIND_BY_MAIL_PASSWORD, params, ADMINISTER_ROW_MAPPER);
        if (administerList.size() == 0) {
            return null;
        }
        return administerList.get(0);
    }
}
