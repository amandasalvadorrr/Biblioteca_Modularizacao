package org.example.service;

import org.example.model.Livros;
import org.example.model.Usuarios;
import org.example.infraestructure.repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {
    static Scanner input = new Scanner(System.in);
    UsuarioRepository usuarioRepository = new UsuarioRepository();


    public void cadastrarUsuario() {
        System.out.println("\n---------------------");
        System.out.println("Cadastrar Usuario ");
        System.out.println("---------------------");

        System.out.println("Escreva o nome do usuario: ");
        String nome = input.nextLine();

        System.out.println("Escreva o email do usuario: ");
        String email = input.nextLine();


        Usuarios usuarios = new Usuarios(nome, email);

        try {
            System.out.println("Usuario Cadastrado com sucesso!");
            usuarioRepository.cadastrarUsuario(usuarios);

        } catch (SQLException e) {
            System.out.println("Erro ao Cadastrar Usuario!");
            throw new RuntimeException(e);
        }
    }

    public void listarUsuarios() throws SQLException {
        System.out.println("\n----------------");
        System.out.println("Lista dos Livros");
        System.out.println("----------------");
        List<Usuarios> usuarios = usuarioRepository.listarUsuarios();

        if(usuarios.isEmpty()) {
            System.out.println("Não há usuarios cadastrados!");

        }else {
            System.out.println("\nUsuarios:");
            for (Usuarios usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", Email: " + usuario.getEmail());
            }
        }
    }
}