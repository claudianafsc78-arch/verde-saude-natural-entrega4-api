package com.verdesaudesaude.api.service;

import com.verdesaudesaude.api.entity.Entregador;
import com.verdesaudesaude.api.repository.EntregadorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public EntregadorService(EntregadorRepository entregadorRepository) {
        this.entregadorRepository = entregadorRepository;
    }

    public List<Entregador> listarTodos() {
        return entregadorRepository.findAll();
    }

    public Entregador cadastrar(Entregador entregador) {
        if (entregadorRepository.existsByEmail(entregador.getEmail())) {
            throw new IllegalArgumentException(
                    "Este email ja esta cadastrado");
        }

        entregador.setSenha(
                passwordEncoder.encode(entregador.getSenha())
        );

        return entregadorRepository.save(entregador);
    }

    public Entregador login(String email, String senha) {
        Entregador entregador = entregadorRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Email ou senha invalidos"));

        if (!passwordEncoder.matches(senha, entregador.getSenha())) {
            throw new IllegalArgumentException(
                    "Email ou senha invalidos");
        }

        return entregador;
   
   
    }
}
