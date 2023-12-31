package com.git.judice.quarkus.panache.resources;

import com.git.judice.quarkus.jdbc.Artist;
import com.git.judice.quarkus.panache.repository.ArtistRepository;

import jakarta.enterprise.context.ApplicationScoped;
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
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@Path("/api/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional(SUPPORTS)
public class ArtistResource {

  @Inject
  ArtistRepository repository;

  /**
   * curl http://localhost:8080/api/artists/1
   */
  @GET
  @Path("{id}")
  public Artist findArtistById(@PathParam("id") Long id) {
    return repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  /**
   * curl http://localhost:8080/api/artists
   */
  @GET
  public List<Artist> listAllArtists() {
    return repository.listAllArtistsSorted();
  }

  /**
   * curl -X POST http://localhost:8080/api/artists -H 'Content-Type:
   * application/json' -d '{ "bio": "artist bi", "name": "artist name" }' -v
   */
  @POST
  @Transactional
  public Response persistArtist(Artist artist, @Context UriInfo uriInfo) {
    repository.persist(artist);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(artist.getId()));
    return Response.created(builder.build()).build();
  }

  /**
   * curl -X DELETE http://localhost:8080/api/artists/1
   */
  @DELETE
  @Transactional
  @Path("/{id}")
  public void deleteArtist(@PathParam("id") Long id) {
    repository.deleteById(id);
  }

}