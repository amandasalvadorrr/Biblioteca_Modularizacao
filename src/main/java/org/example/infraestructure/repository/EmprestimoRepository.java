package org.example.infraestructure.repository;

import org.example.infraestructure.Conexao;
import org.example.model.Emprestimos;
import org.example.model.Livros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    public void registrarEmprestimoDeLivro(Emprestimos emprestimos) throws SQLException {
        String query = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES (?,?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, emprestimos.getLivroId());
            stmt.setInt(2, emprestimos.getUsuarioId());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(emprestimos.getDataEmprestimo()));
            java.time.LocalDateTime dataDevolucao = emprestimos.getDataDevolucao();

            if (dataDevolucao != null) {
                stmt.setTimestamp(4, java.sql.Timestamp.valueOf(dataDevolucao));
            } else {
                stmt.setTimestamp(4, null);
            }
            stmt.executeUpdate();
        }
    }

    public List<Emprestimos> listarEmprestimos() throws SQLException {
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimos";
        List<Emprestimos> emprestimos = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                java.time.LocalDateTime dataEmprestimo = rs.getTimestamp("data_emprestimo").toLocalDateTime();

                java.sql.Timestamp tsDevolucao = rs.getTimestamp("data_devolucao");
                java.time.LocalDateTime dataDevolucao = null;

                if (tsDevolucao != null) {
                    dataDevolucao = tsDevolucao.toLocalDateTime();
                }

                Emprestimos emprestimo = new Emprestimos(
                        rs.getInt("id"),
                        rs.getInt("livro_id"),
                        rs.getInt("usuario_id"),
                        dataEmprestimo,
                        dataDevolucao
                );
                emprestimos.add(emprestimo);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }
}