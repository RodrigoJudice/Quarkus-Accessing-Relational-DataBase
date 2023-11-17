package com.git.judice.quarkus.panache;

import java.time.Instant;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Publisher extends PanacheEntity {
    public String name;
    public Instant createdDate = Instant.now();

    public Publisher() {
    }

    public Publisher(String name, Instant createdDate) {
        this.name = name;
        this.createdDate = createdDate;
    }
}
