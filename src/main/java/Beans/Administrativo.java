/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans;

/**
 *
 * @author Juan David
 */
public class Administrativo extends Persona{
    private String estudios;
    private String cargo;
    private String fechaInicio;
    private double calificacionServicio;

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public double getCalificacionServicio() {
        return calificacionServicio;
    }

    public void setCalificacionServicio(double calificacionServicio) {
        this.calificacionServicio = calificacionServicio;
    }

    public Administrativo() {
    }

    public Administrativo(String estudios, String cargo, String fechaInicio, double calificacionServicio) {
        this.estudios = estudios;
        this.cargo = cargo;
        this.fechaInicio = fechaInicio;
        this.calificacionServicio = calificacionServicio;
    }

    public Administrativo(String estudios, String cargo, String fechaInicio, double calificacionServicio, String nombre, int edad) {
        super(nombre, edad);
        this.estudios = estudios;
        this.cargo = cargo;
        this.fechaInicio = fechaInicio;
        this.calificacionServicio = calificacionServicio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Administrativo{");
        sb.append("estudios=").append(estudios);
        sb.append(", cargo=").append(cargo);
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", calificacionServicio=").append(calificacionServicio);
        sb.append('}');
        return sb.toString();
    }
}
