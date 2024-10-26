/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Beans.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
public class AgendaUsuario {
    
    ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    ArrayList<Usuario> listaRecuperadaUsuarios = new ArrayList<Usuario>();

    
    
    public void adicionarUsuario(Usuario usuario){
        boolean bandera = false;
        for(Usuario objUsuario : listaRecuperadaUsuarios){
            if(objUsuario.getPassword().equals(usuario.getPassword())){
                bandera = true;
            }else{
                bandera = false;
            }
        }
        if(bandera == false){
            listaUsuarios.add(usuario); 
        }
        try {
            guardarFichero();
        } catch (IOException ex) {
            Logger.getLogger(AgendaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarUsuario(String password){
        
        for(Usuario usuario : listaRecuperadaUsuarios){
            if(usuario.getPassword().equals(password)){
                listaRecuperadaUsuarios.remove(usuario);
            }
        }
        try {
            actualizarFichero(listaRecuperadaUsuarios);
        } catch (IOException ex) {
            Logger.getLogger(AgendaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void guardarFichero() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("infousuarios.csv"))) {
            for (Usuario usuario : listaUsuarios) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(usuario.getNombre())
                      .add(usuario.getPassword())
                      .add(usuario.getTipoCuenta());
                bw.write(joiner.toString());
                bw.newLine();
            }
        }
    }
    
    public void leerFichero() throws IOException, ClassNotFoundException {
        listaRecuperadaUsuarios.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("infousuarios.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Usuario usuario = new Usuario(values[0], values[1], values[2]);
                listaRecuperadaUsuarios.add(usuario);
            }
        }
    }
    
    public void actualizarFichero(List<Usuario> lista)throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("infousuarios.csv"))) {
            for (Usuario usuario : lista) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(usuario.getNombre())
                      .add(usuario.getPassword())
                      .add(usuario.getTipoCuenta());
                bw.write(joiner.toString());
                bw.newLine();
            }
        }
    }
    
    public boolean encontrarUsuario(String nom, String pass, String tipo){
        for(Usuario usuario : listaRecuperadaUsuarios){
            if(usuario.getNombre().equals(nom) && usuario.getPassword().equals(pass) && usuario.getTipoCuenta().equals(tipo)){
                return true;
            }
        }
        return false;
    }
    
    
}
