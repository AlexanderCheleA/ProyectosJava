/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complejo_pr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author C40S
 */
public class ServiciosData {
    //public SimpleIntegerProperty id_servicios = new SimpleIntegerProperty();
    //public SimpleIntegerProperty estado_de_servicio = new SimpleIntegerProperty();
    //public SimpleIntegerProperty id_categoria = new SimpleIntegerProperty();
    public SimpleIntegerProperty cantidad = new SimpleIntegerProperty();
    public SimpleStringProperty descripcion_servicios = new SimpleStringProperty();
    public SimpleDoubleProperty ser_precio = new SimpleDoubleProperty();
    public SimpleDoubleProperty cant_por_precio = new SimpleDoubleProperty();
    
    
    /*public Integer getId_servicios(){
        return id_servicios.get();
    }*/
    /*public Integer getEstado_de_servicio(){
        return estado_de_servicio.get();
    }*/
    
    /*public Integer getId_categoria(){
        return id_categoria.get();
    }*/
    public Integer getCantidad(){
        return cantidad.get();
    }
    public String getDescripcion_servicios(){
        return descripcion_servicios.get();
    }
    public Double getSer_precio(){
        return ser_precio.get();
    }
    public Double getCant_por_precio(){
        return cant_por_precio.get();
    }
    
    
}
