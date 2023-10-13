package com.every.everycodeacademy.API;

import com.every.everycodeacademy.board.Board;
import com.every.everycodeacademy.board.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiResponses({
    @ApiResponse(responseCode = "200", description = "success"),
    @ApiResponse(responseCode = "400", description = "bad request"),
    @ApiResponse(responseCode = "404", description = "not found"),
    @ApiResponse(responseCode = "500", description = "internal sever error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardAPI {


  private final BoardService boardService;

  @GetMapping("/list")
  @Operation(summary = "List a board")
  public List<Board> list() {
    return boardService.list();
  }

  @GetMapping("/detail/{idx}")
  @Operation(summary = "detail a view")
  public Board detail(@PathVariable int idx, Model model) {
    return boardService.detail(idx);
  }

  @PostMapping("/register")
  @Operation(summary = "register a board")
  public Board registerPost(Board board) {
    return boardService.register(board);
  }

  @PostMapping("/update")
  @Operation(summary = "update a board")
  public Board updatePost(Board board) {

    return boardService.update(board);
  }

  @PostMapping("/loadtest")
  @Operation(summary = "load test 1 READ_UNCOMMITTED")
  public void loadTest1(){

    Board board = new Board();
    boardService.loadTest1(board);
    System.out.println("트랜잭션 READ_UNCOMMITTED 하나 완료");
    boardService.loadTest2(board);
    System.out.println("트랜잭션 READ_UNCOMMITTED 둘 완료");

  }

  @PostMapping("/loadtest2")
  @Operation(summary = "load test 2 READ_COMMITTED")
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public void loadTest2(){

    Board board = new Board();
    boardService.loadTest3(board);
    System.out.println("트랜잭션 READ_COMMITTED 하나 완료");
    boardService.loadTest4(board);
    System.out.println("트랜잭션 READ_COMMITTED 둘 완료");

  }


  @PostMapping("/loadtest3")
  @Operation(summary = "load test 3 REPEATABLE_READ")
  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public void loadTest3(){

      Board board = new Board();
      boardService.loadTest5(board);
      System.out.println("트랜잭션 REPEATABLE_READ 하나 완료");
      boardService.loadTest6(board);
      System.out.println("트랜잭션 REPEATABLE_READ 둘 완료");

  }
  @PostMapping("/loadtest4")
  @Operation(summary = "load test 4 SERIALIZABLE")
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void loadTest4(){

    Board board = new Board();
    boardService.loadTest7(board);
    System.out.println("트랜잭션 SERIALIZABLE 셋 완료");
    boardService.loadTest8(board);
    System.out.println("트랜잭션 SERIALIZABLE 넷 완료");

  }

}
