/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.models;

/**
 *
 * @author user
 */
public class Hotel {
    
    private String nombre;
    private String ciudad;
    private String direccion;
    private String telefono;

    public Hotel(String nombre, String ciudad, String direccion, String telefono) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    
}
