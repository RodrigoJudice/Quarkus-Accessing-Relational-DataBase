package com.git.judice.quarkus.panache.resources;

import com.git.judice.quarkus.panache.model.Publisher;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

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

}
