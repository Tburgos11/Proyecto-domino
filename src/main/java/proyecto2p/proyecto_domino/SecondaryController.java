package proyecto2p.proyecto_domino;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class SecondaryController implements Initializable {
    ArrayList<Ficha> lineaJuego=new ArrayList<>();
    @FXML
    private HBox hb_turno;

    @FXML
    private Label lb_turno;

    @FXML
    private HBox hb_jugador;

    @FXML
    private Label lb_jugador;

    @FXML
    private ScrollPane sp_mesa;

    @FXML
    private HBox tablero;

    @FXML
    private HBox hb_mano;

    private static ArrayList<Jugador> jugadores=PrimaryController.jugadores;
    private Jugador jugadorActual;
    private Jugador maquina;
    private int contadorTurno;
    private String Decision="Final";
    //Stage ventana=(Stage) hb_jugador.getScene().getWindow();
    
    
    public void initialize(URL url, ResourceBundle rb) {
        initializeData(jugadores);
        ActualizarLineaJuego();
    }

    
    public void initializeData(ArrayList<Jugador> jugadores) {
        if (jugadores != null && !jugadores.isEmpty()) {
            
            jugadorActual = jugadores.get(0);
            contadorTurno = 1;
            lb_turno.setText(String.valueOf(contadorTurno));
            
            String nombreJugador = jugadorActual.getNombre();
            jugadorActual.setMano(Utilitaria.crearManoJugador());
            maquina=new Jugador("Maquina",Utilitaria.crearManoJugador());
            jugadores.add(maquina);
            lb_jugador.setText("Jugador: " + nombreJugador);
            actualizarMano();
        }
    }
   private void actualizarMano() {
    hb_mano.getChildren().clear();

    ArrayList<Ficha> mano = jugadores.get(0).getMano();

    for (Ficha ficha : mano) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(80);
        ContextMenu contextMenu = new ContextMenu();
            MenuItem inicioMenuItem = new MenuItem("Inicio");
            MenuItem finMenuItem = new MenuItem("Final");

            // Configurar acciones para las opciones del menú
            inicioMenuItem.setOnAction(e -> {
                Decision="Inicio";
                if(agregarFichaLinea(ficha,jugadorActual)){
               jugadorActual=jugadores.get(jugadores.size()-1);
               JugarManoComputadora();
               jugadorActual=jugadores.get(0);
                contadorTurno = contadorTurno+2;
            lb_turno.setText(String.valueOf(contadorTurno));
            for(Ficha f: jugadores.get(1).getMano()){
                System.out.println(f.getImagen());
            }
                }
               
               
               
                 
            });

            finMenuItem.setOnAction(e -> {
                 Decision="Final";
                if(agregarFichaLinea(ficha,jugadorActual)){
                jugadorActual=jugadores.get(jugadores.size()-1);
                JugarManoComputadora();
                jugadorActual=jugadores.get(0);
                 contadorTurno = contadorTurno+2;
            lb_turno.setText(String.valueOf(contadorTurno));
            for(Ficha f: jugadores.get(1).getMano()){
                System.out.println(f.getImagen());
                }
                }
               
            });

         
            contextMenu.getItems().addAll(inicioMenuItem, finMenuItem);

        imageView.setOnMouseClicked(e->{
            if (e.getButton() == MouseButton.PRIMARY) {
                    contextMenu.show(imageView, e.getScreenX(), e.getScreenY());
                }

            
        });
        
        // Asignar imagen a ImageView
        try {
            Image image = new Image(getClass().getResourceAsStream("/img/" + ficha.getImagen()));
            imageView.setImage(image);
            
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + ficha.getImagen());
            e.printStackTrace();
        }

        // Agregar ImageView al HBox
        hb_mano.getChildren().add(imageView);
    }
}
   private void ActualizarLineaJuego(){
       tablero.getChildren().clear();
       for(Ficha ficha: lineaJuego){
          ImageView imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(80);
         try {
            Image image = new Image(getClass().getResourceAsStream("/img/" + ficha.getImagen()));
            imageView.setImage(image);
            imageView.setRotate(-90);
            tablero.getChildren().add(imageView);
            
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + ficha.getImagen());
            e.printStackTrace();
        }
           
       }
       
   }
   private void JugarManoComputadora(){
       boolean jugada=false;
      if(!jugadores.get(1).getMano().isEmpty()){
      for(Ficha f: jugadores.get(1).getMano()){
         if(f.getLado1()==ObtenerValorFinal()){
             System.out.println("encontro ficha");
            jugada= agregarFichaLinea(f, jugadores.get(1));
             break;
         }
             if(f.getLado2()==ObtenerValorInicio()){
                 System.out.println("encontro ficha 2");
                jugada= agregarFichaLinea(f, jugadores.get(1));    
                 break;
             }
             if (FichaComodin.class.isAssignableFrom(f.getClass())) {
                 jugada=agregarFichaLinea(f, jugadores.get(1)); 
                 break;
             }
         
      }
         
      if(jugada==true){
          System.out.println("Movimiento computadora valido");
      }
      if(jugada==false){
          Alert al = new Alert(Alert.AlertType.INFORMATION);
          al.setTitle("Ganaste");
               al.setContentText("Ganaste");
               al.showAndWait();
               Stage ventana=(Stage) hb_jugador.getScene().getWindow();
               ventana.close();
      }
      
              // ventana.close();
                   
      }else{
          Alert al = new Alert(Alert.AlertType.INFORMATION);
               al.setTitle("Ganaste");
               al.setContentText("Ganaste");
               al.showAndWait();
               Stage ventana=(Stage) hb_jugador.getScene().getWindow();
               ventana.close();
               
       
   }
   }
