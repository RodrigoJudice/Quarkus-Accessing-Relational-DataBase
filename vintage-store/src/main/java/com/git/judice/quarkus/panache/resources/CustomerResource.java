package com.git.judice.quarkus.panache.resources;

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

import com.git.judice.quarkus.jpa.Customer;
import com.git.judice.quarkus.panache.repository.CustomerRepository;

import static jakarta.transaction.Transactional.TxType.SUPPORTS;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional(SUPPORTS)
public class CustomerResource {

  @Inject
  CustomerRepository repository;

  @GET
  @Path("{id}")
  public Customer findCustomerById(@PathParam("id") Long id) {
    return repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @GET
  public List<Customer> listAllCustomers() {
    return repository.listAll();
  }

  @POST
  @Transactional
  public Response persistCustomer(Customer customer, @Context UriInfo uriInfo) {
    repository.persist(customer);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(customer.getId()));
    return Response.created(builder.build()).build();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void deleteCustomer(@PathParam("id") Long id) {
    repository.deleteById(id);
  }
}