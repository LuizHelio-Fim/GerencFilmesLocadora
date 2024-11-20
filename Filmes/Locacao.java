package Filmes;

import java.time.LocalDate;
import Pessoas.Pessoa;

public class Locacao {
    private Filme filme;
    private Pessoa cliente;
    private LocalDate dataLocacao;

    // Construtor
    public Locacao(Filme filme, Pessoa cliente) {
        this.filme = filme;
        this.cliente = cliente;
        this.dataLocacao = LocalDate.now();
    }

    // Getters e Setters
    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    @Override
    public String toString() {
        return "Filme: " + filme.getNome() + ", Cliente: " + cliente.getNome() + ", Data: " + dataLocacao;
    }
    
    public double calcularMultaAtraso() {
    	
    }
    
    public void finalizarLocacao() {
    	
    }
}