public boolean agregarFichaLinea(Ficha ficha, Jugador jugador) {
    
    if (FichaComodin.class.isAssignableFrom(ficha.getClass())) {
        /*Insertar Ficha Comodin Computadora*/
        if (jugador.getNombre().equals("Maquina")) {
            Random random = new Random();
            int opc = random.nextInt(2) + 1;
            switch (opc) {
                case 1:
                    System.out.println(jugadores.get(1).removerFicha(ficha));
                    ficha.setLado1(ObtenerValorInicio());
                    ficha.setLado2(ObtenerValorInicio());
                    ficha.setImagen();  
                    lineaJuego.add(0, ficha);
                    ActualizarLineaJuego();
                    return true;
                case 2:
                     System.out.println(jugadores.get(1).removerFicha(ficha));
                    ficha.setLado1(ObtenerValorFinal());
                   ficha.setLado2(ObtenerValorFinal());  
                   ficha.setImagen();
                    lineaJuego.add( ficha);
                    ActualizarLineaJuego();
                    return true;
            }
    }else{
            /*Insertar Ficha Comodin Jugador*/
        if (lineaJuego.isEmpty()){ //linea de juego vacia
            TextInputDialog dialogLado1 = new TextInputDialog();
            dialogLado1.setTitle("Valor de lado1");
            dialogLado1.setHeaderText("Ingrese el valor de lado1 para la ficha.");
            dialogLado1.setContentText("Valor de lado1:");
            int lado1 = obtenerValorDesdeDialogo(dialogLado1);
            TextInputDialog dialogLado2 = new TextInputDialog();
            dialogLado2.setTitle("Valor de lado2");
            dialogLado2.setHeaderText("Ingrese el valor de lado2 para la ficha.");
            dialogLado2.setContentText("Valor de lado2:");
            int lado2 = obtenerValorDesdeDialogo(dialogLado2);
            jugador.removerFicha(ficha);
            ficha.setLado1(lado1);
            ficha.setLado2(lado2);
            ficha.setImagen();
            lineaJuego.add(ficha); 
            actualizarMano();
            ActualizarLineaJuego();
            return true;
        } else {/*linea de juego no vacia*/
            if(Decision.equals("Final")){//Insertar al final
            jugador.removerFicha(ficha);
            ficha.setLado1(ObtenerValorFinal());
            ficha.setLado2(ObtenerValorFinal());
            ficha.setImagen();
            lineaJuego.add(ficha);
            actualizarMano();
            ActualizarLineaJuego();
            return true;
               
            }//Insertar al principio
            if(Decision.equals("Inicio")){
           
            actualizarMano();
            ficha.setLado1(ObtenerValorInicio());
            ficha.setLado2(ObtenerValorInicio());
            ficha.setImagen();
            lineaJuego.add(0,ficha);
             jugador.removerFicha(ficha);
            actualizarMano();
            ActualizarLineaJuego();
            return true;  
            }
            /*Fin Insertar Ficha Comodin Jugador*/      
        }
            
        }
    } else {//Agregar Fichas No Comodin//
        /*Insertar Fichas no comodin Computadora*/
         if (jugador.getNombre().equals("Maquina")) {
            if(ficha.getLado1()==ObtenerValorFinal()){
               System.out.println(jugadores.get(1).removerFicha(ficha));
               lineaJuego.add(ficha);
               ActualizarLineaJuego(); 
                System.out.println("todo bein hasta aqui 1");
               return true;
            }else{
                if(ficha.getLado2()==ObtenerValorInicio()){
               System.out.println(jugadores.get(1).removerFicha(ficha));
               lineaJuego.add(0,ficha);
               ActualizarLineaJuego();  
                System.out.println("todo bein hasta aqui");
               return true;
                   
                }
            }  
    }else{/*Fin de  Fichas no comodin Computadora*/
        /*Insertar Ficha no comodin Jugador*/ 
        if(!lineaJuego.isEmpty()){//Linea de juego no vacia//
        if(Decision.equals("Inicio")){
           if(ficha.getLado2()==ObtenerValorInicio()){
               lineaJuego.add(0,ficha);
               Alert al = new Alert(Alert.AlertType.INFORMATION);
               al.setTitle("Confirmacion");
               al.setContentText("Ficha Agregada con exito");
               jugador.removerFicha(ficha);
               actualizarMano();
               ActualizarLineaJuego();
               return true;
           }
           else{
               Alert al = new Alert(Alert.AlertType.ERROR);
               al.setTitle("Error");
               al.setContentText("Ficha Invalida");
               al.showAndWait();
               return false;
                 
           }
        }  
        if(Decision.equals("Final")){
           if(ficha.getLado1()==ObtenerValorFinal()){
             lineaJuego.add(ficha);
               Alert al = new Alert(Alert.AlertType.INFORMATION);
               al.setTitle("Confirmacion");
               al.setContentText("Ficha Agregada con exito");
               jugador.removerFicha(ficha);
                actualizarMano();
               ActualizarLineaJuego();
                al.showAndWait();
                return true;
           }
           else{
               Alert al = new Alert(Alert.AlertType.ERROR);
               al.setTitle("Error");
               al.setContentText("Ficha Invalida");
               al.showAndWait();   
               return false;
           }
        } 
        }else{//Linea de juego  vacia//
            lineaJuego.add(ficha);
             Alert al = new Alert(Alert.AlertType.INFORMATION);
             al.setTitle("Confirmacion");
               al.setContentText("Ficha Agregada con exito");
               jugador.removerFicha(ficha);
               actualizarMano();
               ActualizarLineaJuego();
               al.showAndWait();
               return true;
            
            
        }
         }
        
    }
        

    return false;
}

private int obtenerValorDesdeDialogo(TextInputDialog dialog) {
    Optional<String> resultado = dialog.showAndWait();
    return resultado.map(s -> Integer.parseInt(s)).orElse(-1);
}
   public int ObtenerValorInicio(){
        if(!lineaJuego.isEmpty()){
           Ficha fichaInicio= lineaJuego.get(0);
           return fichaInicio.getLado1();
        }
       return 0; 
    }
    public int ObtenerValorFinal(){
       if(!lineaJuego.isEmpty()){
           Ficha fichaInicio=lineaJuego.get(lineaJuego.size()-1);
           return fichaInicio.getLado2();
        }
       return 0;  
}
    @FXML
    void Rendirse(ActionEvent event) {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
             al.setTitle("Rendirse");
               al.setContentText("Perdiste ññ");
               al.show();
        Stage ventana=(Stage) hb_jugador.getScene().getWindow();
               ventana.close();
               
        

    }
}
 





    
    
