module main.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.media;
    requires java.desktop;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires jasperreports;

    opens main.project to javafx.fxml;
    exports main.project;
}