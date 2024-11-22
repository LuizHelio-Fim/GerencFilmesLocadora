package Pessoas;

import java.util.ArrayList;
import Filmes.Filme;
import Filmes.Locacao;

public class Cliente extends Pessoa {
    private ArrayList<Locacao> historicoLocacoes = new ArrayList<>();

    // Construtores
    public Cliente(int id,String nome, String email, String senha, String endereco, boolean isAdmin) {
        super(id, nome, email, senha, endereco, isAdmin);
    }

    public Cliente() {
        super();
    }

    // Getters e Setters
    public ArrayList<Locacao> getHistoricoLocacoes() {
        return historicoLocacoes;
    }

    public void setHistoricoLocacoes(ArrayList<Locacao> historicoLocacoes) {
        this.historicoLocacoes = historicoLocacoes;
    }

    // Métodos
    public boolean iniciarLocacao(Filme filme) {									//Cria uma locação associando o filme e o cliente
        if (filme.isDisponivel()) {
            Locacao novaLocacao = new Locacao(filme, this);
            historicoLocacoes.add(novaLocacao);
            filme.setDisponivel(false); 											// Marca o filme como indisponível
            System.out.println("Locação iniciada com sucesso: " + filme.getNome());
            return true;
            
        } else {
            System.out.println("O filme não está disponível para locação.");
            return false;
        }
    }

    public void visualizarHistorico() {												//Exibe as locações feitas pelo cliente
        if (historicoLocacoes.isEmpty()) {
            System.out.println("Nenhuma locação encontrada no histórico.");
        } else {
            System.out.println("Histórico de Locações:");
            for (Locacao locacao : historicoLocacoes) {
                System.out.println(locacao);
            }
        }
    }
}
