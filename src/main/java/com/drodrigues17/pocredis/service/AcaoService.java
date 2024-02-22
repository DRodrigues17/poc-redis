package com.drodrigues17.pocredis.service;

import com.drodrigues17.pocredis.model.Acao;
import com.drodrigues17.pocredis.repository.AcaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AcaoService{

    private final AcaoRepository acaoRespository;

    @Cacheable("acaoCache")
    public List<Acao> buscarCombinacoes() {

        log.info("buscando informações no banco");
        return acaoRespository.findAll();

    }
}
