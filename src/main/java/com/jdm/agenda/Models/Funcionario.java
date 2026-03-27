package com.jdm.agenda.Models;

import javafx.scene.paint.Color;

public class Funcionario {

    private String nome;
    private String nivel;
    private Color cor;

    private boolean ativo = true;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    public Funcionario(String nome, String nivel, Color cor) {
        this.nome = nome;
        this.nivel = nivel;
        this.cor = cor;
    }

    public String getNome() { return nome; }
    public String getNivel() { return nivel; }
    public Color getCor() { return cor; }
}
