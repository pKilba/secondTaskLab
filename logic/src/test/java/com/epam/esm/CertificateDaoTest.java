package com.epam.esm;


import com.epam.esm.dao.CertificateDao;
import com.epam.esm.model.Certificate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestJdbcConfig.class)
@Transactional
public class CertificateDaoTest {

    private final CertificateDao certificateDao;

    @Autowired
    public CertificateDaoTest(
            CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }
    @Test
    public void dd(){
        System.out.println("f");
    }

}




