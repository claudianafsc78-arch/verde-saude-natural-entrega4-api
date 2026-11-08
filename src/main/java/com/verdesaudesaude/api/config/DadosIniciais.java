package com.verdesaudesaude.api.config;

import com.verdesaudesaude.api.entity.Produto;
import com.verdesaudesaude.api.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DadosIniciais implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    public DadosIniciais(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) {
        if (produtoRepository.count() > 0) {
            return;
        }

        cadastrar("Tomate Orgânico", "João Silva", 8.00, "kg", "hortalicas");
        cadastrar("Alface Orgânica", "Teresa Maria", 4.50, "un", "hortalicas");
        cadastrar("Cenoura Orgânica", "Ana Souza", 5.00, "kg", "legumes");
        cadastrar("Banana Orgânica", "Carlos Lima", 6.50, "kg", "frutas");
        cadastrar("Ovos Caipira", "Sítio Boa Vida", 18.00, "dz", "outros");
        cadastrar("Cheiro Verde", "Teresa Maria", 3.00, "maço", "outros");
        cadastrar("Mel Puro", "Sítio Boa Vida", 22.00, "un", "outros");
        cadastrar("Pimentão", "Ana Souza", 7.00, "kg", "legumes");
        cadastrar("Cebola Roxa", "João Silva", 6.00, "kg", "legumes");
        cadastrar("Acerola", "Carlos Lima", 9.50, "kg", "frutas");

        System.out.println("10 produtos cadastrados com sucesso!");
    }

    private void cadastrar(String nome, String produtor, Double preco,
                           String unidade, String categoria) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setProdutor(produtor);
        produto.setPreco(preco);
        produto.setUnidade(unidade);
        produto.setCategoria(categoria);
        produto.setEstoque(90);

        produtoRepository.save(produto);
    }
}