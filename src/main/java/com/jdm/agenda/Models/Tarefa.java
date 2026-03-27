package com.jdm.agenda.Models;

import javafx.scene.paint.Color;
import java.time.LocalDate;

public class Tarefa {

    private String titulo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Color cor;

    public Tarefa(String titulo, LocalDate dataInicio, LocalDate dataFim, Color cor) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cor = cor;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Color getCor() {
        return cor;
    }
}
