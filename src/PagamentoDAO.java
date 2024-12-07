import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public void registrarPagamento(Pagamento pagamento) {
        String sql = "INSERT INTO pagamentos (id_cliente, id_plano, data_pagamento, valor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pagamento.getIdCliente());
            stmt.setInt(2, pagamento.getIdPlano());
            stmt.setDate(3, Date.valueOf(pagamento.getDataPagamento()));
            stmt.setDouble(4, pagamento.getValor());

            stmt.executeUpdate();
            System.out.println("Pagamento registrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao registrar pagamento: " + e.getMessage());
        }
    }

    public List<Pagamento> listarPagamentos() {
        String sql = "SELECT * FROM pagamentos";
        List<Pagamento> pagamentos = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pagamento pagamento = new Pagamento(
                        rs.getInt("id_pagamento"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_plano"),
                        rs.getDate("data_pagamento").toLocalDate(),
                        rs.getDouble("valor")
                );
                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pagamentos: " + e.getMessage());
        }
        return pagamentos;
    }
}


