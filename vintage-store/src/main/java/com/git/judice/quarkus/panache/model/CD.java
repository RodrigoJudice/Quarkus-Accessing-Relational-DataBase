package com.git.judice.quarkus.panache.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;

@Entity
public class CD extends Item {

  @Column(name = "music_company")
  public String musicCompany;
  @Column(length = 100)
  public String genre;

  @OneToMany(mappedBy = "cd")
  public List<Track> tracks = new ArrayList<>();
}
