package com.jdm.agenda.Controllers.Admin;

import javafx.scene.control.DateCell;
import java.time.LocalDate;
import com.jdm.agenda.Models.Tarefa;
import com.jdm.agenda.Models.TarefaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.io.IOException;

public class AdicionarTarefasController {

    @FXML private Pane painel_dadosTarefas;
    @FXML private Button btn_voltar;
    @FXML private Button Adicionar_tarefa;
    @FXML private TextField titulo_tarefa;
    @FXML private DatePicker data_inicioTarefa;
    @FXML private TextArea info_tarefa;
    @FXML private DatePicker data_fimTarefa;
    @FXML private ComboBox atribuir_user;
    @FXML private TextArea info_avisoUser;

    @FXML private RadioButton corVerde;
    @FXML private RadioButton corAmarela;
    @FXML private RadioButton corVermelha;

    private ToggleGroup grupoCores;

    @FXML
    public void initialize() {

        grupoCores = new ToggleGroup();
        corVerde.setToggleGroup(grupoCores);
        corAmarela.setToggleGroup(grupoCores);
        corVermelha.setToggleGroup(grupoCores);
        corVerde.setSelected(true);

        // Desabilita datas anteriores no início
        data_inicioTarefa.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setDisable(false);
                    return;
                }
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        // Desabilita datas anteriores no término
        data_fimTarefa.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setDisable(false);
                    return;
                }
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                }
            }
        });
    }

    @FXML
    public void BtnVoltarPaginaTarefa(ActionEvent event) {
        try {
            Pane rootPane = (Pane) btn_voltar.getParent().getParent();
            Pane paginaAgenda = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Agenda_pagina.fxml"));
            rootPane.getChildren().setAll(paginaAgenda);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Btn_adicionar_tarefa(ActionEvent event) {

        String titulo = titulo_tarefa.getText();
        LocalDate dataInicio = data_inicioTarefa.getValue();
        LocalDate dataFim = data_fimTarefa.getValue();

        // Determina a cor conforme o RadioButton selecionado
        Color cor = Color.LIGHTGREEN;
        if (corAmarela.isSelected()) {
            cor = Color.LIGHTYELLOW;
        } else if (corVermelha.isSelected()) {
            cor = Color.LIGHTCORAL;
        }

        // Criar tarefa corretamente
        Tarefa novaTarefa = new Tarefa(titulo, dataInicio, dataFim, cor);
        TarefaRepository.adicionarTarefa(novaTarefa);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tarefa adicionada");
        alert.setHeaderText(null);
        alert.setContentText("A tarefa \"" + titulo + "\" foi adicionada com sucesso!");
        alert.showAndWait();

        try {
            Pane rootPane = (Pane) Adicionar_tarefa.getParent().getParent();
            Pane paginaAgenda = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Agenda_pagina.fxml"));
            rootPane.getChildren().setAll(paginaAgenda);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
