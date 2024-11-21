package reserva;

import java.time.LocalDate;

import Filmes.Filme;
import Pessoas.Cliente;

public class reserva {
	private Filme filme;               // Filme que foi reservado
    private String status;           // Status da reserva (por exemplo: "Ativa", "Cancelada")
	private Cliente cliente;
	private LocalDate dataReserva;   // Data em que a reserva foi feita
    
    
    public void Reserva(Filme filme, Cliente cliente) {
        this.filme = filme;
        this.cliente = cliente;
        this.status = "Ativa"; 
    }
    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
    public LocalDate getDataReserva() {
        return dataReserva;
    }
    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public boolean verificarDisponibilidade() {
        return filme.isDisponivel();  // Verifica se o filme está disponível
    }
 
    public void cancelarReserva() {
        if (this.status.equals("Ativa")) {
            this.status = "Cancelada";
            filme.setDisponivel(true); // Marca o filme como disponível novamente
            System.out.println("Reserva cancelada com sucesso.");
        } else {
            System.out.println("A reserva já está cancelada ou concluída.");
        }
    }
    
    public void concluirReserva() {
        if (this.status.equals("Ativa")) {
            this.status = "Concluída";
            filme.setDisponivel(true);  // Marca o filme como disponível após a devolução
            System.out.println("Reserva concluída com sucesso.");
        } else {
            System.out.println("Não é possível concluir uma reserva que não está ativa.");
        }
    }
    public String toString() {
        return "Reserva de Filme: \n" +
        		"data da reserva " + getDataReserva() + "\n" +
               "Filme: " + filme.getNome() + "\n" +
               "Cliente: " + cliente.getNome() + "\n" +
               "Status: " + status + "\n";
    }
}