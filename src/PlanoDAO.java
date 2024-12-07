import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {

    public void cadastrarPlano(Plano plano) {
        String sql = "INSERT INTO planos (nome, preco, duracao) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getPreco());
            stmt.setString(3, plano.getDuracao());

            stmt.executeUpdate();
            System.out.println("Plano cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar plano: " + e.getMessage());
        }
    }

    public List<Plano> listarPlanos() {
        String sql = "SELECT * FROM planos";
        List<Plano> planos = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Plano plano = new Plano(
                        rs.getInt("id_plano"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("duracao")
                );
                planos.add(plano);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar planos: " + e.getMessage());
        }
        return planos;
    }

    public void atualizarPlano(Plano plano) {
        String sql = "UPDATE planos SET nome = ?, preco = ?, duracao = ? WHERE id_plano = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getPreco());
            stmt.setString(3, plano.getDuracao());
            stmt.setInt(4, plano.getIdPlano());

            stmt.executeUpdate();
            System.out.println("Plano atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar plano: " + e.getMessage());
        }
    }

    public void excluirPlano(int idPlano) {
        String sql = "DELETE FROM planos WHERE id_plano = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPlano);
            stmt.executeUpdate();
            System.out.println("Plano excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir plano: " + e.getMessage());
        }
    }
}
