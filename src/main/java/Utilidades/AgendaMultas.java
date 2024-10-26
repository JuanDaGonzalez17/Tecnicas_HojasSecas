/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Beans.Multa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan David
 */
public class AgendaMultas {
    List<Multa> listaMultas = new ArrayList<>();
    List<Multa> listaRecuperadaMultas = new ArrayList<>();
    
    public void adicionarMulta(Multa multa){
        listaMultas.add(multa); 
            guardarFichero();
    }
    
    public int obtenerIndice(int id){
        for(Multa multa : listaRecuperadaMultas) {
            if(multa.getID()== id){
                return listaRecuperadaMultas.indexOf(multa);
            }
        }
        return -1;
    }
    
    public void actualizarMulta(Multa multa){
        for (Multa objMulta : listaRecuperadaMultas){
            if(multa.getID() == objMulta.getID()){
                objMulta.setFechaMulta(multa.getFechaMulta());
                objMulta.setFechaEventoMulta(multa.getFechaEventoMulta());
                objMulta.setEspacioComun(multa.getEspacioComun());
                objMulta.setPropiedad(multa.getPropiedad());
                objMulta.setPropietario(multa.getPropietario());
                objMulta.setPersonaOriginariaEvento(multa.getPersonaOriginariaEvento());
                objMulta.setDescripcionEvento(multa.getDescripcionEvento());
                objMulta.setEvento(multa.getEvento());
                objMulta.setValorMulta(multa.getValorMulta());
                objMulta.setFechaMaximaPago(multa.getFechaMaximaPago());
                objMulta.setObservacion(multa.getObservacion());
                objMulta.setMensaje(multa.getMensaje());
                objMulta.setEvidencia(multa.getEvidencia());
            }
            actualizarFichero(listaRecuperadaMultas);
        }
    }
    
    public void eliminarMulta(int indice){
        listaRecuperadaMultas.remove(indice);
        actualizarFichero(listaRecuperadaMultas);
    }
    
    public int numMultas(){
        int numMultas = listaRecuperadaMultas.size();
        return numMultas;
    }
    
    public Multa obtenerMulta(int id){
        for(Multa multa : listaRecuperadaMultas) {
            if(multa.getID() == id){
                return multa;
            }
        }
        return null;
    }
    
