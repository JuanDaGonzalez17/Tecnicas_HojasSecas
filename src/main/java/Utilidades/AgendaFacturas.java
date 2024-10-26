/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Beans.Factura;
import Beans.Multa;
import Beans.Propietario;
import Beans.Propiedad;
//import java.awt.Font;
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
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.util.Arrays;

/**
 *
 * @author Juan David
 */
public class AgendaFacturas {
    List<Factura> listaFacturas = new ArrayList<>();
    List<Factura> listaRecuperadaFacturas = new ArrayList<>();
    
    public void adicionarFactura(Factura factura){
        listaFacturas.add(factura); 
        try {
            guardarFichero();
        } catch (IOException ex) {
            Logger.getLogger(AgendaFacturas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int obtenerIndice(String nFactura){
        for(Factura factura : listaRecuperadaFacturas) {
            if(factura.getnFactura().equals(nFactura)){
                return listaRecuperadaFacturas.indexOf(factura);
            }
        }
        return -1;
    }
    
    public Factura obtenerFactura(String nfactura){
        for(Factura factura : listaRecuperadaFacturas) {
            if(factura.getnFactura().equals(nfactura)){
                return factura;
            }
        }
        return null;
    }
    
    public void actualizarFactura(Factura factura){
        for (Factura objfactura : listaRecuperadaFacturas){
            if(factura.getnFactura().equalsIgnoreCase(objfactura.getnFactura())){
                objfactura.setnFactura(factura.getnFactura());
                objfactura.setPropietario(factura.getPropietario());
                objfactura.setPropiedad(factura.getPropiedad());
                objfactura.setFecha(factura.getFecha());
                objfactura.setValor(factura.getValor());
                objfactura.setMesAFacturar(factura.getMesAFacturar());
                objfactura.setValorMetroCuadrado(factura.getValorMetroCuadrado());
                objfactura.setValorPagar(factura.getValorPagar());
                objfactura.setMultas(factura.getMultas());
                objfactura.setDescuentos(factura.getDescuentos());
                objfactura.setFechaMaximaPago(factura.getFechaMaximaPago());
                objfactura.setInformacionPago(factura.getInformacionPago());
                objfactura.setValorPagado(factura.getValorPagado());
            }
            try {
                actualizarFichero(listaRecuperadaFacturas);
            } catch (IOException ex) {
                Logger.getLogger(AgendaFacturas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void eliminarFactura(int indice){
        listaRecuperadaFacturas.remove(indice);
        try {
            actualizarFichero(listaRecuperadaFacturas);
        } catch (IOException ex) {
            Logger.getLogger(AgendaFacturas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int numFacturas(){
        int numFacturas = listaRecuperadaFacturas.size();
        return numFacturas;
    }
    
    public  void guardarFichero() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("infofacturas.csv"))) {
            for (Factura factura : listaFacturas) {
                ArrayList<String> multacsv = null;
                for(Multa multa : factura.getMultas()){
                    multacsv.add(multa.toCsvString());
                }
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(factura.getnFactura())
                      .add(factura.getPropietario().toCsvString())
                      .add(factura.getPropiedad().toCsvString())
                      .add(factura.getFecha())
                      .add(String.valueOf(factura.getValor()))
                      .add(factura.getMesAFacturar())
                      .add(String.valueOf(factura.getValorMetroCuadrado()))
                      .add(String.valueOf(factura.getValorPagar()))
                      .add(String.join("|", multacsv))
                      .add(String.valueOf(factura.getDescuentos()))
                      .add(factura.getFechaMaximaPago())
                      .add(factura.getInformacionPago())
                      .add(String.valueOf(factura.getValorPagado()));
                bw.write(joiner.toString());
                bw.newLine();
            }
        }
    }
    
    public void leerFichero() throws IOException, ClassNotFoundException {
        listaRecuperadaFacturas.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("infofacturas.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ArrayList<String> multaString = new ArrayList<String>(Arrays.asList(values[8].split("\\|")));
                ArrayList<Multa> multas = null;
                for(String control : multaString){
                    multas.add(Multa.fromCsvString(control));
                }
                Factura factura = new Factura(values[0], Propietario.fromCsvString(values[1]), Propiedad.fromCsvString(values[2]), values[3], Double.parseDouble(values[4]), values[5], Double.parseDouble(values[6]), Double.parseDouble(values[7]), multas,
                Double.parseDouble(values[9]), values[10], values[11], Double.parseDouble(values[12]));
                listaRecuperadaFacturas.add(factura);
            }
        }
    }
    
    public void actualizarFichero(List<Factura> lista)throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("infopropietarios.csv"))) {
            for (Factura factura : lista) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(factura.getnFactura())
                      .add(factura.getPropietario().toCsvString())
                      .add(factura.getPropiedad().toCsvString())
                      .add(factura.getFecha())
                      .add(String.valueOf(factura.getValor()))
                      .add(factura.getMesAFacturar())
                      .add(String.valueOf(factura.getValorMetroCuadrado()))
                      .add(String.valueOf(factura.getValorPagar()))
                      .add(String.valueOf(factura.getMultas()))
                      .add(String.valueOf(factura.getDescuentos()))
                      .add(factura.getFechaMaximaPago())
                      .add(factura.getInformacionPago())
                      .add(String.valueOf(factura.getValorPagado()));
                bw.write(joiner.toString());
                bw.newLine();
            }
        }
    }
    
    public Factura obtenerFacturaPropMes(int id, String mes){
        for(Factura factura : listaRecuperadaFacturas){
            if(factura.getPropiedad().getId() == id && factura.getMesAFacturar().equals(mes)){
                return factura;
            }
        }
        return null;
    }
    
    public String administracionSaldo(String idCasa){
        String valor = "";
        for(Factura factura : listaRecuperadaFacturas){
            if(factura.getnFactura().equalsIgnoreCase(idCasa)){
                valor += "El saldo a la fecha es: " + factura.getPropiedad().getSaldoActual() + "\n";
                valor += "El valor de administración es: " + factura.getValor();
                break;
            }
        }
        return valor;
    }
    
    public double calcularMultas(Factura factura){
        double valorMultas = 0;
        for(Multa multa : factura.getMultas()){
            valorMultas += multa.getValorMulta();
        }
        return valorMultas;
    }
    
    public double calcularValorTotal(Factura factura){
        return factura.getValor() + factura.getValorMetroCuadrado()*factura.getPropiedad().getMetrosCuadrados() + calcularMultas(factura) - factura.getDescuentos();
    }
    
    public void crearListadoPDF() {
        try {
            Document doc = new Document();
            
            Font tipo1 = FontFactory.getFont(BaseFont.TIMES_ROMAN, 12, BaseColor.BLACK);
            Font tipo2 = FontFactory.getFont(BaseFont.TIMES_BOLD, 20, BaseColor.ORANGE);
            
            PdfWriter.getInstance(doc, new FileOutputStream("Lista_de_Factura.pdf")); 
            doc.open();
            Paragraph titulo = new Paragraph("Lista de Facturas", tipo2);
            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(titulo);
            for (Factura factura : listaRecuperadaFacturas){
                Paragraph txFac = new Paragraph("N° Factura: " + factura.getnFactura(), tipo1);
                Paragraph txFec = new Paragraph("Fecha: " + factura.getFecha(), tipo1);
                Paragraph txFecM = new Paragraph("Fecha Maxima de Pago: "+ factura.getFechaMaximaPago(), tipo1);
                Paragraph txMes = new Paragraph("Mes a Facturar: "+ factura.getMesAFacturar(), tipo1);
                Paragraph txPro = new Paragraph("Propietario: "+ factura.getPropietario().getNombre(), tipo1);
                Paragraph txCom = new Paragraph("Comentario Adicional: "+ factura.getInformacionPago(), tipo1);
                Paragraph txMul = new Paragraph("Valor multas: "+ factura.getMultas(), tipo1);
                Paragraph txDesc = new Paragraph("Valor descontado: " + factura.getDescuentos(), tipo1);
                Paragraph txTot = new Paragraph("Total: " + factura.getValorPagar(), tipo1);
                Paragraph txLinea = new Paragraph("------------------------------------------------------", tipo1);
                
                txFac.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                doc.add(txFac);
                txFec.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                doc.add(txFec);
                txFecM.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                doc.add(txFecM);
                doc.add(txMes);
                doc.add(txPro);
                doc.add(txCom);
                doc.add(txMul);
                doc.add(txDesc);
                doc.add(txTot);
                doc.add(txLinea);
            }
            doc.addAuthor("Juan David González");
            doc.close(); 
        }catch(DocumentException | java.io.FileNotFoundException e){
            System.out.println("Error del archivo");
            e.printStackTrace();
        }
    }
    
    public void crearFacturaPDF(int id){
        try {
            Document doc = new Document();
            
            Font tipo1 = FontFactory.getFont(BaseFont.TIMES_ROMAN, 12, BaseColor.BLACK);
            Font tipo2 = FontFactory.getFont(BaseFont.TIMES_BOLD, 20, BaseColor.ORANGE);
            
            PdfWriter.getInstance(doc, new FileOutputStream("Lista_de_Factura.pdf")); 
            doc.open();
            Paragraph titulo = new Paragraph("Lista de Facturas", tipo2);
            titulo.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(titulo);
            for (Factura factura : listaRecuperadaFacturas){
                if(factura.getPropiedad().getId() == id){
                    Paragraph txFac = new Paragraph("N° Factura: " + factura.getnFactura(), tipo1);
                    Paragraph txFec = new Paragraph("Fecha: " + factura.getFecha(), tipo1);
                    Paragraph txFecM = new Paragraph("Fecha Maxima de Pago: "+ factura.getFechaMaximaPago(), tipo1);
                    Paragraph txMes = new Paragraph("Mes a Facturar: "+ factura.getMesAFacturar(), tipo1);
                    Paragraph txPro = new Paragraph("Propietario: "+ factura.getPropietario().getNombre(), tipo1);
                    Paragraph txCom = new Paragraph("Comentario Adicional: "+ factura.getInformacionPago(), tipo1);
                    Paragraph txMul = new Paragraph("Valor multas: "+ factura.getMultas(), tipo1);
                    Paragraph txDesc = new Paragraph("Valor descontado: " + factura.getDescuentos(), tipo1);
                    Paragraph txTot = new Paragraph("Total: " + factura.getValorPagar(), tipo1);
                    Paragraph txLinea = new Paragraph("------------------------------------------------------", tipo1);

                    txFac.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                    doc.add(txFac);
                    txFec.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                    doc.add(txFec);
                    txFecM.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                    doc.add(txFecM);
                    doc.add(txMes);
                    doc.add(txPro);
                    doc.add(txCom);
                    doc.add(txMul);
                    doc.add(txDesc);
                    doc.add(txTot);
                    doc.add(txLinea);
                }
            }
            doc.addAuthor("Juan David González");
            doc.close(); 
        }catch(DocumentException | java.io.FileNotFoundException e){
            System.out.println("Error del archivo");
            e.printStackTrace();
        }
    }
    
    public String listarObjetos(){
        String lista = "";
        if(!listaRecuperadaFacturas.isEmpty()){
            for(Factura factura : listaRecuperadaFacturas) {
                lista += factura.getPropietario().getNombre() + " " + factura.getPropietario().getEdad()+ "\t" + 
                        factura.getPropiedad().getId() + "\t" + 
                        factura.getFecha() + "\t" + 
                        factura.getValor() + "\t" + 
                        factura.getMesAFacturar()+ "\t" + 
                        factura.getValorMetroCuadrado() + "\t" + 
                        factura.getValor()+ "\t" + 
                        Modelo.AGENDAMULTAS.listarMultas(factura.getMultas()) + "\t" +
                        factura.getDescuentos() + "\t" + 
                        factura.getFechaMaximaPago() + "\t" + 
                        factura.getInformacionPago() + "\t" + 
                        factura.getValorPagado() + "\n";
            }
        }
        return lista;
    }
}
