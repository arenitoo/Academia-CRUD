
import java.time.LocalDate;

public class Pagamento {
    private int idPagamento;
    private int idCliente;
    private int idPlano;
    private LocalDate dataPagamento;
    private double valor;

    public Pagamento(int idPagamento, int idCliente, int idPlano, LocalDate dataPagamento, double valor) {
        this.idPagamento = idPagamento;
        this.idCliente = idCliente;
        this.idPlano = idPlano;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", idCliente=" + idCliente +
                ", idPlano=" + idPlano +
                ", dataPagamento=" + dataPagamento +
                ", valor=" + valor +
                '}';
    }
}
