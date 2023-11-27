package com.every.everycodeacademy.concurrency;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcurrencyRepository extends JpaRepository<Concurrency, Long> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Concurrency> findById(Long id);
}
