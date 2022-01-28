package com.epam.esm.service;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.CertificateWithTag;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.NotFoundEntityException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.validator.CertificateValidator;
import com.epam.esm.validator.TagValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class CertificateServiceImpl implements CertificateService {

    private static final String CERTIFICATE_EXIST = "Certificate exist";
    private static final String CERTIFICATE_NOT_EXIST = "Certificate not exist";
    private static final String CERTIFICATE_NOT_FOUND = "Certificate not found";

    private final CertificateDao certificateDao;
    private final TagDao tagDao;
    private final TagValidator tagValidator;
    private final CertificateValidator certificateValidator;
    private final TagService tagService;
    private final CertificateWithTag certificateWithTag;


    @Autowired
    public CertificateServiceImpl(CertificateDao certificateDao, TagDao tagDao,
                                  TagValidator tagValidator,
                                  CertificateValidator certificateValidator, CertificateWithTag certificateWithTag, TagService tagService) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
        this.tagValidator = tagValidator;
        this.certificateValidator = certificateValidator;
        this.certificateWithTag = certificateWithTag;
        this.tagService = tagService;
    }


    @Override
    @Transactional
    public int create(CertificateDto certificateDto) {
        Certificate certificate = certificateDto.getCertificate();
        certificateValidator.isValid(certificate);
        validateForExistCertificates(certificate);
        Set<Tag> tags = new HashSet();
        tags.addAll(certificateDto.getTags());
        certificateDao.create(certificate);
        validateForExistTag(tags);
        certificateWithTag.create(certificate, tags);
        return 1;
    }

    private void validateForExistTag(Set<Tag> tags) {

        for (Tag tag : tags) {
            if (!tagDao.findByName(tag.getName()).isPresent()) {
                tagDao.create(tag);
            }
        }
    }

    private void validateForExistCertificates(Certificate certificate) {
        if (certificateDao.findByName(certificate.getName()).isPresent()) {
            throw new DuplicateEntityException(CERTIFICATE_EXIST);
        }
    }


    @Override
    public List<Certificate> findAll() {
        return certificateDao.findAll();
    }

    @Override
    public Certificate findById(int id) {
        Optional<Certificate> certificateOptional = certificateDao.findById(id);
        if (!certificateOptional.isPresent()) {
            throw new NotFoundEntityException("certificate not found");
        }
        return certificateOptional.get();
    }

    public Certificate findByName(String name) {
        Optional<Certificate> certificateOptional = certificateDao.findByName(name);
        if (!certificateOptional.isPresent()) {
            throw new NotFoundEntityException(CERTIFICATE_NOT_FOUND);
        }
        return certificateOptional.get();
    }


    @Override
    public Certificate findByPartDescription(String description) {
        Optional<Certificate> certificateOptional = certificateDao.findByPartDescription(description);
        if (!certificateOptional.isPresent()) {
            throw new NotFoundEntityException(CERTIFICATE_NOT_FOUND);
        }
        return certificateOptional.get();

    }

    @Override
    public Certificate findByPartName(String name) {
        Optional<Certificate> certificateOptional = certificateDao.findByPartName(name);
        if (!certificateOptional.isPresent()) {
            throw new NotFoundEntityException(CERTIFICATE_NOT_FOUND);
        }
        return certificateOptional.get();

    }


    @Override
    public void deleteById(long id) {
        Optional<Certificate> certificateOptional = certificateDao.findById(id);
        if (!certificateOptional.isPresent()) {
            throw new NotFoundEntityException(CERTIFICATE_NOT_FOUND);
        }
        certificateDao.deleteById(id);
    }

    public Set<Certificate> findByTagName(String name) {

        return certificateWithTag.findCertificateByTagId(tagDao.findByName(name).get().getId());

    }

    @Transactional
    public CertificateDto updateById(int id, CertificateDto certificateDto) {

        Certificate certificate = certificateDto.getCertificate();
        Set<Tag> tags = new HashSet();
        tags.addAll(certificateDto.getTags());
        if (!certificateDao.findById(id).isPresent()) {
            throw new NotFoundEntityException(CERTIFICATE_NOT_EXIST);
        }

        if (certificate != null) {
            certificateDao.updateCertificateById(id, certificateDto);
        }

        if (tags != null) {
            for (Tag tag : tags) {
                if (!tagDao.findByName(tag.getName()).isPresent()) {
                    tagService.create(tag);
                }
            }
            certificateWithTag.create(findById(id), tags);
        }
        return certificateDto;
    }

}
