package com.git.judice.quarkus.panache.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book extends Item {

  // ======================================
  // = Attributes =
  // ======================================

  @Column(length = 15)
  public String isbn;

  @Column(name = "nb_of_pages")
  public Integer nbOfPages;

  @Column(name = "publication_date")
  public LocalDate publicationDate;

  @Column(length = 20)
  @Enumerated(EnumType.STRING)
  public Language language;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "publisher_fk")
  public Publisher publisher;

  @Override
  public String toString() {
    return "Book{" +
        "isbn='" + isbn + '\'' +
        ", nbOfPages=" + nbOfPages +
        ", publicationDate=" + publicationDate +
        ", language=" + language +
        ", publisher=" + publisher +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", price=" + price +
        ", createdDate=" + createdDate +
        ", id=" + id +
        '}';
  }
}