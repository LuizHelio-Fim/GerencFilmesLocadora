package Pessoas;

import java.time.LocalDate;
import java.util.ArrayList;
import Filmes.Filme;
import Filmes.Locacao;

public class Cliente extends Pessoa {
    private ArrayList<Locacao> historicoLocacoes = new ArrayList<>();

    // Construtores
    public Cliente(int id,String nome, String email, String senha, String endereco, boolean isAdmin) {
        super(id, nome, email, senha, endereco, false);
        this.historicoLocacoes = new ArrayList<>();
    }

    public Cliente() {
        super();
    }

    // Getters e Setters
    public ArrayList<Locacao> getHistoricoLocacoes() {
        return new ArrayList<>(historicoLocacoes);		//Retorna uma cópia
    }

    public void setHistoricoLocacoes(ArrayList<Locacao> historicoLocacoes) {
        this.historicoLocacoes = historicoLocacoes;
    }

    // Métodos
    public boolean iniciarLocacao(Cliente cliente, Filme filme) {									//Cria uma locação associando o filme e o cliente
        if (filme.isDisponivel()) {
        	LocalDate dataDevolucaoPrevista = LocalDate.now().plusDays(7);
        	Locacao novaLocacao = new Locacao(filme, this, LocalDate.now(), dataDevolucaoPrevista);
        	
            if (novaLocacao != null) {
            	historicoLocacoes.add(novaLocacao);
            }
            filme.setDisponivel(false); 											// Marca o filme como indisponível
            System.out.println("Locação iniciada com sucesso: " + filme.getNome() 
            			    + "\nData da Devolução: " + dataDevolucaoPrevista);
            return true;
        } else {
        	System.out.println("O filme '" + filme.getNome() + "' não está disponível para locação.");
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
    
    public Locacao devolverFilme(String nomeFilme, LocalDate dataDevolucao) {
        for (Locacao locacao : historicoLocacoes) {
            if (locacao.getFilme().getNome().equalsIgnoreCase(nomeFilme)) {
                return locacao;
            }
        }
        return null; 
    }

    public void removerLocacao(Locacao locacao) {
    	locacao.getFilme().setDisponivel(true);
        historicoLocacoes.remove(locacao);
    }

}