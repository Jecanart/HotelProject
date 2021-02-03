/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.espol.models;

import static edu.espol.models.Habitacion.habitaciones;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class Reservas{
    private String nombre;
    private String identificacion;
    private String origen;
    private String pago;
    private LocalDate ingreso;
    private LocalDate salida;
    private String nhabitacion;
    public static ArrayList<Reservas> reservas=new ArrayList<>();

    public Reservas(String nombre, String identificacion, String origen, String pago, LocalDate ingreso, LocalDate salida, String nhabitacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.origen = origen;
        this.pago = pago;
        this.ingreso = ingreso;
        this.salida = salida;
        this.nhabitacion = nhabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getOrigen() {
        return origen;
    }

    public String getPago() {
        return pago;
    }

    public LocalDate getIngreso() {
        return ingreso;
    }

    public LocalDate getSalida() {
        return salida;
    }

    public String getNhabitacion() {
        return nhabitacion;
    }
        /**metodo que carga las reservas del archivo*/
        public static void cargarReservas(){
        try{
            List<String> lineas= Files.readAllLines(Paths.get("archivos/Reservas.csv"));
            lineas.remove(0);
            for(String linea:lineas){
                try{
                    String[]separado=linea.split(";");
                    Reservas r=new Reservas(separado[0],separado[1],separado[2],separado[3],LocalDate.parse(separado[4]),LocalDate.parse(separado[5]),separado[6]);
                    reservas.add(r);
                }catch(ArrayIndexOutOfBoundsException e){
                    System.err.println("error en linea: "+linea);
                }
            }
        } catch (IOException ex) {
        System.out.println("No leyo el archivo: "+ ex);
    }
    }

    

    
    
    
}
