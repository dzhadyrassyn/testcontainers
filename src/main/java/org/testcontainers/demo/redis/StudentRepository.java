package org.testcontainers.demo.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.testcontainers.demo.data.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
