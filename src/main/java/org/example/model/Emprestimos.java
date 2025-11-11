package org.example.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Emprestimos {

    private int id;
    private int livroId;
    private int usuarioId;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;

    public Emprestimos(int id, int livroId, int usuarioId, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) {
        this.id = id;
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimos(int livroId, int usuarioId, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) {
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimos(int livroId, int usuarioId, String dataEmpretismo, String dataDevolucao) {
    }

    public Emprestimos() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livro_id) {
        this.livroId = livro_id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuario_id) {
        this.usuarioId = usuario_id;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime data_emprestimo) {
        this.dataEmprestimo = data_emprestimo;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime data_devolucao) {
        this.dataDevolucao = data_devolucao;
    }
}