package com.jdm.agenda.Controllers.Admin;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TextInputDialog;
import com.jdm.agenda.Models.Tarefa;
import com.jdm.agenda.Models.TarefaRepository;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class AgendaController {

    @FXML public DatePicker edit_data;
    @FXML public ScrollPane scrollpane_tarefas;
    @FXML public Button btn_adicionarTarefas;
    @FXML public Button bt_sairLogin;
    @FXML public AnchorPane paginaTarefas;
    public Button btn_excluirTarefas;
    @FXML private FlowPane containerTarefas;

    @FXML
    private void initialize() {
        if (edit_data != null) {
            edit_data.setValue(LocalDate.now());
        }

        bt_sairLogin.setOnAction(e -> handleSairAction());
        carregarTarefas();
    }

    private void handleSairAction() {
        Stage stage = (Stage) bt_sairLogin.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void Btn_Adicionar_Tarefa(ActionEvent actionEvent) {
        try {
            AnchorPane novaPagina = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Adicionar_Tarefas.fxml"));
            AnchorPane rootPane = (AnchorPane) paginaTarefas.getParent();
            rootPane.getChildren().setAll(novaPagina);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarTarefas() {
        containerTarefas.getChildren().clear();

        for (Tarefa tarefa : TarefaRepository.getTarefas()) {

            // Fundo do card
            Rectangle fundo = new Rectangle(220, 130);
            fundo.setArcWidth(15);
            fundo.setArcHeight(15);
            fundo.setFill(tarefa.getCor());

            // Texto formatado
            String textoCompleto = tarefa.getTitulo() + "\n" +
                    "Início: " + tarefa.getDataInicio() + "\n" +
                    "Fim: " + tarefa.getDataFim();

            Label texto = new Label(textoCompleto);
            texto.setWrapText(true);
            texto.setMaxWidth(200);

            // Escolhe a cor automática conforme o fundo
            javafx.scene.paint.Color corFundo = tarefa.getCor();
            double r = corFundo.getRed();
            double g = corFundo.getGreen();
            double b = corFundo.getBlue();

            double luminance = 0.2126 * r + 0.7152 * g + 0.0722 * b;
            String corTextoCss = (luminance < 0.55) ? "white" : "black";

            texto.setStyle(
                    "-fx-text-fill: " + corTextoCss + ";" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-family: 'Segoe UI';" +
                            "-fx-font-weight: 600;"
            );

            // ScrollPane transparente
            ScrollPane scroll = new ScrollPane(texto);
            scroll.setFitToWidth(true);
            scroll.setPrefSize(220, 130);
            scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroll.setStyle(
                    "-fx-background: transparent;" +
                            "-fx-background-color: transparent;" +
                            "-fx-border-color: transparent;"
            );

            // Card final
            StackPane bloco = new StackPane();
            bloco.getChildren().addAll(fundo, scroll);
            bloco.setPrefSize(220, 130);

            bloco.setCursor(Cursor.HAND);

            bloco.setOnMouseClicked(event -> {
                try {
                    AnchorPane novaPagina = FXMLLoader.load(
                            getClass().getResource("/Fxml/Admin/Dados_Tarefas.fxml")
                    );

                    AnchorPane rootPane = (AnchorPane) paginaTarefas.getParent();
                    rootPane.getChildren().setAll(novaPagina);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            containerTarefas.getChildren().add(bloco);
        }
    }

    public void Btn_Excluir_Tarefa(ActionEvent actionEvent) {

        // Janela pedindo o nome da tarefa
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Excluir Tarefa");
        dialog.setHeaderText("Digite o nome da tarefa que deseja excluir:");
        dialog.setContentText("Nome:");

        var resultado = dialog.showAndWait();

        if (!resultado.isPresent()) {
            return; // usuário cancelou
        }

        String nomeDigitado = resultado.get().trim();

        boolean removida = TarefaRepository.removerTarefaPorNome(nomeDigitado);

        if (removida) {
            Alert ok = new Alert(Alert.AlertType.INFORMATION,
                    "Tarefa \"" + nomeDigitado + "\" excluída com sucesso!");
            ok.show();
            carregarTarefas();
        } else {
            Alert erro = new Alert(Alert.AlertType.WARNING,
                    "Nenhuma tarefa com o nome \"" + nomeDigitado + "\" foi encontrada.");
            erro.show();
        }
    }

}
