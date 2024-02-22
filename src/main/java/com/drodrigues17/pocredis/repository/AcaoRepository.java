package com.drodrigues17.pocredis.repository;

import com.drodrigues17.pocredis.model.Acao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Long> {

}
