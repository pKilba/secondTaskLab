package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CertificateWithTag {

    private static final RowMapper<Certificate> ROW_MAPPER = new BeanPropertyRowMapper<>(Certificate.class);

    private static final String SQL_INSERT_CERTIFICATE =
            "INSERT INTO certificates_tags" +
                    " (certificate_id, tag_id) VALUES(?,?)";


    private static final String SQL_FIND_ALL =
            "SELECT tag_id, certificate_id FROM certificates_tags";

    private static final String SQL_FIND_BY_CERTIFICATE_ID =
            SQL_FIND_ALL + " WHERE certificate_id = ?";

    private static final String SQL_FIND_BY_TAG_ID =
            "SELECT * FROM certificates\n" +
                    "    join certificates_tags\n" +
                    "        on certificates.id= certificates_tags.certificate_id where tag_id = ? ";
    private final CertificateDao certificateDao;
    private final TagDao tagDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    CertificateWithTag(CertificateDao certificateDao, TagDao tagDao, JdbcTemplate jdbcTemplate) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Set<Certificate> findCertificateByTagId(int id) {
        Set<Certificate> query = new HashSet<>(jdbcTemplate.query
                (SQL_FIND_BY_TAG_ID, ROW_MAPPER, id));
        return query;
    }

    public void create(Certificate certificate, Set<Tag> tags) {

        //todo перенести часть логики в сервер  мне тут цикл
        for (Tag tag : tags) {
            jdbcTemplate.update(SQL_INSERT_CERTIFICATE,
                    certificateDao.findByName(certificate.getName()).get().getId(),
                    tagDao.findByName(tag.getName()).get().getId()
            );
        }
    }

}
