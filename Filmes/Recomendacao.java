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
    
    public static Locadora locadora = new Locadora();

    // Getters
    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Filme> getRecomendacoes() {
        return new ArrayList<>(recomendacoes); // Retorna uma cópia para evitar modificações externas
    }

    // Gera recomendações com base no gênero do último filme locado
    public void gerarRecomendacoes() {
        if (catalogoFilmes == null || catalogoFilmes.isEmpty()) {
            System.out.println("O catálogo de filmes está vazio. Não é possível gerar recomendações.");
            return;
        }

        ArrayList<Locacao> historicoLocacoes = locadora.getHistoricoLocacoes();

        if (historicoLocacoes == null || historicoLocacoes.isEmpty()) {
            System.out.println("Alugue alguns filmes para gerarmos sua recomendação!");
            return;
        }

        Filme ultimoFilme = historicoLocacoes.get(historicoLocacoes.size() - 1).getFilme();
        String generoDoUltimoFilme = ultimoFilme.getGenero();

        ArrayList<Filme> filmesDoGenero = filtrarFilmesPorGenero(generoDoUltimoFilme);

        // Verifica se existem filmes do mesmo gênero disponíveis
        if (filmesDoGenero.isEmpty()) {
            System.out.println("Não há filmes disponíveis deste gênero para recomendar.");
            return;
        }

        // Gera até 3 recomendações embaralhadas
        recomendacoes = gerarFilmesAleatorios(filmesDoGenero, 3);

        // Exibe as recomendações
        exibirRecomendacoes();
    }

    // Filtra filmes disponíveis do catálogo com base no gênero
    private ArrayList<Filme> filtrarFilmesPorGenero(String genero) {
        ArrayList<Filme> filmesFiltrados = new ArrayList<>();
        for (Filme filme : catalogoFilmes) {
            if (filme.getGenero().equalsIgnoreCase(genero) && filme.isDisponivel()) {
                filmesFiltrados.add(filme);
            }
        }
        return filmesFiltrados;
    }

    // Gera uma lista de filmes aleatórios limitada a um número específico
    private ArrayList<Filme> gerarFilmesAleatorios(ArrayList<Filme> filmes, int limite) {
        Collections.shuffle(filmes); // Embaralha a lista
        return new ArrayList<>(filmes.subList(0, Math.min(limite, filmes.size())));
    }

    // Exibe as recomendações ao cliente
    private void exibirRecomendacoes() {
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
