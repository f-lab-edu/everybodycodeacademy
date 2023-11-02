package com.every.everycodeacademy.concurrency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcurrencyRepository extends JpaRepository<Concurrency, Long> {}
