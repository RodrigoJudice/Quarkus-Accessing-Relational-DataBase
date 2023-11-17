package com.git.judice.quarkus.jdbc;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Getter
@Setter
public class Artist {

    // ======================================
    // = Attributes =
    // ======================================

    private Long id;
    private String name;
    private String bio;
    private Instant createdDate = Instant.now();

    // ======================================
    // = Constructors =
    // ======================================

    public Artist() {
    }

    public Artist(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public Artist(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}