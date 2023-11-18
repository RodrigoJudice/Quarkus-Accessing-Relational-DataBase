package com.git.judice.quarkus.panache.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.git.judice.quarkus.jpa.Customer;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class CustomerRepositoryTest {

  @Inject
  CustomerRepository repository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindACustomer() {
    long count = repository.count();
    int listAll = repository.listAll().size();
    assertEquals(count, listAll);
    assertTrue(repository.ListAllFromName("first name").size() <= listAll);

    // Creates a Customer
    Customer customer = new Customer();
    customer.setFirstName("first name");
    customer.setLastName("last name");
    customer.setEmail("email");

    // Persists the Customer
    repository.persist(customer);
    assertNotNull(customer.getId());

    assertEquals(count + 1, repository.count());

    // Gets the Customer
    customer = repository.findById(customer.getId());
    assertEquals("first name", customer.getFirstName());

    // Deletes the Customer
    repository.deleteById(customer.getId());
    assertEquals(count, repository.count());
  }
}