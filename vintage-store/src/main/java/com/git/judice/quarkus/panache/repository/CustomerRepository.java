package com.git.judice.quarkus.panache.repository;

import com.git.judice.quarkus.jpa.Customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

  public List<Customer> ListAllFromName(String name) {
    return list("firstName = ?1", Sort.by("firstName"), name);
  }

}
