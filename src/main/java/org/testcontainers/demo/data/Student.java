package org.testcontainers.demo.data;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Student")
@Data
public class Student implements Serializable {

    private String id;
    private String name;
    private Gender gender;
    private int grade;

    public enum Gender {
        MALE, FEMALE
    }
}
