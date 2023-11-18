package com.git.judice.quarkus.panache.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_tracks")
public class Track extends PanacheEntity {

  // ======================================
  // = Attributes =
  // ======================================

  @Column(nullable = false)
  public String title;

  @Column(nullable = false)
  public Duration duration;

  @JoinColumn(name = "cd_fk")
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonbTransient
  public CD cd;

  @Column(name = "created_date", nullable = false)
  public Instant createdDate = Instant.now();

  // ======================================
  // = Constructors =
  // ======================================

  public Track() {
  }

  public Track(String title, Duration duration) {
    this.title = title;
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "Track{" +
        "title='" + title + '\'' +
        ", duration=" + duration +
        ", createdDate=" + createdDate +
        ", id=" + id +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Track track = (Track) o;
    return id.equals(track.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
