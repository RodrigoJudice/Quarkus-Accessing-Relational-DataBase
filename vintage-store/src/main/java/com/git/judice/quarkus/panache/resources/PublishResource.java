package com.git.judice.quarkus.panache.resources;

import com.git.judice.quarkus.panache.model.Publisher;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

@Path("/api/publisher")
@Produces("application/json")
@Consumes("application/json")
public class PublishResource {

  @GET
  public List<Publisher> listAll() {
    return Publisher.listAll();
  }

  @GET
  @Path("{id}")
  public Publisher findPublisherById(@PathParam("id") Long id) {
    return (Publisher) Publisher.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @POST
  @Transactional
  public Response createPublisher(Publisher publisher, @Context UriInfo uriInfo) {
    Publisher.persist(publisher);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(publisher.id));
    return Response.created(builder.build()).build();

  }

  @DELETE
  @Path("{id}")
  @Transactional
  public void deletePublisher(@PathParam("id") Long id) {
    Publisher.deleteById(id);
  }

}
