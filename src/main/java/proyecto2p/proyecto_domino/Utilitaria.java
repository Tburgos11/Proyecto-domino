/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2p.proyecto_domino;

/**
 *
 * @author Thomas Burgos
 */
import java.util.ArrayList;
import java.util.Collections;

public class Utilitaria {

    
public static ArrayList<Ficha> crearManoJugador() {
    ArrayList<Ficha> mano = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
        int lado1 = (int) (Math.random() * 6) + 1;
        // Generar lado2 en un rango igual o mayor a lado1
        int lado2 = (int) (Math.random() * (7 - lado1)) + lado1;
        mano.add(new Ficha(lado1, lado2));
    }

    mano.add(new FichaComodin());

    return mano;
}
}
