package com.epam.esm.controller;

import com.epam.esm.model.Tag;
import com.epam.esm.service.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagServiceImpl tagService;

    @Autowired
    public TagController(TagServiceImpl tagService) {


        this.tagService = tagService;
    }

    @PostMapping
    public void create(@RequestBody Tag tag, HttpServletResponse response) {

        tagService.create(tag);
    }


    @GetMapping
    public List<Tag> findAll() {
        return tagService.findAll();
    }


    @GetMapping(value = "/{id}")
    public Tag findById(@PathVariable("id") long id) {

        return tagService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        tagService.deleteById(id);
    }


}