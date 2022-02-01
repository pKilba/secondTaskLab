package com.epam.esm.service;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.model.Certificate;

import java.util.List;
import java.util.Set;

public interface CertificateService {

    /**
     * @param entity certificate and list tags
     * @return id certificate
     */
    int create(CertificateDto entity);

    /**
     * @return list of certificates
     */
    List<Certificate> findAll();

    /**
     * @param id id certificate
     * @return certificate
     */
    Certificate findById(int id);


    /**
     * @param id id certificate
     */
    void deleteById(long id);

    /**
     *
     * @param name name certificate
     * @return certificate
     */
    Set<Certificate> findByTagName(String name);

    /**
     *
     * @param id id certificate
     * @param сertificateDto certificate and set tag
     * @return certificate and set tag
     */
    CertificateDto updateById(int id, CertificateDto сertificateDto);

    /**
     *
     * @param name name certificate
     * @return certificate
     */
    Certificate findByName(String name);

    /**
     *
     * @param name name certificate
     * @return certificate
     */
    Certificate findByPartName(String name);

    Certificate findByPartDescription(String description);
}
