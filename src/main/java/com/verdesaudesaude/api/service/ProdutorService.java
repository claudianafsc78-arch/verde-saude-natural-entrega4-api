package com.verdesaudesaude.api.service;

import com.verdesaudesaude.api.entity.Produtor;
import com.verdesaudesaude.api.repository.ProdutorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutorService {

    private final ProdutorRepository produtorRepository;
    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public ProdutorService(ProdutorRepository produtorRepository) {
        this.produtorRepository = produtorRepository;
    }

    public List<Produtor> listarTodos() {
        return produtorRepository.findAll();
    }

    public Produtor buscarPorId(Long id) {
        return produtorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produtor não encontrado"));
    }

    public Produtor salvar(Produtor produtor) {
        if (produtorRepository.existsByEmail(produtor.getEmail())) {
            throw new IllegalArgumentException(
                    "Este e-mail já está cadastrado");
        }

        produtor.setSenha(
                passwordEncoder.encode(produtor.getSenha())
        );

        return produtorRepository.save(produtor);
    }

    public Produtor atualizar(Long id, Produtor produtor) {
        Produtor produtorExistente = buscarPorId(id);

        if (!produtorExistente.getEmail().equals(produtor.getEmail())
                && produtorRepository.existsByEmail(produtor.getEmail())) {
            throw new IllegalArgumentException(
                    "Este e-mail já está cadastrado");
        }

        produtorExistente.setNome(produtor.getNome());
        produtorExistente.setEmail(produtor.getEmail());
        produtorExistente.setTelefone(produtor.getTelefone());
        produtorExistente.setPropriedade(produtor.getPropriedade());
        produtorExistente.setEndereco(produtor.getEndereco());
        produtorExistente.setProdutos(produtor.getProdutos());

        if (produtor.getSenha() != null
                && !produtor.getSenha().isBlank()) {
            produtorExistente.setSenha(
                    passwordEncoder.encode(produtor.getSenha())
            );
        }

        return produtorRepository.save(produtorExistente);
    }

        public void excluir(Long id) {
        Produtor produtor = buscarPorId(id);
        produtorRepository.delete(produtor);
    }
}