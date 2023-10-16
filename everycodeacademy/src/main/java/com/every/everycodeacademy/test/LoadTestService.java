package com.every.everycodeacademy.test;

import com.every.everycodeacademy.board.Board;
import com.every.everycodeacademy.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class LoadTestService {

  private final BoardRepository boardRepository;

  @Transactional(isolation = Isolation.READ_UNCOMMITTED)
  public void loadTest1(Board board) {

    board.setTitle("1번");
    boardRepository.save(board);
    System.out.println("1번 업데이트 됨");
  }

  @Transactional(isolation = Isolation.READ_UNCOMMITTED)
  public void loadTest2(Board board) {

    board.setTitle("2번");
    boardRepository.save(board);
    System.out.println("2번 업데이트 됨");
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public void loadTest3(Board board) {

    board.setTitle("3번");
    boardRepository.save(board);
    System.out.println("3번 업데이트 됨");
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public void loadTest4(Board board) {

    board.setTitle("4번");
    boardRepository.save(board);
    System.out.println("4번 업데이트 됨");
  }

  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public void loadTest5(Board board) {

    board.setTitle("5번");
    boardRepository.save(board);
    System.out.println("5번 업데이트 됨");
  }

  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public void loadTest6(Board board) {

    board.setTitle("6번");
    boardRepository.save(board);
    System.out.println("6번 업데이트 됨");
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void loadTest7(Board board) {

    board.setTitle("7번");
    boardRepository.save(board);
    System.out.println("7번 업데이트 됨");
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void loadTest8(Board board) {

    board.setTitle("8번");
    boardRepository.save(board);
    System.out.println("8번 업데이트 됨");
  }
}
