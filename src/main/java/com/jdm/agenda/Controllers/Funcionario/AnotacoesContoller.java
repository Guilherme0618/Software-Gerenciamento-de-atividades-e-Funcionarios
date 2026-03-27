package com.jdm.agenda.Controllers.Funcionario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AnotacoesContoller {
    @FXML
    public ColorPicker corFundo_paineltexto;

    @FXML
    public ColorPicker corLetra_paineltexto;

    @FXML
    public MenuButton fontLetra_paineltexto;

    @FXML
    public TextArea painel_textoAnotacoes;

    @FXML
    public ProgressIndicator capacidade_paineltexto;

    @FXML
    public Button bt_sair;

    @FXML
    public void BtnSair(ActionEvent actionEvent) {
        System.exit(0);
    }

}
