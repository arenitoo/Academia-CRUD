import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conn = Conexao.getConnection()) {
            if (conn != null) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.out.println("Falha na conexão.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
