package com.jdm.agenda.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SuporteController implements Initializable {
    @FXML
    public Button botao_sair;
    public Hyperlink hyperlink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        botao_sair.setOnAction(e -> handleSairAction());
    }


    private void handleSairAction() {
        System.out.println("Sair clicado");

        // Fecha a janela ao clicar no botão "Sair"
        Stage stage = (Stage) botao_sair.getScene().getWindow();
        stage.close();
    }

    public void HyperLinlkSuporte(ActionEvent actionEvent) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://business.whatsapp.com/?lang=pt_BR"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
