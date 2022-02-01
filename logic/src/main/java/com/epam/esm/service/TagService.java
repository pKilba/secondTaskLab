package com.epam.esm.service;

import com.epam.esm.model.Tag;

import java.util.List;

public interface TagService {

    /**
     * @param entity some tag
     * @return id tag
     */
    int create(Tag entity);


    /**
     *
     * @return list of tags
     */
    List<Tag> findAll();

    /**
     *
     * @param id id tag
     * @return tag
     */
    Tag findById(long id);

    /**
     *
     * @param id id tag
     */
    void deleteById(long id);
}
