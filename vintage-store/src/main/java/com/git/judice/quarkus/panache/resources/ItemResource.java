package com.git.judice.quarkus.panache.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import com.git.judice.quarkus.panache.model.Book;
import com.git.judice.quarkus.panache.model.CD;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@Path("/api/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional(SUPPORTS)
public class ItemResource {

  @GET
  @Path("/books/{id}")
  public Book findBookById(@PathParam("id") Long id) {
    return Book.findById(id);
  }

  @GET
  @Path("/books")
  public List<Book> listAllBooks() {
    return Book.listAll();
  }

  @POST
  @Path("/books")
  @Transactional
  public Response persistBook(Book book, @Context UriInfo uriInfo) {
    Book.persist(book);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(book.id));
    return Response.created(builder.build()).build();
  }

  @DELETE
  @Transactional
  @Path("/books/{id}")
  public void deleteBook(@PathParam("id") Long id) {
    Book.deleteById(id);
  }

  @GET
  @Path("/cds/{id}")
  public CD findCDById(@PathParam("id") Long id) {
    return CD.findById(id);
  }

  @GET
  @Path("/cds")
  public List<CD> listAllCDs() {
    return CD.listAll();
  }

  @POST
  @Path("/cds")
  @Transactional
  public Response persistCD(CD cd, @Context UriInfo uriInfo) {
    CD.persist(cd);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(cd.id));
    return Response.created(builder.build()).build();
  }

  @DELETE
  @Transactional
  @Path("/cds/{id}")
  public void deleteCD(@PathParam("id") Long id) {
    CD.deleteById(id);
  }

}