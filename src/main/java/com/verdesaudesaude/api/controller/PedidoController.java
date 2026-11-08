package com.verdesaudesaude.api.controller;

import com.verdesaudesaude.api.entity.Pedido;
import com.verdesaudesaude.api.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.salvar(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(
            @PathVariable Long id,
            @RequestBody Pedido pedido) {

        return ResponseEntity.ok(pedidoService.atualizar(id, pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}