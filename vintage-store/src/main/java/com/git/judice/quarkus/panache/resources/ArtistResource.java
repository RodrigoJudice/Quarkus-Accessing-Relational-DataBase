package com.git.judice.quarkus.panache.resources;

import com.git.judice.quarkus.jdbc.Artist;
import com.git.judice.quarkus.panache.repository.ArtistRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import java.util.List;

@Path("/api/artists")
@Produces("application/json")
@Consumes("application/json")
public class ArtistResource {

  @Inject
  ArtistRepository artistRepository;

  @GET
  public List<Artist> listAll() {
    return artistRepository.listAll();
  }

  @GET
  @Path("{id}")
  public Artist findArtistsById(@PathParam("id") Long id) {
    return artistRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

}
