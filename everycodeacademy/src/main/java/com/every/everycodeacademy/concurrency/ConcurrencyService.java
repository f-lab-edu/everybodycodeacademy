package com.every.everycodeacademy.concurrency;

public interface ConcurrencyService {

  void incrementCount(Long value);

  Long getCount();
}
