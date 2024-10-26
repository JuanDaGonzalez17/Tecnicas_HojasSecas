/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans;

/**
 *
 * @author Juan David
 */
public class Propietario extends Persona{
    private int id;
    private String direccion;
    private String telefono;
    private String email;
    private String profesion;
    private String oficio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }
    
    public String toCsvString(){
        return id + "," + direccion + "," + telefono + "," + email + "," + profesion + "," + oficio + "," + getNombre() + "," + getEdad();
    }

    public static Propietario fromCsvString(String str){
        String[] partes = str.split(",");
        return new Propietario(Integer.parseInt(partes[0]), partes[1], partes[2], partes[3], partes[4], partes[5], partes[6], Integer.parseInt(partes[7]));
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Propietario{");
        sb.append("id=").append(id);
        sb.append(", direccion=").append(direccion);
        sb.append(", telefono=").append(telefono);
        sb.append(", email=").append(email);
        sb.append(", profesion=").append(profesion);
        sb.append(", oficio=").append(oficio);
        sb.append('}');
        return sb.toString();
    }

    public Propietario() {
    }

    public Propietario(int id, String direccion, String telefono, String email, String profesion, String oficio) {
        this.id = id;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.profesion = profesion;
        this.oficio = oficio;
    }

    public Propietario(int id, String direccion, String telefono, String email, String profesion, String oficio, String nombre, int edad) {
        super(nombre, edad);
        this.id = id;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.profesion = profesion;
        this.oficio = oficio;
    }

    
    
    
}
