package com.example.gestproj.controller;

import com.example.gestproj.entities.Board;
import com.example.gestproj.entities.Ticket;
import com.example.gestproj.repository.BoardRepository;
import com.example.gestproj.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/board")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {
    @Autowired
    private BoardRepository BoardService;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/allboard")
    public ResponseEntity getAllboard() {
        try {
            List<Board> lb = new ArrayList<>();
            lb = BoardService.findAll();
            return new ResponseEntity(lb, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addboard")
    public ResponseEntity<Board> addBoard(@RequestBody Board badd) {
        try {
            Board v = BoardService.save(badd);
            System.out.println("eeeeeeeeee");
            return new ResponseEntity(v, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateboard/{id}")
    public ResponseEntity<Board> updateBoard(@RequestBody Board des, @PathVariable long id) {
        try {
            Optional<Board> boardOpt = BoardService.findById(id);
            if (boardOpt.isPresent()) {
                Board board = boardOpt.get();
                board.setName(des.getName());
                board.setShowNewItem(des.isShowNewItem());
                BoardService.save(board);
                return new ResponseEntity<>(board, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getboardById/{id}")
    public ResponseEntity getBoardById(@PathVariable("id") Long id) {
        try {
            Board board = BoardService.findById(id).get();
            return new ResponseEntity(board, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteboard/{id}")
    public ResponseEntity deleteBoardById(@PathVariable("id") Long id) {
        try {
            BoardService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}