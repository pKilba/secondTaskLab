package com.epam.esm.controller;


import com.epam.esm.dto.CertificateDto;

import com.epam.esm.model.Certificate;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CertificateDto giftCertificateDto,
                       HttpServletResponse response) {
        certificateService.create(giftCertificateDto);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Certificate> findAll() {
        return certificateService.findAll();
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto updateById(@PathVariable("id") int id,
                                     @RequestBody CertificateDto сertificateDto) {
        return certificateService.updateById(id, сertificateDto);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Certificate findById(@PathVariable("id") int id) {
        return certificateService.findById(id);
    }


    @GetMapping("/find-by-description/{description}")
    @ResponseStatus(HttpStatus.OK)
    public Certificate findByDescription(@PathVariable("description") String description) {
        return certificateService.findByPartDescription(description);
    }


    @GetMapping("/find-by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Certificate findByName(@PathVariable("name") String name) {
        return certificateService.findByPartName(name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") int id) {
        certificateService.deleteById(id);
    }


    @GetMapping("/find-certificates-by-tag/{nameTag}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Certificate> findByTagName(@PathVariable("nameTag") String name) {
        return certificateService.findByTagName(name);
    }

}
