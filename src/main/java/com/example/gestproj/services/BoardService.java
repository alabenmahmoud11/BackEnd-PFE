package com.example.gestproj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    com.example.gestproj.repository.BoardRepository BoardRepository ;
    //public Board addboard(Board board){ return com.example.gestionprojet.repository.BoardRepository.save(board);}
    //public List<Board> getAllboard(){ return com.example.gestionprojet.repository.BoardRepository.findAll();}
    //public Board updateboard(Board board){ return com.example.gestionprojet.repository.BoardRepository.save(board);}
    //public Board getboardById(Long id){ return com.example.gestionprojet.repository.BoardRepository.findById(id).get();}
    //public void deleteboard(Long id) { com.example.gestionprojet.repository.BoardRepository.deleteById(id);}
}
