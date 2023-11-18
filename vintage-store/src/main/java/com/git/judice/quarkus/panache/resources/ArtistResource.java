package com.git.judice.quarkus.panache.resources;

import com.git.judice.quarkus.jdbc.Artist;
import com.git.judice.quarkus.panache.repository.ArtistRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

@Path("/api/artists")
@Produces("application/json")
@Consumes("application/json")
@Transactional(Transactional.TxType.SUPPORTS)
public class ArtistResource {

  @Inject
  ArtistRepository repository;

  @GET
  public List<Artist> listAll() {
    return repository.listAll();
  }

  @GET
  @Path("{id}")
  public Artist findArtistsById(@PathParam("id") Long id) {
    return repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @POST
  @Transactional
  public Response createArtist(Artist artist, @Context UriInfo uriInfo) {
    repository.persist(artist);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(artist.getId()));
    return Response.created(builder.build()).build();

  }

  @DELETE
  @Path("{id}")
  @Transactional
  public void deleteArtist(@PathParam("id") Long id) {
    repository.deleteById(id);
  }

}
