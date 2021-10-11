package org.testcontainers.demo.mysql;

import org.springframework.data.repository.CrudRepository;
import org.testcontainers.demo.data.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
