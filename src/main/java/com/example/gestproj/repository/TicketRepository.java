package com.example.gestproj.repository;

import com.example.gestproj.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository <Ticket,Long> {
}
