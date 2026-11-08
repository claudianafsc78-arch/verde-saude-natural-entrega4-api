package com.verdesaudesaude.api.controller;

import com.verdesaudesaude.api.entity.Entregador;
import com.verdesaudesaude.api.service.EntregadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/entregadores")
@CrossOrigin(origins = "*")
public class EntregadorController {

    private final EntregadorService entregadorService;

    public EntregadorController(EntregadorService entregadorService) {
        this.entregadorService = entregadorService;
    }

    @GetMapping
    public ResponseEntity<List<Entregador>> listarTodos() {
        return ResponseEntity.ok(entregadorService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Entregador> cadastrar(
            @Valid @RequestBody Entregador entregador) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entregadorService.cadastrar(entregador));
    }

    @PostMapping("/login")
    public ResponseEntity<Entregador> login(
            @RequestBody Map<String, String> dados) {

        Entregador entregador = entregadorService.login(
                dados.get("email"),
                dados.get("senha")
        );

        return ResponseEntity.ok(entregador);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> tratarErro(
            IllegalArgumentException erro) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(erro.getMessage());
    }
}
