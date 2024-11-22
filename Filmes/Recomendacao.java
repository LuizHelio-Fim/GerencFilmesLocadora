package Filmes;

import java.util.ArrayList;
import java.util.Collections;
import Pessoas.Cliente;

public class Recomendacao {
    
    private Cliente cliente;
    private ArrayList<Filme> recomendacoes;
    private ArrayList<Filme> catalogoFilmes;

    public Recomendacao(Cliente cliente, ArrayList<Filme> catalogoFilmes) {
        this.cliente = cliente;
        this.recomendacoes = new ArrayList<>();
        this.catalogoFilmes = catalogoFilmes;
    }

    // Getters
    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Filme> getRecomendacoes() {
        return recomendacoes;
    }

    public void gerarRecomendacoes() {
    	ArrayList<Locacao> historicoLocacoes = cliente.getHistoricoLocacoes();

        if (historicoLocacoes.isEmpty()) {
            System.out.println("Alugue alguns filmes para gerarmos sua recomendação!");
            return;
        }

        Filme ultimoFilme = historicoLocacoes.get(historicoLocacoes.size() - 1).getFilme();
        String generoDoUltimoFilme = ultimoFilme.getGenero();

        ArrayList<Filme> filmesDoGenero = new ArrayList<>();
        for (Filme filme : catalogoFilmes) {
            if (filme.getGenero().equals(generoDoUltimoFilme) && filme.isDisponivel()) {
                filmesDoGenero.add(filme);
            }
        }

        // Verifica se existem filmes do mesmo gênero disponíveis
        if (filmesDoGenero.isEmpty()) {
            System.out.println("Não há filmes disponíveis deste gênero para recomendar.");
            return;
        }

        // Embaralha a lista de filmes do gênero
        Collections.shuffle(filmesDoGenero);

        // Limita a recomendação a 3 filmes
        for (int i = 0; i < Math.min(3, filmesDoGenero.size()); i++) {
            recomendacoes.add(filmesDoGenero.get(i));
        }

        
        if (recomendacoes.isEmpty()) {
            System.out.println("Não há recomendações disponíveis.");
        } else {
            System.out.println("Recomendações para " + cliente.getNome() + ":");
            for (Filme f : recomendacoes) {
                System.out.println(f.getNome() + " (" + f.getGenero() + ")");
            }
        }
    }
}