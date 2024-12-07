import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstrutorDAO {

    public void cadastrarInstrutor(Instrutor instrutor) {
        String sql = "INSERT INTO instrutores (nome, especialidade, telefone, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getEspecialidade());
            stmt.setString(3, instrutor.getTelefone());
            stmt.setString(4, instrutor.getEmail());

            stmt.executeUpdate();
            System.out.println("Instrutor cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar instrutor: " + e.getMessage());
        }
    }

    public List<Instrutor> listarInstrutores() {
        String sql = "SELECT * FROM instrutores";
        List<Instrutor> instrutores = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Instrutor instrutor = new Instrutor(
                        rs.getInt("id_instrutor"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
                instrutores.add(instrutor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar instrutores: " + e.getMessage());
        }
        return instrutores;
    }

    public void atualizarInstrutor(Instrutor instrutor) {
        String sql = "UPDATE instrutores SET nome = ?, especialidade = ?, telefone = ?, email = ? WHERE id_instrutor = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getEspecialidade());
            stmt.setString(3, instrutor.getTelefone());
            stmt.setString(4, instrutor.getEmail());
            stmt.setInt(5, instrutor.getIdInstrutor());

            stmt.executeUpdate();
            System.out.println("Instrutor atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar instrutor: " + e.getMessage());
        }
    }

    public void excluirInstrutor(int idInstrutor) {
        String sql = "DELETE FROM instrutores WHERE id_instrutor = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idInstrutor);
            stmt.executeUpdate();
            System.out.println("Instrutor excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir instrutor: " + e.getMessage());
        }
    }
}
