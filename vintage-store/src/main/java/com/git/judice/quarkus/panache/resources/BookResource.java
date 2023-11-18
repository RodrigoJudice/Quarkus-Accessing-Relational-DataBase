package com.git.judice.quarkus.panache.resources;

import com.git.judice.quarkus.panache.model.Book;

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

@Path("/api/books")
@Produces("application/json")
@Consumes("application/json")
@Transactional(Transactional.TxType.SUPPORTS)
public class BookResource {

  @GET
  public List<Book> listAll() {
    return Book.listAll();
  }

  @GET
  @Path("{id}")
  public Book findBookById(@PathParam("id") Long id) {
    return (Book) Book.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @POST
  @Transactional
  public Response createBook(Book book, @Context UriInfo uriInfo) {
    Book.persist(book);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(book.id));
    return Response.created(builder.build()).build();

  }

  @DELETE
  @Path("{id}")
  @Transactional
  public void deleteBook(@PathParam("id") Long id) {
    Book.deleteById(id);
  }

}
