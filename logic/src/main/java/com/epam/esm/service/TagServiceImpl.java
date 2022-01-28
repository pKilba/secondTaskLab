package com.epam.esm.service;

import com.epam.esm.dao.TagDao;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.NotFoundEntityException;
import com.epam.esm.model.Tag;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class TagServiceImpl implements TagService {

    private static final String TAG_EXIST = "Tag exist";
    private static final String TAG_NOT_FOUND = "Tag not found";

    private final TagValidator validator = new TagValidator();
    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public int create(Tag tag) {
        validator.isValid(tag);
        validateForExistTag(tag);
        tagDao.create(tag);

        return 1;
    }

    private void validateForExistTag(Tag tag) {
        if (tagDao.findByName(tag.getName()).isPresent()) {
            throw new DuplicateEntityException(TAG_EXIST);
        }
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    @Transactional
    public Tag findById(long id) {
        Optional<Tag> optionalTag = tagDao.findById(id);
        if (!optionalTag.isPresent()) {
            throw new NotFoundEntityException(TAG_NOT_FOUND);
        }
        return optionalTag.get();
    }


    @Override
    public void deleteById(long id) {
        Optional<Tag> optionalTag = tagDao.findById(id);
        if (!optionalTag.isPresent()) {
            throw new NotFoundEntityException(TAG_NOT_FOUND);
        }
        tagDao.deleteById(id);
    }
}
