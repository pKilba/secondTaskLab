package com.epam.esm.dao;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.model.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CertificateDao {

    private static final RowMapper<Certificate> ROW_MAPPER = new BeanPropertyRowMapper<>(Certificate.class);

    private static final String SQL_DELETE_CERTIFICATE_BY_ID =
            "DELETE FROM certificates WHERE id=?";

    private static final String SQL_INSERT_CERTIFICATE =
            "INSERT INTO certificates (name, description, price,  duration) VALUES(?,?,?,?)";
    private static final String SQL_FIND_ALL =
            "SELECT id, name, description, price, duration, create_date, last_update_date " +
                    "FROM certificates";

    private static final String SQL_FIND_CERTIFICATE_BY_ID = SQL_FIND_ALL + " WHERE id=?";
    private static final String SQL_FIND_CERTIFICATE_BY_NAME = SQL_FIND_ALL + " WHERE name=?";
    private static final String SQL_FIND_CERTIFICATE_BY_PART_NAME = SQL_FIND_ALL + " WHERE name LIKE ?";
    private static final String SQL_FIND_CERTIFICATE_BY_PART_DESCRIPTION = SQL_FIND_ALL + " WHERE descriptor like ?";

    private static final String SQL_UPDATE_CERTIFICATE_PART_QUERY =
            "UPDATE certificates SET last_update_date=NOW()";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CertificateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long create(Certificate giftCertificate) {
        return jdbcTemplate.update(SQL_INSERT_CERTIFICATE,
                giftCertificate.getName(),
                giftCertificate.getDescription(),
                giftCertificate.getPrice(),
                giftCertificate.getDuration());
    }


    public List<Certificate> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL + " ORDER BY name", ROW_MAPPER);
    }

    public Optional<Certificate> findById(long id) {
        return jdbcTemplate.query(SQL_FIND_CERTIFICATE_BY_ID, ROW_MAPPER, id).stream().findAny();
    }

    public Optional<Certificate> findByName(String name) {
        return jdbcTemplate.query(SQL_FIND_CERTIFICATE_BY_NAME, ROW_MAPPER, name).stream().findFirst();
    }


    public Optional<Certificate> findByPartDescription(String description) {
        return jdbcTemplate.query(SQL_FIND_CERTIFICATE_BY_PART_DESCRIPTION, ROW_MAPPER, "%" + description + "%").stream().findFirst();
    }

    public Optional<Certificate> findByPartName(String name) {
        return jdbcTemplate.query(SQL_FIND_CERTIFICATE_BY_PART_NAME, ROW_MAPPER, "%" + name + "%").stream().findFirst();
    }

    public void updateCertificateById(int id, CertificateDto certificateDto) {
        Certificate certificate = certificateDto.getCertificate();
        Certificate pastCertificate = findById(id).get();
        String s = SQL_UPDATE_CERTIFICATE_PART_QUERY;
        List<Object> values = new ArrayList();
        int duration;
        if (!pastCertificate.getName().equals(certificate.getName()) && certificate.getName() != null) {
            s += ",name = ?";
            values.add(certificate.getName());
        }
        if (!pastCertificate.getDescription().equals(certificate.getDescription()) && certificate.getDescription() != null) {
            s += ",descriptor = " + certificate.getDescription();
            values.add(certificate.getDescription());
        }
        if (!pastCertificate.getPrice().equals(certificate.getPrice()) && certificate.getPrice() != null) {
            s += ", price = " + certificate.getPrice();
            values.add(certificate.getPrice());
        }
        if (!(certificate.getDuration() == pastCertificate.getDuration()) && certificate.getDuration() != 0) {
            s += ", duration = " + certificate.getDuration();
            values.add(certificate.getDuration());
        }
        s += " WHERE id = ?";
        values.add(id);
        jdbcTemplate.update(s, values.toArray());
    }


    public void deleteById(long id) {
        jdbcTemplate.update(SQL_DELETE_CERTIFICATE_BY_ID, id);
    }


}
