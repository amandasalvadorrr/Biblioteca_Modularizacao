package org.example.service;

import org.example.model.Livros;
import org.example.infraestructure.repository.LivroRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LivroService {
    static Scanner input = new Scanner(System.in);
    LivroRepository livroRepository = new LivroRepository();


    public void cadastrarLivro() {
        System.out.println("\n-----------------");
        System.out.println("Cadastrar Livro");
        System.out.println("-----------------");

        System.out.println("Escreva o titulo do livro: ");
        String titulo = input.nextLine();

        if (titulo.isEmpty()) {
            System.out.println("\nErro: Você não cadastrou um titulo!");
        }

        System.out.println("Escreva o autor do livro: ");
        String autor = input.nextLine();

        if (autor.isEmpty()) {
            System.out.println("\nErro: Você não cadastrou um autor!");
            return;
        }

        System.out.println("Digite o ano de lançamento do livro: ");
        int ano = input.nextInt();

        boolean disponivel = true;


        Livros livros = new Livros(titulo, autor, ano, disponivel);

        try {
            System.out.println("Livro Cadastrado com sucesso!");
            livroRepository.cadastrarLivro(livros);

        } catch (SQLException e) {
            System.out.println("Erro ao Cadastrar Livro! ");
            throw new RuntimeException(e);
        }
    }


    public void listarLivros() throws SQLException {
        System.out.println("\n-------------------");
        System.out.println("Lista dos Livros");
        System.out.println("-------------------");
        List<Livros> livros = livroRepository.listarLivros();

        if(livros.isEmpty()) {
            System.out.println("Não há livros cadastrados!");

        }else {
            System.out.println("\nLivros:");
            for (Livros livro : livros) {
                System.out.println("ID: " + livro.getId() + ", Titulo: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", Ano de Lançamento: " + livro.getAno() + ", Disponivel: " + livro.isDisponivel());
            }
        }
    }


}