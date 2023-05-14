package com.example.gestproj.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="idTicket",nullable = false)
    private Long id;
    private String name;
    private Date date;
    private String comment;
    private String attachment;
    private String image;
    private String description;
    private String selectedLabel;


    // @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "board_id")
    // private Board board;
}
