/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans;

/**
 *
 * @author Juan David
 */
public class Propiedad {
    private int id;
    private double saldoActual;
    private Propietario propietario;
    private double metrosCuadrados;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }
    
    public String toCsvString(){
        return id + "," + saldoActual + "," + propietario.toCsvString() + "," + metrosCuadrados;
    }
    
    public static Propiedad fromCsvString(String str){
        String[] partes = str.split(",");
        return new Propiedad(Integer.parseInt(partes[0]), Double.parseDouble(partes[1]), Propietario.fromCsvString(partes[2]), Double.parseDouble(partes[3]));
    }

    public Propiedad() {
    }

    public Propiedad(int id, double saldoActual, Propietario propietario, double metrosCuadrados) {
        this.id = id;
        this.saldoActual = saldoActual;
        this.propietario = propietario;
        this.metrosCuadrados = metrosCuadrados;
    }
    
    
}
