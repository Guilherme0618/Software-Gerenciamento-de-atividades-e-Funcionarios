package com.jdm.agenda.Controllers.Admin;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import java.util.Optional;

import com.jdm.agenda.Models.Funcionario;
import com.jdm.agenda.Models.FuncionariosData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualizarFunController {

    @FXML
    public AnchorPane painel_geralfundo;

    @FXML
    public ScrollPane painel_visualizarFuncionario;

    @FXML
    public Pane painel_quantidadeFuncionario;

    @FXML
    public Button botao_sair;

    @FXML
    public Pane painel_botao;

    @FXML
    public Button botao_cadastrarFuncionario;

    @FXML
    public Button botao_inativarFuncionario;

    @FXML
    public TextField campo_pesquisaFuncionario;

    @FXML
    public FlowPane visualizarFuncionario;

    @FXML
    private void initialize() {
        botao_sair.setOnAction(e -> handleSairAction());
        campo_pesquisaFuncionario.setOnAction(e -> handlePesquisarAction());

        carregarFuncionarios();
    }

    private void handleSairAction() {
        Stage stage = (Stage) botao_sair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlePesquisarAction() {
        System.out.println("Pesquisar Funcionario Clicado!");
    }


    @FXML
    public void Btn_PaginaCadastrar(ActionEvent actionEvent) {
        try {
            AnchorPane novaPagina = FXMLLoader.load(
                    getClass().getResource("/Fxml/Admin/AdicionarFuncionario.fxml")
            );

            AnchorPane rootPane = (AnchorPane) painel_geralfundo.getParent();
            rootPane.getChildren().setAll(novaPagina);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void carregarFuncionarios() {
        visualizarFuncionario.getChildren().clear();

        for (Funcionario f : FuncionariosData.listaFuncionarios) {
            Pane card = criarCardFuncionario(f);
            visualizarFuncionario.getChildren().add(card);
        }
    }



    private Pane criarCardFuncionario(Funcionario f) {

        Pane card = new Pane();
        card.setPrefSize(530, 80);

        Rectangle rect = new Rectangle(530, 80);
        rect.setArcWidth(15);
        rect.setArcHeight(15);

        // 🟦 Se estiver ativo → usar cor normal
        // 🟫 Se estiver inativo → cinza
        if (f.isAtivo()) {
            rect.setFill(f.getCor());
        } else {
            rect.setFill(Color.web("#bfbfbf")); // cinza
        }

        rect.setStroke(Color.WHITE);

        Text txt = new Text(
                f.getNome() + "\nNível: " + f.getNivel()
        );

        txt.setFill(Color.WHITE);
        txt.setStyle("-fx-font-size: 16;");
        txt.setLayoutX(15);
        txt.setLayoutY(30);

        card.getChildren().addAll(rect, txt);

        card.setOnMouseEntered(e -> card.setCursor(javafx.scene.Cursor.HAND));
        card.setOnMouseExited(e -> card.setCursor(javafx.scene.Cursor.DEFAULT));

        card.setOnMouseClicked(e -> {
            try {
                AnchorPane novaPagina = FXMLLoader.load(
                        getClass().getResource("/Fxml/Admin/Dados_Funcionario.fxml")
                );

                AnchorPane rootPane = (AnchorPane) painel_geralfundo.getParent();
                rootPane.getChildren().setAll(novaPagina);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        return card;
    }




    @FXML
    public void Btn_inativar(ActionEvent actionEvent) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Inativar Funcionário");
        dialog.setHeaderText("Digite o nome do funcionário que deseja inativar:");
        dialog.setContentText("Nome:");

        Optional<String> resultado = dialog.showAndWait();

        if (!resultado.isPresent()) {
            return;
        }

        String nomeDigitado = resultado.get().trim().toLowerCase();

        for (Funcionario f : FuncionariosData.listaFuncionarios) {
            if (f.getNome().toLowerCase().equals(nomeDigitado)) {
                f.setAtivo(false);
                carregarFuncionarios();
                return;
            }
        }

        Alert alerta = new Alert(Alert.AlertType.WARNING, "Funcionário não encontrado.");
        alerta.show();
    }



}
