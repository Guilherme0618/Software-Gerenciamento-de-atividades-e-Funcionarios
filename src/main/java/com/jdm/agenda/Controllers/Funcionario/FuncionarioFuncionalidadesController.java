package com.jdm.agenda.Controllers.Funcionario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class FuncionarioFuncionalidadesController {
    @FXML
    public AnchorPane conteudoPane_func;

    @FXML
    public Button btn_agenda;

    @FXML
    public Button btn_anotacoes;

    @FXML
    public Button btn_configuracao;

    @FXML
    public Text nome_user;


    private void setActiveButton(Button activeButton) {
        // Remove a classe "active" de todos
        btn_agenda.getStyleClass().remove("active");
        btn_anotacoes.getStyleClass().remove("active");
        btn_configuracao.getStyleClass().remove("active");

        // Adiciona a classe "active" ao botão clicado
        if (!activeButton.getStyleClass().contains("active")) {
            activeButton.getStyleClass().add("active");
        }
    }

    public void mudarPagina_agenda(ActionEvent actionEvent) {
        try {
            AnchorPane novaPagina = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Agenda_pagina.fxml"));
            conteudoPane_func.getChildren().setAll(novaPagina);
            setActiveButton(btn_agenda);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mudarPagina_anotacoes(ActionEvent actionEvent) {
        try {
            AnchorPane novaPagina = FXMLLoader.load(getClass().getResource("/Fxml/Funcionario/Anotacoes.fxml"));
            conteudoPane_func.getChildren().setAll(novaPagina);
            setActiveButton(btn_anotacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mudarPagina_config(ActionEvent actionEvent) {
        try {
            AnchorPane novaPagina = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Suporte.fxml"));
            conteudoPane_func.getChildren().setAll(novaPagina);
            setActiveButton(btn_configuracao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
