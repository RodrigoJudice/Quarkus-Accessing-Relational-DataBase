package com.git.judice.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import com.git.judice.quarkus.jdbc.Artist;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

  @Inject
  ArtistRepository repository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindAnArtist() {
    long count = repository.count();
    int listAll = repository.listAll().size();
    assertEquals(count, listAll);
    assertEquals(repository.listAllArtistsSorted().size(), listAll);

    // Creates an Artist
    Artist artist = new Artist();
    artist.setName("name");
    artist.setBio("bio");

    // Persists the Artist
    repository.persist(artist);
    assertNotNull(artist.getId());

    assertEquals(count + 1, repository.count());

    // Gets the Artists
    artist = repository.findById(artist.getId());
    assertEquals("name", artist.getName());

    // Deletes the Artist
    repository.deleteById(artist.getId());
    assertEquals(count, repository.count());
  }
}