/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.models;

import java.util.Date;

/**
 *
 * @author user
 */
public class Reservas{
    private String nombre;
    private String identificacion;
    private String origen;
    private String pago;
    private Date ingreso;
    private Date salida;
    private String nhabitacion;

    public Reservas(String nombre, String identificacion, String origen, String pago, Date ingreso, Date salida, String nhabitacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.origen = origen;
        this.pago = pago;
        this.ingreso = ingreso;
        this.salida = salida;
        this.nhabitacion = nhabitacion;
    }


    

    
    
    
}
