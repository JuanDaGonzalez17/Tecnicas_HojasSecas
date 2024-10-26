/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;
import Beans.*;

import Beans.EspacioComun;
import Beans.Propiedad;
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
public class AgendaPropiedades {
    List<Propiedad> listaPropiedades = new ArrayList<>();
    List<Propiedad> listaRecuperadaPropiedades = new ArrayList<>();
    
    public void adicionarPropiedades(Propiedad propiedad){
        boolean bandera = false;
        for(Propiedad objPropiedad : listaRecuperadaPropiedades){
            if(objPropiedad.getId() == propiedad.getId()){
                bandera = true;
            }else{
                bandera = false;
            }
        }
        if(bandera == false){
            listaPropiedades.add(propiedad); 
        }
        try {
            guardarFichero();
        } catch (IOException ex) {
            Logger.getLogger(AgendaPropiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int obtenerIndice(int id){
        for(Propiedad propiedad : listaRecuperadaPropiedades) {
            if(propiedad.getId() == id){
                return listaRecuperadaPropiedades.indexOf(propiedad);
            }
        }
        return -1;
    }
    
    public Propiedad obtenerPropiedad(int id){
        for(Propiedad propiedad : listaRecuperadaPropiedades) {
            if(propiedad.getId() == id){
                return propiedad;
            }
        }
        return null;
    }
    
    public void actualizarPropiedad(Propiedad propiedad){ // Cambiar aqui tambien
        for (Propiedad objPropiedad : listaRecuperadaPropiedades){
            if(propiedad.getId() == objPropiedad.getId()){
                objPropiedad.setSaldoActual(propiedad.getSaldoActual());
                objPropiedad.setMetrosCuadrados(propiedad.getMetrosCuadrados());
                objPropiedad.setPropietario(propiedad.getPropietario());
            }
            try {
                actualizarFichero(listaRecuperadaPropiedades);
            } catch (IOException ex) {
                Logger.getLogger(AgendaPropiedades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void eliminarPropiedad(int indice){
        listaRecuperadaPropiedades.remove(indice);
        try {
            actualizarFichero(listaRecuperadaPropiedades);
        } catch (IOException ex) {
            Logger.getLogger(AgendaPropiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int numPropiedades(){
        int numPropiedades = listaRecuperadaPropiedades.size();
        return numPropiedades;
    }
    
    public  void guardarFichero() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("infopropiedades.csv"))) {
            for (Propiedad propiedad : listaPropiedades) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(String.valueOf(propiedad.getId()))
                      .add(String.valueOf(propiedad.getSaldoActual()))
                      .add(propiedad.getPropietario().toCsvString())
                      .add(String.valueOf(propiedad.getMetrosCuadrados()));
                bw.write(joiner.toString());
                bw.newLine();
            }
        }
    }
    
    public void leerFichero() throws IOException, ClassNotFoundException {
        listaRecuperadaPropiedades.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("infopropiedades.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Propiedad propiedad = new Propiedad(Integer.parseInt(values[0]), Double.parseDouble(values[1]), Propietario.fromCsvString(values[2]), Double.parseDouble(values[3]));
                listaRecuperadaPropiedades.add(propiedad);
            }
        }
    }
    
    public void actualizarFichero(List<Propiedad> lista)throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("infopropiedades.csv"))) {
            for (Propiedad propiedad : listaPropiedades) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(String.valueOf(propiedad.getId()))
                      .add(String.valueOf(propiedad.getSaldoActual()))
                      .add(propiedad.getPropietario().toCsvString())
                      .add(String.valueOf(propiedad.getMetrosCuadrados()));
                bw.write(joiner.toString());
                bw.newLine();
            }
        }
    }
    
    public String profesionVecinos(int id){
        int idCasa = 0;
        String profesionVecinos = "";
        for(Propiedad propiedad : listaPropiedades){
            if(propiedad.getPropietario().getId() == id){
                idCasa = propiedad.getId();
            }
        }
        if(idCasa == 1){
            for(Propiedad propiedad : listaPropiedades){
                if(propiedad.getId() == 2){
                    profesionVecinos = "Su vecino de la casa: " + propiedad.getId() + ", tiene como profesión " + propiedad.getPropietario().getProfesion();
                    break;
                }
            }
        }
        else if(idCasa == listaPropiedades.size()){
            for(Propiedad propiedad : listaPropiedades){
                if(propiedad.getId() == listaPropiedades.size() - 1){
                    profesionVecinos = "Su vecino de la casa: " + propiedad.getId() + ", tiene como profesión " + propiedad.getPropietario().getProfesion();
                    break;
                }
            }
        }
        else{
            for(Propiedad propiedad : listaPropiedades){
                if(propiedad.getId() == idCasa -1 || propiedad.getId() == idCasa + 1)
                    profesionVecinos += "Su vecino de la casa: " + propiedad.getId() + ", tiene como profesión " + propiedad.getPropietario().getProfesion() + "\n";
            }
        }
        
        
        return profesionVecinos;
    }
    
    public void crearPDF() {
        try {
            Document doc = new Document();  

            Font tipo1 = FontFactory.getFont(BaseFont.TIMES_ROMAN, 12, BaseColor.BLACK);
            Font tipo2 = FontFactory.getFont(BaseFont.TIMES_BOLD, 20, BaseColor.GREEN);

            PdfWriter.getInstance(doc, new FileOutputStream("Lista_de_Propiedades.pdf")); 
            doc.open();
            Paragraph titulo = new Paragraph("Lista de Propiedades", tipo2);
            doc.add(titulo);

            for (Propiedad propiedad : listaRecuperadaPropiedades){
                Paragraph txId = new Paragraph("ID: " + propiedad.getId(), tipo1);
                Paragraph txSaldoActual = new Paragraph("Saldo Actual: $" + propiedad.getSaldoActual(), tipo1);

                // Para el propietario, obtenemos sus datos
                Propietario propietario = propiedad.getPropietario();
                Paragraph txPropietario = new Paragraph("Datos del Propietario:", tipo1);
                Paragraph txNombreProp = new Paragraph("Nombre: " + propietario.getNombre(), tipo1);
                Paragraph txIdProp = new Paragraph("ID: " + propietario.getId(), tipo1);

                Paragraph txMetrosCuadrados = new Paragraph("Metros Cuadrados: " + propiedad.getMetrosCuadrados() + " m²", tipo1);
                Paragraph txLinea = new Paragraph("------------------------------------------------------", tipo1);

                // Agregando todos los campos al documento
                doc.add(txId);
                doc.add(txSaldoActual);
                doc.add(txPropietario);
                doc.add(txNombreProp);
                doc.add(txIdProp);
                doc.add(txMetrosCuadrados);
                doc.add(txLinea);
            }

            doc.addAuthor("Juan Andres Posada, Juan David González, Alejandro Munera, Miguel Angel Avila");
            doc.close(); 
        } catch(DocumentException | java.io.FileNotFoundException e){
            System.out.println("Error del archivo");
            e.printStackTrace();
        }
    }
    public String listarObjetos(){
        String lista = "";
        if(!listaRecuperadaPropiedades.isEmpty()){
            for(Propiedad propiedad : listaRecuperadaPropiedades) {
                lista += propiedad.getId() + "\t" + propiedad.getSaldoActual() + "\t" + 
                    propiedad.getPropietario().getNombre()+ "\t" + propiedad.getMetrosCuadrados() + "\n";
            }
        }
        return lista;
    }
}