    public void guardarFichero() {
        Gson gson = new Gson();
        String ruta = "C:\\Usuario\\Documents\\NetBeansProjects\\ArchivoJSON\\src\\Datos\\datosJson1.json";

        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(ruta))) {
            String jsonString = gson.toJson(listaMultas);
            bw2.write(jsonString);
        } catch (IOException ex) {
            Logger.getLogger(AgendaMultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void leerFichero() {
        Gson gson = new Gson();
        String ruta = "C:\\Usuario\\Documents\\NetBeansProjects\\ArchivoJSON\\src\\Datos\\datosJson1.json";

        try (Reader reader = new FileReader(ruta)) {
            // Creamos un tipo que representa un array de Multa
            Type tipoListaMultas = new TypeToken<List<Multa>>(){}.getType();

            // Convertimos el JSON a lista de multas
            listaRecuperadaMultas = gson.fromJson(reader, tipoListaMultas);
        } catch (IOException ex) {
            Logger.getLogger(AgendaMultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarFichero(List<Multa> listado){
        Gson gson = new Gson();
        String ruta = "C:\\Usuario\\Documents\\NetBeansProjects\\ArchivoJSON\\src\\Datos\\datosJson1.json";

        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(ruta))) {
            String jsonString = gson.toJson(listado);
            bw2.write(jsonString);
        } catch (IOException ex) {
            Logger.getLogger(AgendaMultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearPDF() {
        try {
            Document doc = new Document();  //Creamos un objeto tipo documento

            Font tipo1 = FontFactory.getFont(BaseFont.TIMES_ROMAN, 12, BaseColor.BLACK);
            Font tipo2 = FontFactory.getFont(BaseFont.TIMES_BOLD, 20, BaseColor.GREEN);

            PdfWriter.getInstance(doc, new FileOutputStream("Lista_de_Multas.pdf"));  //lo asociamos como archivo y colocarle nombre
            doc.open();
            Paragraph titulo = new Paragraph("Lista de Multas", tipo2);
            doc.add(titulo);

            for (Multa multa : listaRecuperadaMultas){
                Paragraph txID = new Paragraph("ID: " + multa.getID(), tipo1);
                Paragraph txFechaMulta = new Paragraph("Fecha de Multa: " + multa.getFechaMulta(), tipo1);
                Paragraph txFechaEvento = new Paragraph("Fecha del Evento: " + multa.getFechaEventoMulta(), tipo1);
                Paragraph txEspacio = new Paragraph("Espacio Común: " + multa.getEspacioComun(), tipo1);
                Paragraph txPropiedad = new Paragraph("Propiedad: " + multa.getPropiedad(), tipo1);
                Paragraph txPropietario = new Paragraph("Propietario: " + multa.getPropietario(), tipo1);
                Paragraph txPersonaEvento = new Paragraph("Persona Origen del Evento: " + multa.getPersonaOriginariaEvento(), tipo1);
                Paragraph txDescripcion = new Paragraph("Descripción del Evento: " + multa.getDescripcionEvento(), tipo1);
                Paragraph txEvento = new Paragraph("Evento: " + multa.getEvento(), tipo1);
                Paragraph txValor = new Paragraph("Valor de Multa: " + multa.getValorMulta(), tipo1);
                Paragraph txFechaPago = new Paragraph("Fecha Máxima de Pago: " + multa.getFechaMaximaPago(), tipo1);
                Paragraph txObservacion = new Paragraph("Observación: " + multa.getObservacion(), tipo1);
                Paragraph txEvidencia = new Paragraph("Evidencia: " + multa.getEvidencia(), tipo1);
                Paragraph txLinea = new Paragraph("------------------------------------------------------", tipo1);

                doc.add(txID);
                doc.add(txFechaMulta);
                doc.add(txFechaEvento);
                doc.add(txEspacio);
                doc.add(txPropiedad);
                doc.add(txPropietario);
                doc.add(txPersonaEvento);
                doc.add(txDescripcion);
                doc.add(txEvento);
                doc.add(txValor);
                doc.add(txFechaPago);
                doc.add(txObservacion);
                doc.add(txEvidencia);
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
        if(!listaRecuperadaMultas.isEmpty()){
            for(Multa multa : listaRecuperadaMultas) {
                lista += multa.getID() + "\t" + multa.getFechaMulta() + "\t" + multa.getFechaEventoMulta() + 
                    "\t" + multa.getEspacioComun().getDescripcion() + "\t" + multa.getPropiedad().getId() + "\t" + multa.getPropietario().getNombre() + 
                    "\t" + multa.getPersonaOriginariaEvento()+ "\t" + multa.getDescripcionEvento() + "\t" + 
                    multa.getEvento() + "\t" + multa.getValorMulta() + "\t" + multa.getFechaMaximaPago() + 
                    "\t" + multa.getObservacion() + "\t" + multa.getEvidencia() + "\n";
            }
        }
        return lista;
    }
    
    public String listarMultas(ArrayList<Multa> listaM){
        String lista = "";
        if(!listaM.isEmpty()){
            for(Multa multa : listaM) {
                lista += multa.getID() + "\t" + multa.getFechaMulta() + "\t" + multa.getFechaEventoMulta() + 
                    "\t" + multa.getEspacioComun().getDescripcion() + "\t" + multa.getPropiedad().getId() + "\t" + multa.getPropietario().getNombre() + 
                    "\t" + multa.getPersonaOriginariaEvento()+ "\t" + multa.getDescripcionEvento() + "\t" + 
                    multa.getEvento() + "\t" + multa.getValorMulta() + "\t" + multa.getFechaMaximaPago() + 
                    "\t" + multa.getObservacion() + "\t" + multa.getEvidencia() + "\n";
            }
        }
        return lista;
    }
}
