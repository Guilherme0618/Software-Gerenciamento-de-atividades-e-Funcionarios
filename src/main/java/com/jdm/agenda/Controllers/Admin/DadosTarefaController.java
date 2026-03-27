package com.jdm.agenda.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;

public class DadosTarefaController {
    @FXML
    public TextField txt_tarefa;

    @FXML
    public TextArea txt_descricao;

    @FXML
    public DatePicker data_inicio;

    @FXML
    public DatePicker data_termino;

    @FXML
    public TextArea txt_funcionariosAtribuidos;

    @FXML
    public TextArea txt_descricaoFuncionarios;

    @FXML
    public RadioButton btn_verde;

    @FXML
    public RadioButton btn_amarelo;

    @FXML
    public RadioButton btn_vermelho;

    @FXML
    public Button btn_concluir;

    @FXML
    public Button btn_voltar;


    @FXML
    public void initialize() {
        mostrarAvisoAbertura();
    }

    private void mostrarAvisoAbertura() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso");
        alerta.setHeaderText("Modo de Edição Ativado");
        alerta.setContentText("Você está editando os dados das Tarefas.");
        alerta.showAndWait();

        // Desabilita datas anteriores no início
        data_inicio.setDayCellFactory(picker -> new DateCell() {
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
        data_termino.setDayCellFactory(picker -> new DateCell() {
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
    public void BtnConcluir(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atualização");
        alert.setHeaderText(null);
        alert.setContentText("A atualização foi concluída com sucesso!");
        alert.showAndWait();
    }


    @FXML
    public void BtnVoltar(ActionEvent actionEvent) {
        try {
            Pane rootPane = (Pane) btn_voltar.getParent().getParent();
            Pane paginaVisualizar = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Agenda_pagina.fxml"));
            rootPane.getChildren().setAll(paginaVisualizar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
