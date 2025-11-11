package org.example.view;

import org.example.service.DevolucaoService;
import org.example.service.EmprestimoService;
import org.example.service.LivroService;
import org.example.service.UsuarioService;

import java.sql.SQLException;
import java.util.Scanner;

public class BibliotecaView {
    static Scanner input = new Scanner(System.in);
    static BibliotecaView bibliotecaView = new BibliotecaView();

    LivroService livroService = new LivroService();
    EmprestimoService emprestimoService = new EmprestimoService();
    UsuarioService usuarioService = new UsuarioService();
    DevolucaoService devolucaoService = new DevolucaoService();

    public void mostrarMenu() {
        System.out.println("-------------------------");
        System.out.println("Sistema de Biblioteca");
        System.out.println("-------------------------");
        System.out.println("1- Cadastrar Livro       ");
        System.out.println("2- Cadastrar Usuario     ");
        System.out.println("3- Cadastrar Emprestimo  ");
        System.out.println("4- Devolução de Livro    ");
        System.out.println("5- Consultar             ");
        System.out.println("-------------------------");
        System.out.println("0- Sair                  ");
        System.out.println("-------------------------");
        bibliotecaView.capturarOpcao();
    }

    public void capturarOpcao() {
        boolean sair = false;

        while(sair != true) {

            int opcao = input.nextInt();

            switch (opcao) {

                case 1: {
                    livroService.cadastrarLivro();
                    bibliotecaView.mostrarMenu();
                    break;
                }

                case 2: {
                    usuarioService.cadastrarUsuario();
                    bibliotecaView.mostrarMenu();
                    break;
                }

                case 3: {
                    try {
                        emprestimoService.cadastrarEmprestimo();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    bibliotecaView.mostrarMenu();
                    break;
                }

                case 4: {
                    try {
                        devolucaoService.cadastrarDevolucao();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    bibliotecaView.mostrarMenu();
                    break;
                }

                case 5: {
                    bibliotecaView.menuConsulta();
                    bibliotecaView.mostrarMenu();
                    break;
                }

                case 0: {
                    System.out.println("Saindo...");
                    sair = false;
                    break;
                }

                default: {
                    System.out.println("\nOpção incorreta!");
                    bibliotecaView.mostrarMenu();
                    break;
                }
            }
        }
    }

    public void menuConsulta() {
        System.out.println("---------------------------------");
        System.out.println("          Consultas           ");
        System.out.println("---------------------------------");
        System.out.println("1- Consultar Todos os Livros     ");
        System.out.println("2- Consultar Todos os Emprestimos ");
        System.out.println("---------------------------------");
        System.out.println("0- Voltar                        ");
        System.out.println("---------------------------------");
        int opcao = input.nextInt();

        switch (opcao) {

            case 1: {
                try {
                    livroService.listarLivros();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                bibliotecaView.menuConsulta();
                break;
            }

            case 2: {
                try {
                    emprestimoService.listarEmprestimos();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                bibliotecaView.menuConsulta();
                break;
            }

            case 0: {
                System.out.println("Voltando...");
                bibliotecaView.mostrarMenu();
                break;
            }

            default: {
                System.out.println("\nOpção incorreta!");
                bibliotecaView.menuConsulta();
                break;
            }
        }
    }
}
