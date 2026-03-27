package com.jdm.agenda.Controllers.Admin;

import javafx.event.ActionEvent;
import com.jdm.agenda.Models.Funcionario;

import com.jdm.agenda.Models.FuncionariosData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AdicionarFuncionarioController {
    @FXML
    public TextField nome_funcionario;

    @FXML
    public DatePicker dataIngresso_corporacao;

    @FXML
    public TextArea text_habilidades;

    @FXML
    public TextField cpf_funcionario;

    @FXML
    public DatePicker nascimento_funcionario;

    @FXML
    public Button Btn_voltar_pagina;

    @FXML
    public TextField login_funcionario;

    @FXML
    public TextField senha_funcionario;

    @FXML
    public RadioButton rdb_nivel1;

    @FXML
    public RadioButton rdb_nivel2;

    @FXML
    public Button btn_cadastrarFuncionario;


    @FXML
    public void BtnVoltarPagina(ActionEvent actionEvent) {
        try {
            Pane rootPane = (Pane) Btn_voltar_pagina.getParent().getParent();
            Pane paginaVisualizar = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Visualizar_Funcionarios.fxml"));
            rootPane.getChildren().setAll(paginaVisualizar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void BtnCadastrarFuncionario(ActionEvent actionEvent) {

        String nome = nome_funcionario.getText();

        if (nome == null || nome.isEmpty()) {
            return;
        }

        String nivel;
        javafx.scene.paint.Color cor;

        // Definir nível + cor
        if (rdb_nivel1.isSelected()) {
            nivel = "Nível 1";
            cor = javafx.scene.paint.Color.web("#74c0fc"); // azul
        } else if (rdb_nivel2.isSelected()) {
            nivel = "Nível 2";
            cor = javafx.scene.paint.Color.web("#f06595");
        } else {
            nivel = "Indefinido";
            cor = javafx.scene.paint.Color.GRAY;
        }

        // Salvar funcionário completo
        FuncionariosData.listaFuncionarios.add(
                new Funcionario(nome, nivel, cor)
        );

        // Retornar à página de Visualizar Funcionários
        try {
            Pane root = (Pane) btn_cadastrarFuncionario.getParent().getParent();
            Pane pagina = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Visualizar_Funcionarios.fxml"));
            root.getChildren().setAll(pagina);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
