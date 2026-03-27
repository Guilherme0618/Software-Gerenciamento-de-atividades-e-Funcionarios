module com.jdm.agenda {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.net.http;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;

    // Abrindo o pacote para o javafx.fxml ter acesso aos controllers
    opens com.jdm.agenda.Controllers.Admin to javafx.fxml;

    // Expondo outros pacotes
    exports com.jdm.agenda;
    exports com.jdm.agenda.Controllers;
    exports com.jdm.agenda.Controllers.Admin;
    exports com.jdm.agenda.Controllers.Funcionario;
    exports com.jdm.agenda.Models;
    exports com.jdm.agenda.Views;
}
