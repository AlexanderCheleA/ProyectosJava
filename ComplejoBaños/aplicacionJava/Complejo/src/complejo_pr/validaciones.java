/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complejo_pr;

import complejo_pr.conexion.ConexiOnBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;

/**
 *
 * @author C40S
 */
public class validaciones {
    
    public validaciones () {}
    
    /******* VALIDAR VACIOS ************/
    public boolean validarVacios(String valor, String nombreCampo) {
        if (valor.equals("")) {
            JOptionPane.showMessageDialog(null, "El campo "+nombreCampo+" no puede estar vacio");
            return false;
        }
        return true;
    }
    
    public boolean validar_dato_repedido_en_descripcion(TableView<ServiciosData> tablaServiciosData, String descripcion_servicios) {
        System.out.println("-------------------------------------------");
        for(int i=0;i<tablaServiciosData.getItems().size();i++){
            if((tablaServiciosData.getItems().get(i).getDescripcion_servicios()).equals(descripcion_servicios)){
                JOptionPane.showMessageDialog(null, "El servicio "+descripcion_servicios+" ya esta registrado");
                return true;
            }
        }
        return false;
    }
    ///validacion de cliente
    public int validacion_de_cliente(String cedula){  
        int bandera=0;
        ConexiOnBD cc = new ConexiOnBD();//SOLO INSTANCIA EL OBJETO, PERO NO CONECTA  ALA BD
        Connection cn = cc.getConnection();//Establece la conexion 
        PreparedStatement pst = null;//LLamado a la base de datos (tabla Empleado)
        String sql =  "SELECT cedula FROM personas";
        try{
            pst= cn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                if (cedula.equals(res.getString("cedula"))) {
                    bandera=1;
                }
            }
            if(bandera==0){
                JOptionPane.showMessageDialog(null, "No existe la cedula: "+cedula+"");
            }
        }catch(SQLException e){
                System.out.println("Errord, "+e.getMessage());
            }finally{
            try{
                if(cn != null){
                    cn.close();
                    pst.close();
                }
            }catch(SQLException e){
                System.out.println("ERROR: "+e.getMessage());
            }
        }
        return bandera;
    }
    //validacion empleado
    public int validacion_de_empleado(String cedula){  
        int bandera=0;
        ConexiOnBD cc = new ConexiOnBD();//SOLO INSTANCIA EL OBJETO, PERO NO CONECTA  ALA BD
        Connection cn = cc.getConnection();//Establece la conexion 
        PreparedStatement pst = null;//LLamado a la base de datos (tabla Empleado)
        String sql =  "SELECT cedula FROM empleado";
        try{
            pst= cn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                if (cedula.equals(res.getString("cedula"))) {
                    bandera=1;
                }
            }
            if(bandera==0){
                JOptionPane.showMessageDialog(null, "No existe la cedula: "+cedula+"");
            }
        }catch(SQLException e){
                System.out.println("Errord, "+e.getMessage());
            }finally{
            try{
                if(cn != null){
                    cn.close();
                    pst.close();
                }
            }catch(SQLException e){
                System.out.println("ERROR: "+e.getMessage());
            }
        }
        return bandera;
    }
    //cargos validado
       public int validacion_de_cargos(String descripcion_cargos){  
        int bandera=0;
        ConexiOnBD cc = new ConexiOnBD();//SOLO INSTANCIA EL OBJETO, PERO NO CONECTA  ALA BD
        Connection cn = cc.getConnection();//Establece la conexion 
        PreparedStatement pst = null;//LLamado a la base de datos (tabla Empleado)
        String sql =  "SELECT descripcion_cargos FROM cargos";
        try{
            pst= cn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                if (descripcion_cargos.equals(res.getString("descripcion_cargos"))) {
                    bandera=1;
                }
            }
            if(bandera==0){
                JOptionPane.showMessageDialog(null, "No existe cargo: "+descripcion_cargos+"");
            }
        }catch(SQLException e){
                System.out.println("Errord, "+e.getMessage());
            }finally{
            try{
                if(cn != null){
                    cn.close();
                    pst.close();
                }
            }catch(SQLException e){
                System.out.println("ERROR: "+e.getMessage());
            }
        }
        return bandera;
    }
    ///
    public Double validacion_de_servicio(String servicio){  
        Double bandera=0.0;
        ConexiOnBD cc = new ConexiOnBD();//SOLO INSTANCIA EL OBJETO, PERO NO CONECTA  ALA BD
        Connection cn = cc.getConnection();//Establece la conexion 
        PreparedStatement pst = null;//LLamado a la base de datos (tabla Empleado)
        String sql =  "SELECT descripcion_servicios,ser_precio FROM servicios";
        try{
            pst= cn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                if (servicio.equals(res.getString("descripcion_servicios"))) {
                    bandera=res.getDouble("ser_precio");
                }
            }
            if(bandera==0){
                JOptionPane.showMessageDialog(null, "No existe el servicio de "+servicio+"");
            }
        }catch(SQLException e){
                System.out.println("Errord, "+e.getMessage());
            }finally{
            try{
                if(cn != null){
                    cn.close();
                    pst.close();
                }
            }catch(SQLException e){
                System.out.println("ERROR: "+e.getMessage());
            }
        }
        return bandera;
    }
}
///