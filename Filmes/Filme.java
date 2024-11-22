package Filmes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Filme {
    private int id;
    private String nome;
    private String genero;
    private String diretor;
    private LocalDate dataLancamento;
    private boolean disponivel;

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Construtores
    public Filme(int id, String nome, String genero, String diretor, String dataLancamento, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.diretor = diretor;
        this.dataLancamento = LocalDate.parse(dataLancamento, FORMATTER);
        this.disponivel = disponivel;
    }

    public Filme() {
        this.id = 0;
        this.nome = "";
        this.genero = "";
        this.diretor = "";
        this.dataLancamento = LocalDate.now();
        this.disponivel = true;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getDataLancamentoFormatada() {
        return this.dataLancamento.format(FORMATTER);
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = LocalDate.parse(dataLancamento, FORMATTER);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNome: " + nome + "\nGenero: " + genero + "\nDiretor: " + diretor
                + "\nLançamento: " + dataLancamento.format(FORMATTER) + "\nDisponivel: " + disponivel + "\n\n";
    }
}
