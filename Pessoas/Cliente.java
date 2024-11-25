package Pessoas;

import java.time.LocalDate;
import java.util.ArrayList;
import Filmes.Filme;
import Filmes.Locacao;

public class Cliente extends Pessoa {
    private ArrayList<Locacao> filmesReservado = new ArrayList<>();

    // Construtores
    public Cliente(int id,String nome, String email, String senha, String endereco, boolean isAdmin) {
        super(id, nome, email, senha, endereco, false);
        this.filmesReservado = new ArrayList<>();
    }

    public Cliente() {
        super();
    }

    // Getters e Setters
    public ArrayList<Locacao> getHistoricoLocacoes() {
        return new ArrayList<>(filmesReservado);		//Retorna uma cópia
    }

    public void setHistoricoLocacoes(ArrayList<Locacao> historicoLocacoes) {
        this.filmesReservado = historicoLocacoes;
    }
    
    
    public ArrayList<Locacao> getFilmesReservado() {
		return filmesReservado;
	}

	// Métodos
    public boolean iniciarLocacao(Cliente cliente, Filme filme) {									//Cria uma locação associando o filme e o cliente
        if (filme.isDisponivel()) {
        	LocalDate dataDevolucaoPrevista = LocalDate.now().plusDays(7);
        	Locacao novaLocacao = new Locacao(filme, this, LocalDate.now(), dataDevolucaoPrevista);
        	
            if (novaLocacao != null) {
            	filmesReservado.add(novaLocacao);
            }
            filme.setDisponivel(false); 											// Marca o filme como indisponível
            System.out.println("Locação iniciada com sucesso: " + filme.getNome() 
            			    + "\nData da Devolução: " + dataDevolucaoPrevista);
            
            novaLocacao.salvarLocacaoEmArquivo(filme.getNome(), cliente.getEmail(), novaLocacao.getDataLocacao());
            
            return true;
        } else {
        	System.out.println("O filme '" + filme.getNome() + "' não está disponível para locação.");
            return false;
        }
    }

    public void visualizarHistorico() {												//Exibe as locações feitas pelo cliente
        if (filmesReservado.isEmpty()) {
            System.out.println("Nenhuma locação encontrada no histórico.");
        } else {
            System.out.println("Histórico de Locações:");
            for (Locacao locacao : filmesReservado) {
                System.out.println(locacao);
            }
        }
    }
    
    public Locacao devolverFilme(String nomeFilme, LocalDate dataDevolucao) {
        for (Locacao locacao : filmesReservado) {
            if (locacao.getFilme().getNome().equalsIgnoreCase(nomeFilme)) {
                return locacao;
            }
        }
        return null; 
    }

    public void removerLocacao(Locacao locacao) {
    	locacao.getFilme().setDisponivel(true);
        filmesReservado.remove(locacao);
    }

}