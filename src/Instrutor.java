

public class Instrutor {
    private int idInstrutor;
    private String nome;
    private String especialidade;
    private String telefone;
    private String email;

    public Instrutor(int idInstrutor, String nome, String especialidade, String telefone, String email) {
        this.idInstrutor = idInstrutor;
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
    }

    public int getIdInstrutor() {
        return idInstrutor;
    }

    public void setIdInstrutor(int idInstrutor) {
        this.idInstrutor = idInstrutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Instrutor{" +
                "idInstrutor=" + idInstrutor +
                ", nome='" + nome + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
