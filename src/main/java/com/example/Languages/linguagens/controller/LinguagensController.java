package com.example.Languages.linguagens.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Languages.linguagens.service.ServiceLinguagem;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Languages.linguagens.dto.LinguagemDto;


@RestController
@CrossOrigin
@RequestMapping("/linguagem")
@AllArgsConstructor
public class LinguagensController {

    @Autowired
    ServiceLinguagem serviceLinguagem;

    @GetMapping("/todas")
    public ResponseEntity<List<LinguagemDto>> buscarLinguagens(
        @PageableDefault(page = 0, size = Integer.MAX_VALUE,sort = "nome")Pageable pageable,
        @RequestParam("nome")Optional<String>nome,
        @RequestParam("tipo")Optional<String>tipo,
        @RequestParam("ano")Optional<String>anoCriacao
    ) {
        List<LinguagemDto> linguagens = serviceLinguagem.retornaLinguagens(pageable, nome, tipo, anoCriacao);
         return new ResponseEntity<>(linguagens, HttpStatus.OK);
    }
    
    @PostMapping("/salvar")
    public ResponseEntity<?>salvarLinguagem(
    		@RequestBody LinguagemDto linguagem){
    	serviceLinguagem.salvarLinguagem(linguagem);
    	 return new ResponseEntity<>(HttpStatus.OK);
    }
}
