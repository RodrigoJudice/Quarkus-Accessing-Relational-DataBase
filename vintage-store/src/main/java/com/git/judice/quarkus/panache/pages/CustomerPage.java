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

import com.git.judice.quarkus.jpa.Customer;
import com.git.judice.quarkus.panache.repository.CustomerRepository;

@Path("/page/customers")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class CustomerPage {

  @Inject
  CustomerRepository repository;

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance customer(Customer customer);

    public static native TemplateInstance customers(List<Customer> customers);
  }

  @GET
  @Path("{id}")
  public TemplateInstance showCustomerById(@PathParam("id") Long id) {
    return Templates.customer(repository.findById(id));
  }

  @GET
  public TemplateInstance showAllCustomers(@QueryParam("query") String query,
      @QueryParam("sort") @DefaultValue("id") String sort, @QueryParam("page") @DefaultValue("0") Integer pageIndex,
      @QueryParam("size") @DefaultValue("1000") Integer pageSize) {
    return Templates.customers(repository.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
        .data("query", query)
        .data("sort", sort)
        .data("pageIndex", pageIndex)
        .data("pageSize", pageSize);
  }
}