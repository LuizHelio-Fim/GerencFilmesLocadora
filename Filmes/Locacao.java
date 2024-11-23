package Filmes;

import java.time.LocalDate;
import Pessoas.Cliente;

public class Locacao {
    private Filme filme;
    private Cliente cliente;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucaoPrevista;

    // Construtor
    public Locacao(Filme filme, Cliente cliente, LocalDate dataLocacao, LocalDate dataDevolucaoPrevista) {
        this.filme = filme;
        this.cliente = cliente;
        this.dataLocacao = LocalDate.now();
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        filme.setDisponivel(false);

    }

    public boolean valida(){
        if (filme.isDisponivel()) {
            return true;
        }
        return false;
    }

    // Getters e Setters
    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    //Métodos
    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    @Override
    public String toString() {
        return "Filme: " + filme.getNome() + ", Cliente: " + cliente.getNome() + ", Data: " + dataLocacao;
    }
    
    public void finalizarLocacao() {
    	this.filme.setDisponivel(true);
    }
}