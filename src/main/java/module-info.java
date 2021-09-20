module com.panthers.bank {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    exports com.panthers.bank.view;
    opens com.panthers.bank.view to javafx.fxml;
    opens com.panthers.bank.model to javafx.base;
}