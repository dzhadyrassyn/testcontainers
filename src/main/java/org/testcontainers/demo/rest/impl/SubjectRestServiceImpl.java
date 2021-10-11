package org.testcontainers.demo.rest.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.demo.data.Subject;
import org.testcontainers.demo.rest.SubjectRestService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class SubjectRestServiceImpl implements SubjectRestService {

    private final RestTemplate restTemplate;
    @Value("${api.subjects}")
    private String url;

    public SubjectRestServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Subject> fetchAllGrades() {

        ResponseEntity<Subject[]> response = restTemplate.getForEntity(url, Subject[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
