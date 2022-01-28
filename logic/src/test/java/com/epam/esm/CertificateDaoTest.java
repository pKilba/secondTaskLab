package com.epam.esm;


import com.epam.esm.dao.CertificateDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestJdbcConfig.class)
@Transactional
public class CertificateDaoTest {
    private final CertificateDao сertificateDao;


    @Autowired
    public CertificateDaoTest(CertificateDao сertificateDao
    ) {
        this.сertificateDao = сertificateDao;
    }

    @Test
    public void ff() {

    }
}




