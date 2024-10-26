/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans; 
/**
 *
 * @author Juan David
 */
import java.util.ArrayList;
public class Factura {
    private Propietario propietario;
    private Propiedad propiedad;
    private String fecha;
    private double valor;
    private String mesAFacturar;
    private double valorMetroCuadrado;
    private double valorPagar;
    private ArrayList<Multa> multas;
    private double descuentos;
    private String fechaMaximaPago;
    private String informacionPago;
    private double valorPagado;
    private String nFactura;

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMesAFacturar() {
        return mesAFacturar;
    }

    public void setMesAFacturar(String mesAFacturar) {
        this.mesAFacturar = mesAFacturar;
    }

    public double getValorMetroCuadrado() {
        return valorMetroCuadrado;
    }

    public void setValorMetroCuadrado(double valorMetroCuadrado) {
        this.valorMetroCuadrado = valorMetroCuadrado;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public ArrayList<Multa> getMultas() {
        return multas;
    }

    public void setMultas(ArrayList<Multa> multas) {
        this.multas = multas;
    }

    public double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(double descuentos) {
        this.descuentos = descuentos;
    }

    public String getFechaMaximaPago() {
        return fechaMaximaPago;
    }

    public void setFechaMaximaPago(String fechaMaximaPago) {
        this.fechaMaximaPago = fechaMaximaPago;
    }

    public String getInformacionPago() {
        return informacionPago;
    }

    public void setInformacionPago(String informacionPago) {
        this.informacionPago = informacionPago;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    
    
    public Factura() {
    }

    public Factura(String nFactura, Propietario propietario, Propiedad propiedad, String fecha, double valor, String mesAFacturar, double valorMetroCuadrado, double valorPagar, ArrayList<Multa> multas, double descuentos, String fechaMaximaPago, String informacionPago, double valorPagado) {
        this.nFactura = nFactura;
        this.propietario = propietario;
        this.propiedad = propiedad;
        this.fecha = fecha;
        this.valor = valor;
        this.mesAFacturar = mesAFacturar;
        this.valorMetroCuadrado = valorMetroCuadrado;
        this.valorPagar = valorPagar;
        this.multas = multas;
        this.descuentos = descuentos;
        this.fechaMaximaPago = fechaMaximaPago;
        this.informacionPago = informacionPago;
        this.valorPagado = valorPagado;
    }

    public Factura(String nFactura, Propietario propietario, Propiedad propiedad, String fecha, double valor, String mesAFacturar, double valorMetroCuadrado, double descuentos, String fechaMaximaPago, String informacionPago) {
        this.nFactura = nFactura;
        this.propietario = propietario;
        this.propiedad = propiedad;
        this.fecha = fecha;
        this.valor = valor;
        this.mesAFacturar = mesAFacturar;
        this.valorMetroCuadrado = valorMetroCuadrado;
        this.descuentos = descuentos;
        this.fechaMaximaPago = fechaMaximaPago;
        this.informacionPago = informacionPago;
        
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura{");
        sb.append("nFactura=").append(nFactura);
        sb.append("propietario=").append(propietario);
        sb.append(", propiedad=").append(propiedad);
        sb.append(", fecha=").append(fecha);
        sb.append(", valor=").append(valor);
        sb.append(", mesAFacturar=").append(mesAFacturar);
        sb.append(", valorMetroCuadrado=").append(valorMetroCuadrado);
        sb.append(", valorPagar=").append(valorPagar);
        sb.append(", multas=").append(multas);
        sb.append(", descuentos=").append(descuentos);
        sb.append(", fechaMaximaPago=").append(fechaMaximaPago);
        sb.append(", informacionPago=").append(informacionPago);
        sb.append(", valorPagado=").append(valorPagado);
        sb.append('}');
        return sb.toString();
    }

    public String getnFactura() {
        return nFactura;
    }

    public void setnFactura(String nFactura) {
        this.nFactura = nFactura;
    }
    
    
}
