package com.git.judice.quarkus.panache.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.git.judice.quarkus.panache.model.Publisher;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.EntityNotFoundException;

@QuarkusTest
public class PublishRepositoryTest {

  @Test
  @TestTransaction
  public void shouldCreateAndFindAPublisher() {
    long count = Publisher.count();
    int listAll = Publisher.listAll().size();
    assertEquals(count, listAll);

    // Creates a Publisher
    Publisher publisher = new Publisher();
    publisher.name = "name";

    // Persists the Publisher
    Publisher.persist(publisher);
    assertNotNull(publisher.id);

    assertEquals(count + 1, Publisher.count());

    // Gets the Publisher
    publisher = Publisher.findById(publisher.id);
    assertEquals("name", publisher.name);

    // Gets the Publisher by name
    publisher = Publisher.findByName(publisher.name).orElseThrow(EntityNotFoundException::new);
    assertEquals("name", publisher.name);
    assertTrue(Publisher.findContainingName("name").size() >= 1);

    // Deletes the Artist
    Publisher.deleteById(publisher.id);
    assertEquals(count, Publisher.count());
  }
}
