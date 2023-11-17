package com.git.judice.quarkus.panache.repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.git.judice.quarkus.jdbc.Artist;
import com.git.judice.quarkus.jpa.Customer;
import com.git.judice.quarkus.panache.model.Book;
import com.git.judice.quarkus.panache.model.Language;
import com.git.judice.quarkus.panache.model.OrderLine;
import com.git.judice.quarkus.panache.model.Publisher;
import com.git.judice.quarkus.panache.model.PurchaseOrder;

import jakarta.inject.Inject;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PurchaseOrderRepositoryTest {

  @Inject
  CustomerRepository customerRepository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindAPurchaseOrder() {

    // Creates an Artist
    Artist artist = new Artist();
    artist.setName("name");
    artist.setBio("bio");
    artist.setCreatedDate(Instant.now());

    // Creates a Publisher
    Publisher publisher = new Publisher();
    publisher.name = "name";

    // Creates a Book
    Book book = new Book();
    book.title = "title";
    book.description = "description";
    book.price = new BigDecimal(10);
    book.isbn = "ISBN";
    book.nbOfPages = 500;
    book.publicationDate = LocalDate.now().minusDays(100);
    book.language = Language.ENGLISH;
    // Sets the Publisher and Artist to the Book
    book.publisher = publisher;
    book.artist = artist;
    // Persists the Book with one Publisher and one Artist
    Book.persist(book);

    // Creates a Customer
    Customer customer = new Customer("Fulano", "Ciclano", "Fulano.Ciclano@gmail.com");
    customerRepository.persist(customer);

    // Creates an oder line
    OrderLine orderLine = new OrderLine();
    orderLine.item = book;
    orderLine.quantity = 2;

    // Creates a PurchaseOrder
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.customer = customer;
    purchaseOrder.addOrderLine(orderLine);

    // Persists the PurchaseOrder
    PurchaseOrder.persist(purchaseOrder);
  }
}
