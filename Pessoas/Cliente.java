package Pessoas;

import java.time.LocalDate;
import java.util.ArrayList;
import Filmes.Filme;
import Filmes.Locacao;
import Filmes.Locadora;

public class Cliente extends Pessoa {
    // Construtores
    public Cliente(int id, String nome, String email, String senha, String endereco, boolean isAdmin) {
        super(id, nome, email, senha, endereco, isAdmin);
    }

    public Cliente() {
        super();
    }

    // Métodos
    public boolean iniciarLocacao(Filme filme, Locadora locadora) { 
        if (filme.isDisponivel()) {
            LocalDate dataDevolucaoPrevista = LocalDate.now().plusDays(7);
            Locacao novaLocacao = new Locacao(filme, this, LocalDate.now(), dataDevolucaoPrevista);

            locadora.adicionarLocacao(novaLocacao);  // Salva no histórico geral da Locadora
            filme.setDisponivel(false);				 // Marca o filme como indisponível

            System.out.println("Locação iniciada com sucesso: " + filme.getNome() 
                             + "\nData da Devolução: " + dataDevolucaoPrevista);
            return true;
        } else {
            System.out.println("O filme '" + filme.getNome() + "' não está disponível para locação.");
            return false;
        }
    }

    public void visualizarHistorico(Locadora locadora) {
        ArrayList<Locacao> historicoLocacoes = locadora.getHistoricoLocacoesCliente(this);

        if (historicoLocacoes.isEmpty()) {
            System.out.println("Nenhuma locação encontrada no histórico.");
        } else {
            System.out.println("Histórico de Locações:");
            for (Locacao locacao : historicoLocacoes) {
                System.out.println(locacao);
            }
        }
    }

    public Locacao devolverFilme(String nomeFilme, LocalDate dataDevolucao, Locadora locadora) {
        ArrayList<Locacao> historicoLocacoes = locadora.getHistoricoLocacoesCliente(this);

        for (Locacao locacao : historicoLocacoes) {
            if (locacao.getFilme().getNome().equalsIgnoreCase(nomeFilme)) {
                return locacao;
            }
        }
        System.out.println("Locação do filme '" + nomeFilme + "' não encontrada.");
        return null;
    }

    public void removerLocacao(Locacao locacao, Locadora locadora) {
        locacao.getFilme().setDisponivel(true);
        locadora.removerLocacao(locacao); // Remove do histórico geral da Locadora
    }
}
