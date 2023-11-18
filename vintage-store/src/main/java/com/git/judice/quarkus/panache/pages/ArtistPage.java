package com.git.judice.quarkus.panache.pages;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

import com.git.judice.quarkus.jdbc.Artist;
import com.git.judice.quarkus.panache.repository.ArtistRepository;

@Path("/page/artists")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class ArtistPage {

  @Inject
  ArtistRepository repository;

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance artist(Artist artist);

    public static native TemplateInstance artists(List<Artist> artists);
  }

  @GET
  @Path("{id}")
  public TemplateInstance showArtistById(@PathParam("id") Long id) {
    return Templates.artist(repository.findById(id));
  }

  @GET
  public TemplateInstance showAllArtists(@QueryParam("query") String query,
      @QueryParam("sort") @DefaultValue("id") String sort, @QueryParam("page") @DefaultValue("0") Integer pageIndex,
      @QueryParam("size") @DefaultValue("1000") Integer pageSize) {
    return Templates.artists(repository.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
        .data("query", query)
        .data("sort", sort)
        .data("pageIndex", pageIndex)
        .data("pageSize", pageSize);
  }
}