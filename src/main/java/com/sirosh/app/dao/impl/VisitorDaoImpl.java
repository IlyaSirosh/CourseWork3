package com.sirosh.app.dao.impl;

import com.sirosh.app.dao.VisitorDao;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Illya on 5/25/17.
 */
@Repository
public class VisitorDaoImpl implements VisitorDao {

    private static final String INSERT = "INSERT INTO visitors (date,ip,page) VALUES (?,?,?)";
    private static final String SELECT_IP = "SELECT DISTINCT ip FROM visitors";
    private static final String SELECT_PAGE_VISIT_INFO = "SELECT page,COUNT(ip) AS amount FROM visitors GROUP BY page ORDER BY amount DESC";


    @Autowired
    private JdbcTemplate jdbcTemplate;



    public void add(Date date, String ip,String page) {
        jdbcTemplate.update(INSERT,date,ip,page);
    }

    public List<Pair> getPageVisitInfo() {
        return jdbcTemplate.query(SELECT_PAGE_VISIT_INFO,pairMapper);
    }

    public List<String> getVisitorsIp() {

        return jdbcTemplate.queryForList(SELECT_IP,String.class);

    }

    private RowMapper<Pair> pairMapper = new RowMapper<Pair>() {
        public Pair mapRow(ResultSet rs, int i) throws SQLException {

            return new Pair<String, Integer>(rs.getString("page"),
                                             rs.getInt("amount"));
        }
    };


}
