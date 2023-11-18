package com.git.judice.quarkus.panache.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

@Entity
public class CD extends Item {

  // ======================================
  // = Attributes =
  // ======================================

  @Column(name = "music_company")
  public String musicCompany;

  @Column(length = 100)
  public String genre;

  @OneToMany(mappedBy = "cd", cascade = { CascadeType.REMOVE, CascadeType.PERSIST }, orphanRemoval = true)
  public List<Track> tracks = new ArrayList<>();

  public void addTrack(Track track) {
    tracks.add(track);
    track.cd = this;
  }

  public void removeTrack(Track track) {
    tracks.remove(track);
    track.cd = null;
  }

  // ======================================
  // = Constructors =
  // ======================================

  public CD() {
  }

  public CD(String title, String description, BigDecimal price, String genre) {
    this.title = title;
    this.description = description;
    this.price = price;
    this.genre = genre;
  }
}