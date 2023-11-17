package com.git.judice.quarkus.panache.model;

import java.math.BigDecimal;
import java.time.Instant;

import com.git.judice.quarkus.jdbc.Artist;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "t_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends PanacheEntity {

  // ======================================
  // = Attributes =
  // ======================================

  @Column(length = 100, nullable = false)
  public String title;

  @Column(length = 3000)
  public String description;

  @Column(nullable = false)
  public BigDecimal price;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "artist_fk")
  public Artist artist;

  @Column(name = "created_date", nullable = false)
  public Instant createdDate = Instant.now();
}