package proyecto2p.proyecto_domino;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
    public static ArrayList<Jugador> jugadores;

    @FXML
    private Label mensajeLabel;

    @FXML
    private TextField tf_nombre;

    @FXML
    private Button bt_continuar;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void continuar(ActionEvent event) throws IOException {
        try {
            String nombre = tf_nombre.getText();

            if (!nombre.isEmpty()) {
                ArrayList<Ficha> fichas = Utilitaria.crearManoJugador();
                Jugador jugador = new Jugador(nombre, fichas);
                jugadores = new ArrayList<>();
                jugadores.add(jugador);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
                Parent root = fxmlLoader.load();

                
                SecondaryController secondaryController = fxmlLoader.getController();
                
                secondaryController.initializeData(jugadores);

                Scene scene = new Scene(root);
                Stage newStage = new Stage();
                newStage.setScene(scene);
                newStage.show();

                if (stage != null) {
                    stage.close();
                }
            } else {
                mostrarMensaje("Por favor, ingrese su nombre.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}


