package com.verdesaudesaude.api.service;

import com.verdesaudesaude.api.entity.Pedido;
import com.verdesaudesaude.api.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido nao encontrado"));
    }

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizar(Long id, Pedido pedido) {
        Pedido pedidoExistente = buscarPorId(id);
        pedidoExistente.setStatus(pedido.getStatus());
        pedidoExistente.setCliente(pedido.getCliente());
        return pedidoRepository.save(pedidoExistente);
    }

    public void excluir(Long id) {
        Pedido pedido = buscarPorId(id);
        pedidoRepository.delete(pedido);
    }
}