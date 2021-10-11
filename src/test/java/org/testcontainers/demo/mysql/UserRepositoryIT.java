package org.testcontainers.demo.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.demo.AbstractIntegrationTest;
import org.testcontainers.demo.data.User;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class UserRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        User expectedUser = new User();
        expectedUser.setName("Michael");
        expectedUser.setEmail("michael@test.ru");

        userRepository.save(expectedUser);

        Iterable<User> all = userRepository.findAll();

        assertThat(all.iterator().hasNext()).isTrue();

        User processedUser = all.iterator().next();
        assertThat(processedUser.getId()).isNotNull();
        assertThat(processedUser.getName()).isEqualTo(expectedUser.getName());
        assertThat(processedUser.getEmail()).isEqualTo(expectedUser.getEmail());
    }
}
