package com.jdm.agenda.Controllers.Admin;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GraficoController {

    @FXML private TextField Pesquiar_funcionalidades;
    @FXML private Button botao_sair;
    @FXML private PieChart graficoPrioridadeTarefa;
    @FXML private LineChart<String, Number> graficoTotalTarefas;
    @FXML private StackedAreaChart<String, Number> graficoUsuariosAtivos;

    @FXML
    private void initialize() {

        botao_sair.setOnAction(e -> handleSairAction());

        Pesquiar_funcionalidades.setOnAction(e -> handlePesquisarAction());

        carregarDadosFicticios();
    }

    @FXML
    private void handleSairAction() {
        Stage stage = (Stage) botao_sair.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handlePesquisarAction() {

    }

    @FXML
    private void carregarDadosFicticios() {
        carregarGraficoPrioridade();
        carregarGraficoTotalTarefas();
        carregarGraficoUsuariosAtivos();
    }


    @FXML
    private void carregarGraficoPrioridade() {
        graficoPrioridadeTarefa.getData().clear();
        graficoPrioridadeTarefa.getData().addAll(
                new PieChart.Data("Alta", 12),
                new PieChart.Data("Média", 20),
                new PieChart.Data("Baixa", 8)
        );
        graficoPrioridadeTarefa.setTitle("Distribuição de Tarefas por Prioridade");
    }


    @FXML
    private void carregarGraficoTotalTarefas() {
        graficoTotalTarefas.getData().clear();

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Tarefas criadas");

        serie.getData().add(new XYChart.Data<>("Jan", 10));
        serie.getData().add(new XYChart.Data<>("Fev", 14));
        serie.getData().add(new XYChart.Data<>("Mar", 18));
        serie.getData().add(new XYChart.Data<>("Abr", 25));
        serie.getData().add(new XYChart.Data<>("Mai", 30));
        serie.getData().add(new XYChart.Data<>("Jun", 22));

        graficoTotalTarefas.getData().add(serie);
        graficoTotalTarefas.setTitle("Evolução de Tarefas por Mês");
    }



    @FXML
    private void carregarGraficoUsuariosAtivos() {

        graficoUsuariosAtivos.getData().clear();

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Usuários Ativos");

        serie.getData().add(new XYChart.Data<>("Seg", 10));
        serie.getData().add(new XYChart.Data<>("Ter", 25));
        serie.getData().add(new XYChart.Data<>("Qua", 40));
        serie.getData().add(new XYChart.Data<>("Qui", 20));
        serie.getData().add(new XYChart.Data<>("Sex", 35));

        graficoUsuariosAtivos.getData().add(serie);
    }

}
