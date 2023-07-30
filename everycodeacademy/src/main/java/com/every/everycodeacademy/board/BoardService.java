package com.every.everycodeacademy.board;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void register(Board board) {
        boardRepository.save(board);
    }

    public List<Board> list() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "idx"));
    }

    public Board detail(int idx) {
        return boardRepository.findById(idx).orElse(null);
    }

    public void update(Board board) {
        boardRepository.save(board);
    }

    public void delete(int idx) {
        boardRepository.deleteById(idx);
    }

}
