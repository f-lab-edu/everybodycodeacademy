package com.every.everycodeacademy.test;

import com.every.everycodeacademy.board.Board;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/loadtest")
public class LoadTestAPI {

  private final LoadTestService loadTestService;

  @PostMapping("/test1")
  @Operation(summary = "load test 1 READ_UNCOMMITTED")
  public void loadTest1() {

    Board board = new Board();
    loadTestService.loadTest1(board);
    System.out.println("트랜잭션 READ_UNCOMMITTED 하나 완료");
    loadTestService.loadTest2(board);
    System.out.println("트랜잭션 READ_UNCOMMITTED 둘 완료");
  }

  @PostMapping("/test2")
  @Operation(summary = "load test 2 READ_COMMITTED")
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public void loadTest2() {

    Board board = new Board();
    loadTestService.loadTest3(board);
    System.out.println("트랜잭션 READ_COMMITTED 하나 완료");
    loadTestService.loadTest4(board);
    System.out.println("트랜잭션 READ_COMMITTED 둘 완료");
  }

  @PostMapping("/test3")
  @Operation(summary = "load test 3 REPEATABLE_READ")
  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public void loadTest3() {

    Board board = new Board();
    loadTestService.loadTest5(board);
    System.out.println("트랜잭션 REPEATABLE_READ 하나 완료");
    loadTestService.loadTest6(board);
    System.out.println("트랜잭션 REPEATABLE_READ 둘 완료");
  }

  @PostMapping("/test4")
  @Operation(summary = "load test 4 SERIALIZABLE")
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void loadTest4() {

    Board board = new Board();
    loadTestService.loadTest7(board);
    System.out.println("트랜잭션 SERIALIZABLE 셋 완료");
    loadTestService.loadTest8(board);
    System.out.println("트랜잭션 SERIALIZABLE 넷 완료");
  }
}
