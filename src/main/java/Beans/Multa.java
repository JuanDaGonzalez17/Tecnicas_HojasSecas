/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans;

/**
 *
 * @author Juan David
 */
public class Multa {
    
    private int ID;
    private String fechaMulta;
    private String fechaEventoMulta;
    private EspacioComun espacioComun;
    private Propiedad propiedad;
    private Propietario propietario;
    private String personaOriginariaEvento;
    private String descripcionEvento;
    private String evento;
    private double valorMulta;
    private String fechaMaximaPago;
    private String observacion;
    private String mensaje;
    private String evidencia;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFechaMulta() {
        return fechaMulta;
    }

    public void setFechaMulta(String fechaMulta) {
        this.fechaMulta = fechaMulta;
    }

    public String getFechaEventoMulta() {
        return fechaEventoMulta;
    }

    public void setFechaEventoMulta(String fechaEventoMulta) {
        this.fechaEventoMulta = fechaEventoMulta;
    }

    public EspacioComun getEspacioComun() {
        return espacioComun;
    }

    public void setEspacioComun(EspacioComun espacioComun) {
        this.espacioComun = espacioComun;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public String getPersonaOriginariaEvento() {
        return personaOriginariaEvento;
    }

    public void setPersonaOriginariaEvento(String personaOriginariaEvento) {
        this.personaOriginariaEvento = personaOriginariaEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public double getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(double valorMulta) {
        this.valorMulta = valorMulta;
    }

    public String getFechaMaximaPago() {
        return fechaMaximaPago;
    }

    public void setFechaMaximaPago(String fechaMaximaPago) {
        this.fechaMaximaPago = fechaMaximaPago;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }
    
    public String toCsvString(){
        return ID + ":" + fechaMulta + ":" + fechaEventoMulta + ":" + espacioComun.toCsvString() + ":" + propiedad.toCsvString() + ":" + propietario.toCsvString() + ":" + personaOriginariaEvento + ":" + descripcionEvento + ":" + evento + ":" + valorMulta + ":" + fechaMaximaPago + ":" + observacion + ":" + mensaje + ":" + evidencia;
    }
    
    public static Multa fromCsvString(String str){
        String[] partes = str.split(":");
        return new Multa(Integer.parseInt(partes[0]), partes[1], partes[2], EspacioComun.fromCsvString(partes[3]), Propiedad.fromCsvString(partes[4]), Propietario.fromCsvString(partes[5]), partes[6], partes[7], partes[8], Double.parseDouble(partes[9]), partes[10], partes[11], partes[12], partes[13]);
    }

    public Multa() {
    }

    public Multa(int ID, String fechaMulta, String fechaEventoMulta, EspacioComun espacioComun, Propiedad propiedad, Propietario propietario, String personaOriginariaEvento, String descripcionEvento, String evento, double valorMulta, String fechaMaximaPago, String observacion, String mensaje, String evidencia) {
        this.ID = ID;
        this.fechaMulta = fechaMulta;
        this.fechaEventoMulta = fechaEventoMulta;
        this.espacioComun = espacioComun;
        this.propiedad = propiedad;
        this.propietario = propietario;
        this.personaOriginariaEvento = personaOriginariaEvento;
        this.descripcionEvento = descripcionEvento;
        this.evento = evento;
        this.valorMulta = valorMulta;
        this.fechaMaximaPago = fechaMaximaPago;
        this.observacion = observacion;
        this.mensaje = mensaje;
        this.evidencia = evidencia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Multa{");
        sb.append("ID=").append(ID);
        sb.append(", fechaMulta=").append(fechaMulta);
        sb.append(", fechaEventoMulta=").append(fechaEventoMulta);
        sb.append(", espacioComun=").append(espacioComun);
        sb.append(", propiedad=").append(propiedad);
        sb.append(", propietario=").append(propietario);
        sb.append(", personaOriginariaEvento=").append(personaOriginariaEvento);
        sb.append(", descripcionEvento=").append(descripcionEvento);
        sb.append(", evento=").append(evento);
        sb.append(", valorMulta=").append(valorMulta);
        sb.append(", fechaMaximaPago=").append(fechaMaximaPago);
        sb.append(", observacion=").append(observacion);
        sb.append(", mensaje=").append(mensaje);
        sb.append(", evidencia=").append(evidencia);
        sb.append('}');
        return sb.toString();
    }
    
    
}
