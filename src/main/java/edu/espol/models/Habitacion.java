/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.models;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Habitacion {
    private String nHabitacion;
    private int precio;
    private String servicios;
    private String categoria;
    
    public static ArrayList<Habitacion> Habitaciones=new ArrayList<>();
    
    public Habitacion(String nHabitacion, int precio, String servicios, String categoria) {
        this.nHabitacion = nHabitacion;
        this.precio = precio;
        this.servicios = servicios;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "nHabitacion=" + nHabitacion + ", precio=" + precio + ", servicios=" + servicios + ", categoria=" + categoria + '}';
    }

    public String getnHabitacion() {
        return nHabitacion;
    }
    
    
}
