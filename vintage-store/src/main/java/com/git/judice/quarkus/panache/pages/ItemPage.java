package com.git.judice.quarkus.panache.pages;

import com.git.judice.quarkus.panache.model.Book;
import com.git.judice.quarkus.panache.model.CD;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/page/items")
@Produces(MediaType.TEXT_HTML)

public class ItemPage {

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance book(Book book);

    public static native TemplateInstance books(List<Book> books);

    public static native TemplateInstance cd(CD cd);

    public static native TemplateInstance cds(List<CD> cds);

  }

  @GET
  @Path("/books/{id}")
  public TemplateInstance showBookById(@PathParam("id") Long id) {
    return Templates.book(Book.findById(id));
  }

  @GET
  @Path("/books")
  public TemplateInstance showBookAllBooks() {
    return Templates.books(Book.listAll());
  }

  @GET
  @Path("/cds/{id}")
  public TemplateInstance showCDById(@PathParam("id") Long id) {
    return Templates.cd(CD.findById(id));
  }

  @GET
  @Path("/cds")
  public TemplateInstance showCDAllCDs() {
    return Templates.cds(CD.listAll());
  }

}
