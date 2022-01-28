package com.epam.esm.dao;

import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TagDao {
    private static final RowMapper<Tag> ROW_MAPPER = new BeanPropertyRowMapper<>(Tag.class);
    private static final String SQL_FIND_ALL = "SELECT id, name FROM tags";
    private static final String SQL_FIND_TAG_BY_ID = "SELECT id, name FROM tags WHERE id=?";
    private static final String SQL_INSERT_TAG =
            "INSERT INTO tags (name) VALUES(?)";
    private static final String SQL_FIND_TAG_BY_NAME = "SELECT id, name FROM tags WHERE name=?";
    private static final String SQL_DELETE_TAG = "DELETE FROM tags WHERE id=?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tag> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL + " ORDER BY name ", ROW_MAPPER);
    }

    public Optional<Tag> findById(long id) {
        return jdbcTemplate.query(SQL_FIND_TAG_BY_ID, ROW_MAPPER, id).stream().findFirst();
    }

    public void create(Tag tag) {

        jdbcTemplate.update(SQL_INSERT_TAG, tag.getName());
    }

    public Optional<Tag> findByName(String name) {
        return jdbcTemplate.query(SQL_FIND_TAG_BY_NAME, ROW_MAPPER, name).stream().findAny();
    }

    public void deleteById(long id) {
        jdbcTemplate.update(SQL_DELETE_TAG, id);
    }

}
