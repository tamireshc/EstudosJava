module com.example.javafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.javafxjdbc.model.entities to javafx.fxml, javafx.base;
    opens com.example.javafxjdbc to javafx.fxml, javafx.base;
    exports com.example.javafxjdbc;
}