package com.epam.esm;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.CertificateWithTag;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.NotFoundEntityException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.CertificateServiceImpl;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.CertificateValidator;
import com.epam.esm.validator.TagValidator;
import com.epam.esm.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CertificateServiceTest {
    private CertificateDao certificateDao;
    private Validator<Certificate> certificateValidator;
    private CertificateServiceImpl certificateService;
    private TagDao tagDao;
    private Validator<Tag> tagValidator;
    private CertificateWithTag certificateWithTag;
    private TagService tagService;

    private static final Certificate CERTIFICATE = new Certificate("testTest",
            "description", BigDecimal.TEN, new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis()), 3);
    private static final CertificateDto CERTIFICATE_DTO
            = new CertificateDto(CERTIFICATE, new HashSet<>());


    @BeforeEach
    public void initMethod() {
        certificateDao = Mockito.mock(CertificateDao.class);
        certificateValidator = Mockito.mock(CertificateValidator.class);
        tagDao = Mockito.mock(TagDao.class);
        certificateWithTag = Mockito.mock(CertificateWithTag.class);
        tagValidator = Mockito.mock(TagValidator.class);
        tagService = Mockito.mock(TagService.class);
        certificateService = new CertificateServiceImpl(certificateDao, tagDao, (TagValidator) tagValidator,
                (CertificateValidator) certificateValidator, certificateWithTag, tagService); //Mockito.mock(CertificateServiceImpl.class);
    }


    @Test
    public void testFindAll_ShouldGetAll() {
        certificateService.findAll();
        verify(certificateDao).findAll();
    }

    @Test
    public void testCreate_ShouldCreateWhenNotExistAndValid() {
        when(certificateValidator.isValid(any())).thenReturn(true);
        when(tagValidator.isValid(any())).thenReturn(true);
        when(certificateDao.findByName(anyString())).thenReturn(Optional.empty());
        certificateService.create(CERTIFICATE_DTO);
        verify(certificateDao).create(CERTIFICATE);
    }

//    @Test
//    public void testFindByName_ShouldGetCertificate(){
//        certificateService.findByName("test");
//        verify(certificateDao).findByName("test");
//    }

    @Test
    public void testCreateShouldThrowsNotValidTagExceptionWhenTagNotCorrect() {
        when(certificateValidator.isValid(any())).thenReturn(false);
        //todo
        assertThrows(Exception.class, () -> certificateService.create(new CertificateDto()));
    }

    @Test
    public void testFindById_ShouldGetWhenFound() {
        when(certificateDao.findById(anyLong())).thenReturn(Optional.of(CERTIFICATE));
        certificateService.findById(1);
        verify(certificateDao).findById(1);
    }

    @Test
    public void testFindByName_ShouldGetWhenFound() {
        when(certificateDao.findByName(anyString())).thenReturn(Optional.of(CERTIFICATE));
        certificateService.findByName("TEST");
        verify(certificateDao).findByName("TEST");
    }

    @Test
    public void testDeleteById_ShouldDeletedWhenFound() {
        when(certificateDao.findById(anyLong())).thenReturn(Optional.of(CERTIFICATE));
        certificateService.deleteById(1);
        verify(certificateDao).deleteById(1);
    }

    @Test
    public void testCreateShouldThrowsDuplicateEntityExceptionWhenExist() {
        when(certificateValidator.isValid(any())).thenReturn(true);
        when(certificateDao.findByName(anyString())).thenReturn(Optional.of(CERTIFICATE));
        assertThrows(DuplicateEntityException.class, () -> certificateService.create(CERTIFICATE_DTO));
    }

    @Test
    public void testFindByIdShouldThrowsNotFoundEntityExceptionWhenNotFound() {
        when(certificateDao.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundEntityException.class, () -> certificateService.findById(-1));
    }

    @Test
    public void testUpdateByIds_ShouldUpdateWhenFound() {
        when(certificateDao.findById(anyLong())).thenReturn(Optional.of(CERTIFICATE));
        certificateService.updateById(1, CERTIFICATE_DTO);
        verify(certificateDao).updateCertificateById(anyInt(), any());
    }

}
