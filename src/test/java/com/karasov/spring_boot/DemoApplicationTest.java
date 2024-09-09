package com.karasov.spring_boot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void testDevProfile() {
        var expected = "Current profile is dev";

        var forEntity = restTemplate
                .getForEntity("http://localhost:" + devapp.getMappedPort(8080) + "/profile", String.class);
        var actual = forEntity.getBody();

        assertEquals(expected, actual);
    }

    @Test
    void testProdProfile() {
        var expected = "Current profile is production";

        var forEntity = restTemplate
                .getForEntity("http://localhost:" + prodapp.getMappedPort(8081) + "/profile", String.class);
        var actual = forEntity.getBody();

        assertEquals(expected, actual);
    }
}
