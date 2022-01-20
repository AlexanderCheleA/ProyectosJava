/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complejo_pr;

import Empleado.Empleado;
import complejo_pr.conexion.ConexiOnBD;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author C40S
 */

public class GerenteViewController implements Initializable {
        
            
    @FXML private ImageView btn_home,btn_reports,btn_exit;
    @FXML private AnchorPane h_home,h_reports;
    @FXML private ImageView btn_Rusuario,btn_Rservicio,btn_Riva,btn_RLEmpleado;
    @FXML private AnchorPane R_usuario,R_servicio,R_iva,R_listaEmpleado;
    
    //PRIVATES USADOS PARA LA LISTA DE EMPLEADOS
    @FXML private TableView<Empleado> TBempleado;
    private ObservableList<Empleado> listaEmpleado; 
    @FXML private TableColumn<Empleado, String> CLcedula;
    @FXML private TableColumn<Empleado, String> CLnombre;
    @FXML private TableColumn<Empleado, String> CLapellido;
    @FXML private TableColumn<Empleado, String> CLtelefono;
    @FXML private TableColumn<Empleado, String> CLcargo;
    
    
   
    @FXML 
    private void handleButtonAction(MouseEvent event){
        if(event.getTarget() == btn_home){
            h_home.setVisible(true);
            h_reports.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);

        }else 
            if(event.getTarget() == btn_reports){
            h_home.setVisible(false);
            h_reports.setVisible(true);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            R_listaEmpleado.setVisible(false);
            }
        else 
                //Component cmpnt;
            if(event.getTarget() == btn_exit){
                Stage staGe = (Stage) btn_exit.getScene().getWindow();
                staGe.close();
            }
        else 
            if(event.getTarget() == btn_Rusuario){
            R_listaEmpleado.setVisible(false);
            R_usuario.setVisible(true);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            h_home.setVisible(false);
            h_reports.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_Rservicio){
            R_listaEmpleado.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(true);
            R_iva.setVisible(false);
            h_home.setVisible(false);
            h_reports.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_Riva){
            R_listaEmpleado.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(true);
            h_home.setVisible(false);
            h_reports.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_RLEmpleado){
            R_listaEmpleado.setVisible(true);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            h_home.setVisible(false);
            h_reports.setVisible(false);
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Inicializamos la tabla
        ConexiOnBD cc = new ConexiOnBD();
        Connection cn = cc.getConnection();
        PreparedStatement pst = null;
        //TOMA LOS DATOS EN EL ARRAY
        listaEmpleado = FXCollections.observableArrayList();        
        Empleado.llenarinfoEmpleado(cn, listaEmpleado);
        TBempleado.setItems(listaEmpleado);
        
        CLcedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        CLapellido.setCellValueFactory(new PropertyValueFactory<>("priapellido"));
        CLnombre.setCellValueFactory(new PropertyValueFactory<>("prinombre"));
        CLtelefono.setCellValueFactory(new PropertyValueFactory<>("numerodetelefono"));
        CLcargo.setCellValueFactory(new PropertyValueFactory<>("descripcion_cargos"));
        
        cc.desconectar();
        
    }
public void ActualizarTablaEmpleado(){
        ConexiOnBD cc = new ConexiOnBD();
        Connection cn = cc.getConnection();
        PreparedStatement pst = null;
        //TOMA LOS DATOS EN EL ARRAY
        listaEmpleado = FXCollections.observableArrayList();        
        Empleado.llenarinfoEmpleado(cn, listaEmpleado);
        TBempleado.setItems(listaEmpleado);
        CLcedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        CLapellido.setCellValueFactory(new PropertyValueFactory<>("priapellido"));
        CLnombre.setCellValueFactory(new PropertyValueFactory<>("prinombre"));
        CLtelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        CLcargo.setCellValueFactory(new PropertyValueFactory<>("descripcion_cargos"));
        
        cc.desconectar();
    }
        
     
}
