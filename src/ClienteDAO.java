import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, idade, telefone, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setInt(3, cliente.getIdade());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEmail());

            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, cpf = ?, idade = ?, telefone = ?, email = ? WHERE id_cliente = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setInt(3, cliente.getIdade());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEmail());
            stmt.setInt(6, cliente.getIdCliente());

            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void excluirCliente(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            System.out.println("Cliente excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
}
