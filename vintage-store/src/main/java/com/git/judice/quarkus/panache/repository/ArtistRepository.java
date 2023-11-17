package com.git.judice.quarkus.panache.repository;

import com.git.judice.quarkus.jdbc.Artist;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {

  public List<Artist> listAllArtistsSorted() {
    return listAll(Sort.descending("name"));
  }

}
