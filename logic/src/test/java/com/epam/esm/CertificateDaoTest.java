package com.epam.esm;


import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.model.Certificate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestJdbcConfig.class)
@Transactional
public class CertificateDaoTest {


    private static final Certificate FIRST_CERTIFICATE = new Certificate("test",
            "description", BigDecimal.TEN, new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis()), 3);
    private static final Certificate TEST_CERTIFICATE = new Certificate("create",
            "description", BigDecimal.TEN, new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis()), 3);
    private final CertificateDao certificateDao;

    @Autowired
    public CertificateDaoTest(
            CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Test
    public void testCreateCertificateShouldCreate() {
        certificateDao.create(TEST_CERTIFICATE);
        Optional<Certificate> giftCertificate = certificateDao
                .findByName(TEST_CERTIFICATE.getName());
        assertTrue(giftCertificate.isPresent());
    }

    @Test
    public void testDeleteByIdShouldDelete() {
        certificateDao.deleteById(1);

        boolean isExist = certificateDao.findById(1).isPresent();
        Assertions.assertFalse(isExist);
    }


    @Test
    public void testFindByIdShouldFind() {
        Optional<Certificate> giftCertificate = certificateDao.findByName(
                FIRST_CERTIFICATE.getName());
        assertEquals(FIRST_CERTIFICATE.getName(), giftCertificate.get().getName());
    }

//    @Test
//    public void testUpdateByIdShouldUpdate() {
//        FIRST_CERTIFICATE.setName("newTest");
//        certificateDao.updateCertificateById(
//                1,new CertificateDto(FIRST_CERTIFICATE,new HashSet<>()));
//        assertEquals(FIRST_CERTIFICATE.getName(), "newTest");
//    }



    @Test
    public void testFindByNameShouldFind() {
        Optional<Certificate> giftCertificate = certificateDao.findByName(
                FIRST_CERTIFICATE.getName());
        assertEquals(FIRST_CERTIFICATE.getName(), giftCertificate.get().getName());
    }


}




