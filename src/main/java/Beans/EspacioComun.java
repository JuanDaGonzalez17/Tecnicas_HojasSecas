/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Juan David
 */
public class EspacioComun {
    private String descripcion;
    private String horaInicio;
    private String horaCierre;
    private int capacidadMaxima;
    private ArrayList<String> diasServicio;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public ArrayList<String> getDiasServicio() {
        return diasServicio;
    }

    public void setDiasServicio(ArrayList<String> diasServicio) {
        this.diasServicio = diasServicio;
    }
    
    public String toCsvString(){
        return descripcion + "," + horaInicio + "," + horaCierre + "," + capacidadMaxima + "," + String.join("|", diasServicio);
    }
    
    public static EspacioComun fromCsvString(String str){
        String[] partes = str.split(",");
        return new EspacioComun(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]), new ArrayList<String>(Arrays.asList(partes[4].split("\\|"))));
    }

    public EspacioComun() {
    }

    public EspacioComun(String descripcion, String horaInicio, String horaCierre, int capacidadMaxima, ArrayList<String> diasServicio) {
        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.horaCierre = horaCierre;
        this.capacidadMaxima = capacidadMaxima;
        this.diasServicio = diasServicio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EspacioComun{");
        sb.append("descripcion=").append(descripcion);
        sb.append(", horaInicio=").append(horaInicio);
        sb.append(", horaCierre=").append(horaCierre);
        sb.append(", capacidadMaxima=").append(capacidadMaxima);
        sb.append(", diasServicio=").append(diasServicio);
        sb.append('}');
        return sb.toString();
    }
    
    
}
