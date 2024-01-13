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

public class Jugador {

    private String nombre;
    
    private ArrayList<Ficha> mano;
//CONSTRUCTOR JUGADOR
    public Jugador(String nombre, ArrayList<Ficha> mano) {
        this.nombre = nombre;
        this.mano = mano;
    }
    

    public String getNombre() {
        return nombre;
    }

    public Ficha getFicha(int i) {
        if (i >= 0 && i < mano.size()) {
            return mano.get(i);
        }
        return null;
    }

    public ArrayList<Ficha> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Ficha> mano) {
        this.mano = mano;
    }
     public boolean removerFicha(Ficha ficha){
       return this.mano.remove(ficha);
    }

    

    
}
