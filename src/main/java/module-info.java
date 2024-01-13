module proyecto2p.proyecto_domino {
    requires javafx.controls;
    requires javafx.fxml;

    opens proyecto2p.proyecto_domino to javafx.fxml;
    exports proyecto2p.proyecto_domino;
}
