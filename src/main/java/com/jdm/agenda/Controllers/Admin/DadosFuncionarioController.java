package com.jdm.agenda.Controllers.Admin;

import com.jdm.agenda.Models.FuncionariosData;
import com.jdm.agenda.Models.Funcionario;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DadosFuncionarioController {
    @FXML
    public TextField txt_nomeFuncionario;

    @FXML
    public TextField txt_cpfFuncionario;

    @FXML
    public TextArea txt_habilidadesFuncionario;

    @FXML
    public DatePicker data_nascimento;

    @FXML
    public TextField txt_loginFuncionario;

    @FXML
    public TextField txt_senhaFuncionario;

    @FXML
    public Button Btn_SalvarLogin;

    @FXML
    public CheckBox nivel1;

    @FXML
    public CheckBox nivel2;

    @FXML
    public Button Btn_Salvamento;

    @FXML
    public Button Btn_Voltar;
    
    @FXML
    public Button btn_ativarfuncionario;

    @FXML
    public void initialize() {
        mostrarAvisoAbertura();
    }

    private void mostrarAvisoAbertura() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso");
        alerta.setHeaderText("Modo de Edição Ativado");
        alerta.setContentText("Você está editando os dados dos Funcioários.");
        alerta.showAndWait();
    }


    @FXML
    public void BtnSalvarLogin(ActionEvent actionEvent) {
    }

    @FXML
    public void BtnSalvamentoGeral(ActionEvent actionEvent) {
    }

    @FXML
    public void BtnVoltar(ActionEvent actionEvent) {
        try {
            Pane rootPane = (Pane) Btn_Voltar.getParent().getParent();
            Pane paginaVisualizar = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Visualizar_Funcionarios.fxml"));
            rootPane.getChildren().setAll(paginaVisualizar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BtnAtivarFuncionario(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ativar Funcionário");
        dialog.setHeaderText("Digite o nome do funcionário para confirmar ativacao:");
        dialog.setContentText("Nome:");

        Optional<String> resultado = dialog.showAndWait();

        if (!resultado.isPresent()) {
            return;
        }

        String nomeDigitado = resultado.get().trim().toLowerCase();

        // Procura funcionário
        for (Funcionario f : FuncionariosData.listaFuncionarios) {
            if (f.getNome().toLowerCase().equals(nomeDigitado)) {
                f.setAtivo(true); // ativa
                mostrarMensagem("Funcionário ativado com sucesso!");

                // Recarrega tela de visualização
                try {
                    Pane rootPane = (Pane) btn_ativarfuncionario.getParent().getParent();
                    Pane paginaVisualizar = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Visualizar_Funcionarios.fxml"));
                    rootPane.getChildren().setAll(paginaVisualizar);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return;
            }
        }

        mostrarMensagemErro("Funcionário não encontrado.");
    }

    private void mostrarMensagem(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }

    private void mostrarMensagemErro(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }


}
