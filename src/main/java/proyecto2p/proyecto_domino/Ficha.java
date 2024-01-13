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

public class Ficha {

    private int lado1;
    private int lado2;

    public void setLado1(int lado1) {
        this.lado1 = lado1;
        
    }

    public void setLado2(int lado2) {
        this.lado2 = lado2;
    }

    public void setImagen() {
        this.imagen =lado1 + "-" + lado2 + ".jpg";
    }
    private String imagen;

    public Ficha(int lado1, int lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.imagen = lado1 + "-" + lado2 + ".jpg";
    }
    

    public int getLado1() {
        return lado1;
    }

    public int getLado2() {
        return lado2;
    }

    public String getImagen() {
        return imagen;
    }

    
}
