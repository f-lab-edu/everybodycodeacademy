package com.every.everycodeacademy.concurrency;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Concurrency {

  @Id private Long id;
  private Long count;
}
