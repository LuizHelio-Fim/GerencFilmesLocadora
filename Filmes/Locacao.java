package Filmes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    
    public void salvarLocacaoEmArquivo(String emailCliente, String nomeFilme, LocalDate dataLocacao) {
        String caminhoArquivo = "historicoLocacoes.txt"; 
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            String linha = String.format("%s : %s : Data Locação: %s", nomeFilme, emailCliente, dataLocacao.format(formatoData));
            
            writer.write(linha);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Erro ao salvar locação no arquivo: " + e.getMessage());
        }
    }


    @Override
    public String toString() {
        return "Filme: " + filme.getNome() + ", Data: " + dataLocacao 
        		+ " Data Devolução:" + dataDevolucaoPrevista;
    }

}