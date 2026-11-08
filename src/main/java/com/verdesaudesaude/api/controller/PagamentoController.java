package com.verdesaudesaude.api.controller;

import com.verdesaudesaude.api.entity.Pagamento;
import com.verdesaudesaude.api.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pagamento> salvar(@RequestBody Pagamento pagamento) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagamentoService.salvar(pagamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizar(
            @PathVariable Long id,
            @RequestBody Pagamento pagamento) {

        return ResponseEntity.ok(pagamentoService.atualizar(id, pagamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pagamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}