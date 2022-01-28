package com.epam.esm.service;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.model.Certificate;

import java.util.List;
import java.util.Set;

public interface CertificateService {

    int create(CertificateDto entity);

    List<Certificate> findAll();

    Certificate findById(int id);

    void deleteById(long id);

    Set<Certificate> findByTagName(String name);

    CertificateDto updateById(int id, CertificateDto сertificateDto);

    Certificate findByName(String name);

    Certificate findByPartName(String name);

    Certificate findByPartDescription(String description);
}
