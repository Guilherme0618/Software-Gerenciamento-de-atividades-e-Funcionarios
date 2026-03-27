package com.jdm.agenda.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

public class LoginController {

    @FXML
    public TextField usuario_fld;
    @FXML
    public PasswordField senha_fld;
    @FXML
    public Button btn_login;
    @FXML
    public Label error_lbl;
    @FXML
    public Label painel_lbl;
    @FXML
    public ProgressBar progresso_nivel2;
    @FXML
    public ProgressBar progresso_nivel1;
    @FXML
    public Label quantidadeLogin_lbl;
    @FXML
    public RadioButton rdb_nivel2;
    @FXML
    public RadioButton rdb_nivel1;

    private ToggleGroup grupoNivel;

    @FXML
    public void initialize() {
        // Define ação do botão de login
        btn_login.setOnAction(this::handleLoginButton);

        // Cria grupo para garantir seleção única
        grupoNivel = new ToggleGroup();
        rdb_nivel1.setToggleGroup(grupoNivel);
        rdb_nivel2.setToggleGroup(grupoNivel);

        // Label de erro invisível inicialmente
        error_lbl.setVisible(false);
    }

    private void handleLoginButton(ActionEvent event) {
        String usuario = usuario_fld.getText();
        String senha = senha_fld.getText();
        Toggle selectedToggle = grupoNivel.getSelectedToggle();

        // --- Validação ---
        if (!usuario.equals("") || !senha.equals("")) {
            error_lbl.setText("Usuário ou senha inválidos!");
            error_lbl.setVisible(true);
            return;
        }

        if (selectedToggle == null) {
            error_lbl.setText("Selecione um nível!");
            error_lbl.setVisible(true);
            return;
        }

        // --- Login válido ---
        error_lbl.setVisible(false);

        if (selectedToggle == rdb_nivel1) {
            abrirTela("/Fxml/Funcionario/Funcionario_Funcoes.fxml", event, "Painel do Funcionário");
        } else if (selectedToggle == rdb_nivel2) {
            abrirTela("/Fxml/Admin/Admin_Funcoes.fxml", event, "Painel do Administrador");
        }
    }

    private void abrirTela(String fxmlPath, ActionEvent event, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            error_lbl.setText("Erro ao abrir a tela: " + titulo);
            error_lbl.setVisible(true);
        }



    }
}
