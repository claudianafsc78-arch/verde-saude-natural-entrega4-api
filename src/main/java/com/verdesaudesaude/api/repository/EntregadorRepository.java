package com.verdesaudesaude.api.repository;

import com.verdesaudesaude.api.entity.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntregadorRepository extends JpaRepository<Entregador, Long> {

    boolean existsByEmail(String email);

    Optional<Entregador> findByEmail(String email);
}