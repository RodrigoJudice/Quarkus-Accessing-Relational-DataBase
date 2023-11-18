package com.git.judice.quarkus.panache.resources;

import com.git.judice.quarkus.panache.model.PurchaseOrder;

import jakarta.enterprise.context.ApplicationScoped;
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

@Path("/api/purchase-orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional(SUPPORTS)
public class PurchaseOrderResource {

  @GET
  @Path("{id}")
  public PurchaseOrder findPurchaseOrderById(@PathParam("id") Long id) {
    return (PurchaseOrder) PurchaseOrder.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @GET
  public List<PurchaseOrder> listAllPurchaseOrders() {
    return PurchaseOrder.listAll();
  }

  @POST
  @Transactional
  public Response persistPurchaseOrder(PurchaseOrder purchaseOrder, @Context UriInfo uriInfo) {
    PurchaseOrder.persist(purchaseOrder);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(purchaseOrder.id));
    return Response.created(builder.build()).build();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void deletePurchaseOrder(@PathParam("id") Long id) {
    PurchaseOrder.deleteById(id);
  }
}
