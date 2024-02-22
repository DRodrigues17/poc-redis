package com.drodrigues17.pocredis.controller;

import com.drodrigues17.pocredis.model.Acao;
import com.drodrigues17.pocredis.service.AcaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/acoes")
@AllArgsConstructor
public class AcaoController {

    private final AcaoService acaoService;

    @GetMapping
    public ResponseEntity<List<Acao>> buscarAcoes(){
        return ResponseEntity.ok(acaoService.buscarCombinacoes());
    }

}
