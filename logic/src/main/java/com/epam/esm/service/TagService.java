package com.epam.esm.service;

import com.epam.esm.model.Tag;

import java.util.List;

public interface TagService {

    int create(Tag entity);

    List<Tag> findAll();

    Tag findById(long id);

    void deleteById(long id);
}
