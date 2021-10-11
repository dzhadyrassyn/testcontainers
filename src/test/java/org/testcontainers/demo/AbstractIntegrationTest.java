package org.testcontainers.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
	"spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
	"spring.datasource.url=jdbc:tc:mysql:///superapp"
})
public class AbstractIntegrationTest {

	public static final DockerImageName REDIS_IMAGE = DockerImageName.parse("redis");

	public static GenericContainer<?> redis = new GenericContainer<>(REDIS_IMAGE)
		.withExposedPorts(6379);

	public static MockServerContainer mockServer = new MockServerContainer(
		DockerImageName.parse("jamesdbloom/mockserver:mockserver-5.11.2")
	);


	static {

		Stream.of(redis, mockServer).parallel().forEach(GenericContainer::start);

		System.setProperty("spring.redis.host", redis.getContainerIpAddress());
		System.setProperty("spring.redis.port", redis.getFirstMappedPort() + "");

		System.setProperty("api.subjects", mockServer.getEndpoint() + "/subjects");
	}

	@Test
	void contextLoads() {
	}

}
