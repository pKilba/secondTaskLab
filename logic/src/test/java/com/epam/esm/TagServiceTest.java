package com.epam.esm;

import com.epam.esm.dao.TagDao;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.NotFoundEntityException;
import com.epam.esm.exception.NotValidEntityException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.TagServiceImpl;
import com.epam.esm.validator.TagValidator;
import com.epam.esm.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TagServiceTest {

    private TagDao tagDao;
    private Validator<Tag> tagValidator;
    private TagServiceImpl tagService;

    private static final Certificate CERTIFICATE = new Certificate("name",
            "description", BigDecimal.TEN, new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis()), 2);
    private static final Tag TAG = new Tag("testTag");


    @BeforeEach
    public void initMethod() {
        tagDao = Mockito.mock(TagDao.class);
        tagValidator = Mockito.mock(TagValidator.class);
        tagService = new TagServiceImpl(tagDao);

    }


    @Test
    public void testFindAll_ShouldFindAll() {
        tagService.findAll();
        verify(tagDao).findAll();
    }

    @Test
    public void testCreateShouldCreateWhenValidAndNotExist() {
        when(tagValidator.isValid(any())).thenReturn(true);
        when(tagDao.findByName(anyString())).thenReturn(Optional.empty());
        tagService.create(TAG);
        verify(tagDao).create(TAG);
    }

    @Test
    public void testCreateShouldThrowsInvalidEntityExceptionWhenInvalid() {
        when(tagValidator.isValid(any())).thenReturn(false);
       //todo
        assertThrows(Exception.class, () -> tagService.create(TAG));
    }

    @Test
    public void testCreateShouldThrowsDuplicateEntityExceptionWhenExist() {
        when(tagValidator.isValid(any())).thenReturn(true);
        when(tagDao.findByName(anyString())).thenReturn(Optional.of(TAG));
        assertThrows(DuplicateEntityException.class, () -> tagService.create(TAG));
    }

    @Test
    public void testFindById_ShouldGetWhenFound() {
        when(tagDao.findById(anyLong())).thenReturn(Optional.of(TAG));
        tagService.findById(1);
        verify(tagDao).findById(1);
    }


    @Test
    public void testDeleteById_ShouldDeletedWhenFound() {
        when(tagDao.findById(anyLong())).thenReturn(Optional.of(TAG));
        tagService.deleteById(1);
        verify(tagDao).deleteById(1);
    }
    @Test
    public void testCreateShouldThrowsNotValidTagExceptionWhenTagNotCorrect() {
        when(tagValidator.isValid(any())).thenReturn(false);
        //todo
        assertThrows(NotValidEntityException.class, () -> tagService.create(new Tag()));
    }

//    @Test
//    public void testFindById_ShouldFindCertificate() {
//        when(tagDao.findById(anyLong())).thenReturn(Optional.empty());
//        verify(tagDao).findById(anyLong());
//    }

    @Test
    public void testFindByIdShouldThrowsNotFoundEntityExceptionWhenNotFound() {
        when(tagDao.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundEntityException.class, () -> tagService.findById(-1));
    }


}
