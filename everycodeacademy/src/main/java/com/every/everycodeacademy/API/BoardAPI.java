package com.every.everycodeacademy.API;

import com.every.everycodeacademy.board.Board;
import com.every.everycodeacademy.board.BoardService;
import com.every.everycodeacademy.board.View;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

  @GetMapping("/view/{idx}")
  @Operation(summary = "detail a view")
  public View detail(@PathVariable int idx) {
    Board board = boardService.detail(idx);
    return new View(board); // View 객체를 생성하여 반환
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
}
