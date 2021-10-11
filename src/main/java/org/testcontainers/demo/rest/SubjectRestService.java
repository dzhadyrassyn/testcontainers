package org.testcontainers.demo.rest;

import org.testcontainers.demo.data.Subject;

import java.util.List;

public interface SubjectRestService {

    List<Subject> fetchAllGrades();
}
