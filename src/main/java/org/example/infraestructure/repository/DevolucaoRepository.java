package org.example.infraestructure.repository;

import org.example.infraestructure.Conexao;
import org.example.model.Emprestimos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DevolucaoRepository {

    public void registrarDevolucaoDeLivro(Emprestimos emprestimos) throws SQLException {
        String query = "UPDATE emprestimos SET data_devolucao = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(emprestimos.getDataDevolucao()));
            stmt.setInt(2, emprestimos.getId());
            stmt.executeUpdate();
        }
    }
}