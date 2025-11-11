package org.example.service;

import org.example.model.Emprestimos;
import org.example.model.Livros;
import org.example.model.Usuarios;
import org.example.infraestructure.repository.EmprestimoRepository;
import org.example.infraestructure.repository.LivroRepository;
import org.example.infraestructure.repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmprestimoService {
    static Scanner input = new Scanner(System.in);

    static LivroRepository livroRepository = new LivroRepository();
    static UsuarioRepository usuarioRepository = new UsuarioRepository();
    static EmprestimoRepository emprestimoRepository = new EmprestimoRepository();


    public static void cadastrarEmprestimo() throws SQLException {
        System.out.println("\n-----------------------");
        System.out.println("Cadastrar Emprestimo");
        System.out.println("-----------------------");

        List<Livros> listarLivros = livroRepository.listarLivros();

        if (listarLivros.isEmpty()) {
            System.out.println("\nNão existem livros cadastrados.");
            return;
        }

        System.out.println("\nLivros Cadastrados: ");
        for (int i = 0; i < listarLivros.size(); i++) {
            Livros livros = listarLivros.get(i);
            System.out.println((i + 1) + " - Livro ID: " + livros.getId() + ", Titulo: " + livros.getTitulo() + ", Autor: " + livros.getAutor() + ", Ano: " + livros.getAno() + ", Disponivel: " + livros.isDisponivel());
        }

        System.out.println("\nSelecione a id de um livro: ");
        int livro_id = input.nextInt();

        List<Usuarios> listarUsuarios = usuarioRepository.listarUsuarios();

        if (listarUsuarios.isEmpty()) {
            System.out.println("\nNão existem usuarios cadastrados.");
            return;
        }

        System.out.println("\nUsuarios Cadastrados: ");
        for (int i = 0; i < listarUsuarios.size(); i++) {
            Usuarios usuarios = listarUsuarios.get(i);
            System.out.println((i + 1) + " - Usuario ID: " + usuarios.getId() + ", Nome: " + usuarios.getNome() + ", Email: " + usuarios.getEmail());
        }

        System.out.println("\nSelecione o id do usuario que irá pegar o livro: ");
        int usuario_id = input.nextInt();


        java.time.LocalDateTime data_empretismo = java.time.LocalDateTime.now();
        java.time.LocalDateTime data_devolucao = null;


        Emprestimos emprestimos = new Emprestimos(livro_id, usuario_id, data_empretismo, data_devolucao);

        try {
            System.out.println("Empretimo Cadastrado com sucesso!");
            emprestimoRepository.registrarEmprestimoDeLivro(emprestimos);

            Livros livro = new Livros();
            livro.setId(livro_id);
            livro.setDisponivel(false);
            livroRepository.atualizarStatus(livro);


        } catch (SQLException e) {
            System.out.println("Erro ao Cadastrar Empretimo!");
            throw new RuntimeException(e);
        }
    }

    public List<Emprestimos> listarEmprestimos() throws SQLException {
        System.out.println("\n---------------------------");
        System.out.println("Lista dos Emprestimos");
        System.out.println("---------------------------");
        List<Emprestimos> emprestimos = emprestimoRepository.listarEmprestimos();

        if(emprestimos.isEmpty()) {
            System.out.println("Não há empretimos cadastrados!");

        }else {
            System.out.println("\nEmprestimos:");
            for (Emprestimos emprestimo : emprestimos) {
                System.out.println("ID: " + emprestimo.getId() + ", livro_id: " + emprestimo.getLivroId() + ", usuario_id: " + emprestimo.getUsuarioId() + ", Data do Emprestimo: " + emprestimo.getDataEmprestimo() + ", Data de Devolução: " + emprestimo.getDataDevolucao());
            }
        }
        return emprestimos;
    }
}