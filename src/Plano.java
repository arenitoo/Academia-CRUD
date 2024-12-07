
public class Plano {
    private int idPlano;
    private String nome;
    private double preco;
    private String duracao;

    public Plano(int idPlano, String nome, double preco, String duracao) {
        this.idPlano = idPlano;
        this.nome = nome;
        this.preco = preco;
        this.duracao = duracao;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Plano{" +
                "idPlano=" + idPlano +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", duracao='" + duracao + '\'' +
                '}';
    }

}

