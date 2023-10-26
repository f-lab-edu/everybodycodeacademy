package com.every.everycodeacademy.concurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcurrencyServiceImpl implements ConcurrencyService {

  private Long count = 0L;
  //private final Object lock = new Object(); // 동기화를 위한 락 객체

  @Autowired private ConcurrencyRepository concurrencyRepository;

  @Override
  public synchronized void incrementCount(Long value) {

  //  synchronized (lock) { // 동기화 블록 시작
      Concurrency concurrency = concurrencyRepository.findById(1L).orElseThrow();
      concurrency.setCount(concurrency.getCount() + 1);
      concurrencyRepository.save(concurrency);
 //   } // 동기화 블록 끝
  }

  @Override
  public synchronized Long getCount() {
  //  synchronized (lock) { // 동기화 블록 시작
      Concurrency concurrency = concurrencyRepository.findById(1L).orElseThrow();
      return concurrency.getCount();
    } // 동기화 블록 끝
 // }
}
