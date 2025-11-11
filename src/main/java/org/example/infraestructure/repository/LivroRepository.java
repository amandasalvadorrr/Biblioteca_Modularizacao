package org.example.infraestructure.repository;

import org.example.infraestructure.Conexao;
import org.example.model.Livros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    public void cadastrarLivro(Livros livros) throws SQLException {
        String query = "INSERT INTO livros (titulo, autor, ano, disponivel) VALUES (?,?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, livros.getTitulo());
            stmt.setString(2, livros.getAutor());
            stmt.setInt(3, livros.getAno());
            stmt.setBoolean(4, livros.isDisponivel());
            stmt.executeUpdate();
        }
    }

    public void atualizarStatus(Livros livros) throws SQLException {
        String query = "UPDATE livros SET disponivel = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, livros.isDisponivel());
            stmt.setInt(2, livros.getId());
            stmt.executeUpdate();
        }
    }

    public List<Livros> listarLivros() throws SQLException {
        List<Livros> livros = new ArrayList<>();
        String query = "SELECT id, titulo, autor, ano, disponivel FROM livros";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Livros livro = new Livros(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano"),
                        rs.getBoolean("disponivel")
                );
                livros.add(livro);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }
}