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
@RequestMapping(value = "/ticket")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TicketController {
    @Autowired
    private TicketRepository TicketService;
    @Autowired
    private BoardRepository BoardService;

    @GetMapping("/allticket")
    public ResponseEntity getAllticket() {
        try {
            List<Ticket> lt = new ArrayList<>();
            lt = TicketService.findAll();
            return new ResponseEntity(lt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addticket/{idBord}")
    public ResponseEntity<Ticket> addBoard(@PathVariable long idBord,@RequestBody Ticket tadd) {
        try {
            Board board =BoardService.findById(idBord).orElse(null);
            Ticket t = TicketService.save(tadd);
            List <Ticket> listTicket = board.getTickets();
            listTicket.add(t);
            board.setTickets(listTicket);
           //t.setBoard(board);
            BoardService.save(board);
            return new ResponseEntity(t, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateticket/{id}")
    public ResponseEntity<Ticket> updateticket(@RequestBody Ticket tick, @PathVariable long id) {
        try {
            Optional<Ticket> d = TicketService.findById(id);
            Ticket ticket = d.get();
            ticket.setName(tick.getName());
            ticket.setDate(tick.getDate());
            ticket.setComment(tick.getComment());
            ticket.setAttachment(tick.getAttachment());
            ticket.setImage(tick.getImage());
            ticket.setDescription(tick.getDescription());
            ticket.setSelectedLabel(tick.getSelectedLabel());
            TicketService.save(ticket);
            return new ResponseEntity(tick, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getticketById/{id}")
    public ResponseEntity getticketById(@PathVariable("id") Long id) {
        try {
            Ticket ticket = TicketService.findById(id).get();
            return new ResponseEntity(ticket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteticket/{id}")
    public ResponseEntity deleteticket(@PathVariable("id") Long id) {
        try {
            Optional<Ticket> ticketOpt = TicketService.findById(id);

            if (ticketOpt.isPresent()) {
                TicketService.deleteById(id);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/move/{boardId}/tickets/{ticketId}/{targetBoardId}")
    public ResponseEntity<String> moveTicket(
            @PathVariable Long boardId,
            @PathVariable Long ticketId,
            @PathVariable Long targetBoardId) {

        try {
            Optional<Board> sourceBoardOpt = BoardService.findById(boardId);
            Optional<Board> targetBoardOpt = BoardService.findById(targetBoardId);
            Optional<Ticket> ticketOpt = TicketService.findById(ticketId);

            if (sourceBoardOpt.isPresent() && targetBoardOpt.isPresent() && ticketOpt.isPresent()) {
                Board sourceBoard = sourceBoardOpt.get();
                Board targetBoard = targetBoardOpt.get();
                Ticket ticket = ticketOpt.get();

                // Remove the ticket from the source board
                sourceBoard.getTickets().remove(ticket);

                // Add the ticket to the target board
                targetBoard.getTickets().add(ticket);

                // Save the updated boards and ticket back to the database
                BoardService.save(sourceBoard);
                BoardService.save(targetBoard);
                TicketService.save(ticket);

                return ResponseEntity.ok("Ticket moved successfully.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
