/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Beans.EspacioComun;
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
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan David
 */
public class AgendaEspaciosComunes {
    
    List<EspacioComun> listaEspaciosComunes = new ArrayList<>();
    List<EspacioComun> listaRecuperadaEspacios = new ArrayList<>();
    
    public void adicionarEspacioComun(EspacioComun espacioComun){
        listaEspaciosComunes.add(espacioComun); 
        try {
            guardarFichero();
        } catch (IOException ex) {
            Logger.getLogger(AgendaEspaciosComunes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int obtenerIndice(String descripcion){
        for(EspacioComun espacioComun : listaRecuperadaEspacios) {
            if(espacioComun.getDescripcion().equalsIgnoreCase(descripcion)){
                return listaRecuperadaEspacios.indexOf(espacioComun);
            }
        }
        return -1;
    }
    
    public EspacioComun obtenerEspacioComun(String desc){
        for(EspacioComun espacioComun : listaRecuperadaEspacios) {
            if(espacioComun.getDescripcion().equals(desc)){
                return espacioComun;
            }
        }
        return null;
    }
    
    //metodo listar objetos(se supone que es con sql)
    
    public void actualizarEspacioComun(EspacioComun espacioComun){ // Cambiar aqui tambien
        for (EspacioComun objEspacioComun : listaRecuperadaEspacios){
            if(espacioComun.getDescripcion().equalsIgnoreCase(objEspacioComun.getDescripcion())){
                objEspacioComun.setDescripcion(espacioComun.getDescripcion());
                objEspacioComun.setHoraInicio(espacioComun.getHoraInicio());
                objEspacioComun.setHoraCierre(espacioComun.getHoraCierre());
                objEspacioComun.setCapacidadMaxima(espacioComun.getCapacidadMaxima());
                objEspacioComun.setDiasServicio(espacioComun.getDiasServicio());
            }
            try {
                actualizarFichero(listaRecuperadaEspacios);
            } catch (IOException ex) {
                Logger.getLogger(AgendaEspaciosComunes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void eliminarEspacioComun(int indice){
        listaRecuperadaEspacios.remove(indice);
        try {
            actualizarFichero(listaRecuperadaEspacios);
        } catch (IOException ex) {
            Logger.getLogger(AgendaEspaciosComunes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int numEspaciosComunes(){
        int numEspaciosComunes = listaRecuperadaEspacios.size();
        return numEspaciosComunes;
    }
    
    public  void guardarFichero() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("infoespacioscomunes.csv"))) {
            for (EspacioComun espacioComun : listaEspaciosComunes) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(espacioComun.getDescripcion())
                      .add(espacioComun.getHoraInicio())
                      .add(espacioComun.getHoraCierre())
                      .add(String.valueOf(espacioComun.getCapacidadMaxima()))
                      .add(String.join("|", espacioComun.getDiasServicio()));
                bw.write(joiner.toString());
                bw.newLine();
            }
        }
    }
    
    public void leerFichero() throws IOException, ClassNotFoundException {
        listaRecuperadaEspacios.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("infoespacioscomunes.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                EspacioComun espacioComun = new EspacioComun(values[0], values[1], values[2], Integer.parseInt(values[3]), new ArrayList<String>(Arrays.asList(values[4].split("\\|"))));
                listaRecuperadaEspacios.add(espacioComun);
            }
        }
    }
    
    public void actualizarFichero(List<EspacioComun> lista)throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("infoespacioscomunes.csv"))) {
            for (EspacioComun espacioComun : lista) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(espacioComun.getDescripcion())
                      .add(espacioComun.getHoraInicio())
                      .add(espacioComun.getHoraCierre())
                      .add(String.valueOf(espacioComun.getCapacidadMaxima()))
                      .add(String.join("|", espacioComun.getDiasServicio()));
                bw.write(joiner.toString());
                bw.newLine();
            }
        }
    }
    
    public String horarioEspacios(){
        String horario = "";
        for(EspacioComun espacioComun : listaEspaciosComunes){
            horario += "El Espacio Comun: "+ espacioComun.getDescripcion() + "tiene como horario: " + espacioComun.getHoraInicio() + " - " + espacioComun.getHoraCierre() + "\n";
        }
        return horario;
    }
    
    public void crearPDF() {
        try {
            Document doc = new Document();  

            Font tipo1 = FontFactory.getFont(BaseFont.TIMES_ROMAN, 12, BaseColor.BLACK);
            Font tipo2 = FontFactory.getFont(BaseFont.TIMES_BOLD, 20, BaseColor.GREEN);

            PdfWriter.getInstance(doc, new FileOutputStream("Lista_de_Espacios_Comunes.pdf")); 
            doc.open();
            Paragraph titulo = new Paragraph("Lista de Espacios Comunes", tipo2);
            doc.add(titulo);

            for (EspacioComun espacio : listaRecuperadaEspacios){
                Paragraph txDescripcion = new Paragraph("Descripción: " + espacio.getDescripcion(), tipo1);
                Paragraph txHoraInicio = new Paragraph("Hora de Inicio: " + espacio.getHoraInicio(), tipo1);
                Paragraph txHoraCierre = new Paragraph("Hora de Cierre: " + espacio.getHoraCierre(), tipo1);
                Paragraph txCapacidad = new Paragraph("Capacidad Máxima: " + espacio.getCapacidadMaxima() + " personas", tipo1);

                // Para la lista de días de servicio
                Paragraph txDiasServicio = new Paragraph("Días de Servicio:", tipo1);
                for (String dia : espacio.getDiasServicio()) {
                    Paragraph txDia = new Paragraph("    • " + dia, tipo1);
                    doc.add(txDia);
                }

                Paragraph txLinea = new Paragraph("------------------------------------------------------", tipo1);

                // Agregando todos los campos al documento
                doc.add(txDescripcion);
                doc.add(txHoraInicio);
                doc.add(txHoraCierre);
                doc.add(txCapacidad);
                doc.add(txDiasServicio);
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
        if(!listaRecuperadaEspacios.isEmpty()){
            for(EspacioComun espacio : listaRecuperadaEspacios) {
                lista += espacio.getDescripcion() + "\t" + espacio.getHoraInicio() + "\t" + 
                    espacio.getHoraCierre() + "\t" + espacio.getCapacidadMaxima() + "\t" + 
                    String.join(",", espacio.getDiasServicio()) + "\n";
            }
        }
        return lista;
    }
}
