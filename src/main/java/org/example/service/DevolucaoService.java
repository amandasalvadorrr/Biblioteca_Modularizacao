package org.example.service;

import org.example.model.Emprestimos;
import org.example.model.Livros;
import org.example.infraestructure.repository.DevolucaoRepository;
import org.example.infraestructure.repository.EmprestimoRepository;
import org.example.infraestructure.repository.LivroRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class DevolucaoService {
    static Scanner input = new Scanner(System.in);

    static EmprestimoService emprestimoService = new EmprestimoService();
    static EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
    static LivroRepository livroRepository = new LivroRepository();
    static DevolucaoRepository devolucaoRepository = new DevolucaoRepository();

    public void cadastrarDevolucao() throws SQLException {
        System.out.println("\n-----------------------");
        System.out.println("Cadastrar Devolução");
        System.out.println("-----------------------");

        List<Emprestimos> emprestimos = emprestimoService.listarEmprestimos();

        if (emprestimos.isEmpty()) {
            System.out.println("\nNão há empréstimos registrados!");
            return;
        }

        System.out.println("\nSelecione o ID do empréstimo a ser devolvido: ");
        int emprestimo_id = input.nextInt();

        Emprestimos emprestimoSelecionado = null;
        for (Emprestimos emp : emprestimos) {
            if (emp.getId() == emprestimo_id) {
                emprestimoSelecionado = emp;
                break;
            }
        }

        if (emprestimoSelecionado == null) {
            System.out.println("\nEmpréstimo não encontrado!");
            return;
        }

        emprestimoSelecionado.setDataDevolucao(LocalDateTime.now());

        try {
            devolucaoRepository.registrarDevolucaoDeLivro(emprestimoSelecionado);

            Livros livro = new Livros();
            livro.setId(emprestimoSelecionado.getLivroId());
            livro.setDisponivel(true);
            livroRepository.atualizarStatus(livro);

            System.out.println("Devolução registrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao registrar devolução!");
            throw new RuntimeException(e);
        }
    }
}
