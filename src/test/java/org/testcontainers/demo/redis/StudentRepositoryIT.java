package org.testcontainers.demo.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.demo.AbstractIntegrationTest;
import org.testcontainers.demo.data.Student;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StudentRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void test() {

        Student expectedStudent = new Student();
        expectedStudent.setId(UUID.randomUUID().toString());
        expectedStudent.setName("John");
        expectedStudent.setGender(Student.Gender.MALE);

        studentRepository.save(expectedStudent);

        Optional<Student> processedStudent = studentRepository.findById(expectedStudent.getId());

        assert processedStudent.isPresent();

        assertThat(processedStudent.get().getGender().toString()).isEqualTo(expectedStudent.getGender().toString());
        assertThat(processedStudent.get().getId()).isEqualTo(expectedStudent.getId());
    }
}
