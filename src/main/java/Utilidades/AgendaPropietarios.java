/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Beans.Propiedad;
import Beans.Propietario;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan David
 */
public class AgendaPropietarios {
    List<Propietario> listaPropietarios = new ArrayList<>();
    List<Propietario> listaRecuperadaPropietarios = new ArrayList<>();
    
    public void adicionarPropietarios(Propietario propietario){
        
        listaPropietarios.addAll(listaRecuperadaPropietarios);
        listaPropietarios.add(propietario); 
        try {
            guardarFichero();
        } catch (IOException ex) {
            Logger.getLogger(AgendaPropietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int obtenerIndice(int id){
        for(Propietario propietario : listaRecuperadaPropietarios) {
            if(propietario.getId() == id){
                return listaRecuperadaPropietarios.indexOf(propietario);
            }
        }
        return -1;
    }
    
    public Propietario obtenerPropietario(int id){
        for(Propietario propietario : listaRecuperadaPropietarios) {
            if(propietario.getId() == id){
                return propietario;
            }
        }
        return null;
    }
    
    public void actualizarPropietario(Propietario propietario){
        for (Propietario objPropietario : listaRecuperadaPropietarios){
            if(propietario.getId() == objPropietario.getId()){
                objPropietario.setDireccion(propietario.getDireccion());
                objPropietario.setTelefono(propietario.getTelefono());
                objPropietario.setEmail(propietario.getEmail());
                objPropietario.setProfesion(propietario.getProfesion());
                objPropietario.setOficio(propietario.getOficio());
                objPropietario.setNombre(propietario.getNombre());
                objPropietario.setEdad(propietario.getEdad());
            }
            try {
                actualizarFichero(listaRecuperadaPropietarios);
            } catch (IOException ex) {
                Logger.getLogger(AgendaPropietarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void eliminarPropietario(int indice){
        listaRecuperadaPropietarios.remove(indice);
        try {
            actualizarFichero(listaRecuperadaPropietarios);
        } catch (IOException ex) {
            Logger.getLogger(AgendaPropietarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int numPropietarios(){
        int numPropietarios = listaRecuperadaPropietarios.size();
        return numPropietarios;
    }
    
    public  void guardarFichero() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("infopropietarios.csv"))) {
            for (Propietario propietario : listaPropietarios) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(String.valueOf(propietario.getId()))
                      .add(propietario.getDireccion())
                      .add(propietario.getTelefono())
                      .add(propietario.getEmail())
                      .add(propietario.getProfesion())
                      .add(propietario.getOficio())
                      .add(propietario.getNombre())
                      .add(String.valueOf(propietario.getEdad()));
                bw.write(joiner.toString());
                bw.newLine();
            }
        }
    }
    
    public void leerFichero() throws IOException, ClassNotFoundException {
        listaRecuperadaPropietarios.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("infopropietarios.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Propietario propietario = new Propietario(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4], values[5], values[6], Integer.parseInt(values[7]));
                listaRecuperadaPropietarios.add(propietario);
            }
        }
    }
    
    public void actualizarFichero(List<Propietario> lista)throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("infopropietarios.csv"))) {
            for (Propietario propietario : lista) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(String.valueOf(propietario.getId()))
                      .add(propietario.getDireccion())
                      .add(propietario.getTelefono())
                      .add(propietario.getEmail())
                      .add(propietario.getProfesion())
                      .add(propietario.getOficio())
                      .add(propietario.getNombre())
                      .add(String.valueOf(propietario.getEdad()));
                bw.write(joiner.toString());
                bw.newLine();
            }
        }
    }
    
    public void crearPDF() {
        try {
            Document doc = new Document();  

            Font tipo1 = FontFactory.getFont(BaseFont.TIMES_ROMAN, 12, BaseColor.BLACK);
            Font tipo2 = FontFactory.getFont(BaseFont.TIMES_BOLD, 20, BaseColor.GREEN);

            PdfWriter.getInstance(doc, new FileOutputStream("Lista_de_Propietarios.pdf")); 
            doc.open();
            Paragraph titulo = new Paragraph("Lista de Propietarios", tipo2);
            doc.add(titulo);

            for (Propietario propietario : listaRecuperadaPropietarios){
                // Campos heredados de la clase Persona
                Paragraph txNombre = new Paragraph("Nombre: " + propietario.getNombre(), tipo1);
                Paragraph txEdad = new Paragraph("Edad: " + propietario.getEdad(), tipo1);

                // Campos específicos de Propietario
                Paragraph txId = new Paragraph("ID: " + propietario.getId(), tipo1);
                Paragraph txDireccion = new Paragraph("Dirección: " + propietario.getDireccion(), tipo1);
                Paragraph txTelefono = new Paragraph("Teléfono: " + propietario.getTelefono(), tipo1);
                Paragraph txEmail = new Paragraph("Email: " + propietario.getEmail(), tipo1);
                Paragraph txProfesion = new Paragraph("Profesión: " + propietario.getProfesion(), tipo1);
                Paragraph txOficio = new Paragraph("Oficio: " + propietario.getOficio(), tipo1);
                Paragraph txLinea = new Paragraph("------------------------------------------------------", tipo1);

                // Agregando todos los campos al documento
                doc.add(txNombre);
                doc.add(txEdad);
                doc.add(txId);
                doc.add(txDireccion);
                doc.add(txTelefono);
                doc.add(txEmail);
                doc.add(txProfesion);
                doc.add(txOficio);
                doc.add(txLinea);
            }

            doc.addAuthor("Juan David González");
            doc.close(); 
        } catch(DocumentException | java.io.FileNotFoundException e){
            System.out.println("Error del archivo");
            e.printStackTrace();
        }
    }
    public String listarObjetos(){
        String lista = "";
        if(!listaRecuperadaPropietarios.isEmpty()){
            for(Propietario propietario : listaRecuperadaPropietarios) {
                // Incluye datos heredados de Persona
                lista += propietario.getNombre() + "\t" + propietario.getEdad()+ "\t" + propietario.getId() + "\t" + propietario.getDireccion() + "\t" + 
                    propietario.getTelefono() + "\t" + propietario.getEmail() + "\t" + 
                    propietario.getProfesion() + "\t" + propietario.getOficio() + "\n";
            }
        }
        return lista;
    }
}
