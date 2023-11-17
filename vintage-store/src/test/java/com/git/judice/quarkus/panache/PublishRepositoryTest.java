package com.git.judice.quarkus.panache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PublishRepositoryTest {

    @Test
    @TestTransaction
    public void shouldCreateAndFindAPublisher() {
        Publisher publisher = new Publisher();

        publisher.name = "name";

        Publisher.persist(publisher);
        assertNotNull(publisher.id);

        publisher = Publisher.findById(publisher.id);
        assertEquals("name", publisher.name);
    }

}
