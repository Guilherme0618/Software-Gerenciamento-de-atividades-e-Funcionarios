package com.jdm.agenda.Models;

import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {
    private static final List<Tarefa> tarefas = new ArrayList<>();

    public static void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public static List<Tarefa> getTarefas() {
        return tarefas;
    }

    public static boolean removerTarefaPorNome(String nome) {
        return tarefas.removeIf(t -> t.getTitulo().equalsIgnoreCase(nome));
    }
}
