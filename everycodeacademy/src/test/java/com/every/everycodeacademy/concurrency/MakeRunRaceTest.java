package com.every.everycodeacademy.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // 테스트 전용 프로파일 사용 가능
public class MakeRunRaceTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private ConcurrencyRepository concurrencyRepository;
  @Autowired private ConcurrencyService concurrencyService;

  @BeforeEach
  void setUp() {
    Concurrency concurrency = new Concurrency();
    concurrency.setId(1L);
    concurrency.setCount(0L);
    concurrencyRepository.save(concurrency);
  }

  @Test
  void makeRunRaceTest() throws InterruptedException {

    Concurrency concurrency = new Concurrency();
    concurrency.setId(1L);
    concurrency.setCount(0L);
    concurrencyRepository.save(concurrency);

    // 동시에 실행할 스레드 수z
    int numberOfThreads = 1000;

    // CountDownLatch를 사용하여 모든 스레드가 동시에 시작되도록 함
    CountDownLatch latch = new CountDownLatch(1);

    ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

    for (int i = 0; i < numberOfThreads; i++) {

      service.submit(
          () -> {
            try {
              latch.await(); // 모든 스레드가 latch를 기다림
              concurrencyService.incrementCount(1L);
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
          });
    }

    // 모든 스레드를 동시에 시작
    latch.countDown();

    // 스레드 종료 시작
    service.shutdown();

    while (!service.isTerminated()) { // 완전히 종료되지 않았다면
      try {
        Thread.sleep(500); // 100ms 대기
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
