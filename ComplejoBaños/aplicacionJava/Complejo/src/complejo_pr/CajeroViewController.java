//ACCESO SOLO PARA CAJERO
package complejo_pr;

import Empleado.Empleado;
import complejo_pr.conexion.ConexiOnBD;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author C40S
 */

public class CajeroViewController implements Initializable {
    
    private validaciones validacion = new validaciones();
    
    
    @FXML private Button buscarBT;
    @FXML private Button aniadirBT;
    @FXML private Button nuevoBT;
    @FXML private Button eliminarBT;
    @FXML private Button cancelarBT;
    @FXML private Button guardarBT;
    @FXML private Button modificarBT;
    
    @FXML private TextField cedulaTF;
    @FXML private TextField servicioTF;
    @FXML private TextField cantidadTF;
    
    @FXML private Label lb_sms;
    @FXML private Label numerodefacturaLB;
    @FXML private Label fechaLB;
    @FXML private Label subtotalLB;
    @FXML private Label subtotalivaLB;
    @FXML private Label totalLB;
    
    //acceso a los paquetes donde estan los set o get<usuario mod>
   
    @FXML private TableView<Cliente> datosdepersonas;
    private ObservableList<Cliente> lstadoCliente;
    //private Object listadoCliente;
     
    @FXML private TableColumn<Cliente, String> cedulaCL;
    @FXML private TableColumn<Cliente, String> nombresCL;
    @FXML private TableColumn<Cliente, String> apellidosCL;
    @FXML private TableColumn<Cliente, String> nombres2CL;
    @FXML private TableColumn<Cliente, String> apellidos2CL;
    @FXML private TableColumn<Cliente, String> telefonoCL;
    @FXML private TableColumn<Cliente, Integer> estadoCL;
    
            
    @FXML private ImageView btn_home,btn_user,btn_process,btn_reports,btn_exit;//CORRECTO
    @FXML private AnchorPane h_home,h_user,h_process,h_reports;//CORRECTO
    @FXML private ImageView btn_rrecaudacion;
    @FXML private AnchorPane r_recaudacion;
    @FXML private ImageView btn_Rdiaria,btn_Rcliente;
    @FXML private AnchorPane R_diaria,R_clientes;
     
    
    @FXML private TableView<ServiciosData> tablaServiciosData;
        
    @FXML private TableColumn<ServiciosData, Integer> cantidadCL;
    @FXML private TableColumn<ServiciosData, String> servicioCL;
    @FXML private TableColumn<ServiciosData, Double> preciounitarioCL;
    @FXML private TableColumn<ServiciosData, Double> precioporcantidadCL;
    ObservableList<ServiciosData> serviciosDatas;
    
    private int posicionServiciosDataEnTabla;
    
    public ResultSet resultado;
    
    //DECLARAR TODOS LOS TEXFIELD A USAR
    @FXML TextField TXidP,TXcedulaP,TXnombre1P,TXnombre2P,TXapellido1P,TXapellido2P,TXtelefonoP,TXemailP,TXdireccionP,TXestadoP;//TEXTFIELD CARGOS
   
    @FXML 
    private void handleButtonAction(MouseEvent event){
        if(event.getTarget() == btn_home){
            h_home.setVisible(true);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            r_recaudacion.setVisible(false);
            R_diaria.setVisible(false);
            R_clientes.setVisible(false);
            
        }else
            if(event.getTarget() == btn_user){
            h_home.setVisible(false);
            h_user.setVisible(true);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            r_recaudacion.setVisible(false);
            R_diaria.setVisible(false);
            R_clientes.setVisible(false);
            
            }
        else 
            if(event.getTarget() == btn_process){
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(true);
            h_reports.setVisible(false);
            r_recaudacion.setVisible(false);
            R_diaria.setVisible(false);
            R_clientes.setVisible(false);
            
            }
        else 
            if(event.getTarget() == btn_reports){
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(true);
            r_recaudacion.setVisible(false);
            R_diaria.setVisible(false);
            R_clientes.setVisible(false);
            }
        else 
                //Component cmpnt;
            if(event.getTarget() == btn_exit){
                Stage staGe = (Stage) btn_exit.getScene().getWindow();
                staGe.close();
            }
        else 
            if(event.getTarget() == btn_rrecaudacion){
            r_recaudacion.setVisible(true);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            R_diaria.setVisible(false);
            R_clientes.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_Rcliente){
            R_clientes.setVisible(true);
            R_diaria.setVisible(false);
            r_recaudacion.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            }    
            
        else 
            if(event.getTarget() == btn_Rdiaria){
            R_clientes.setVisible(false);
            R_diaria.setVisible(true);
            r_recaudacion.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
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
       
        
        this.inicializarTablaServiciosDatas();
        
        // Seleccionar las tuplas de la tabla de las personas
        final ObservableList<ServiciosData> tablaServiciosDataSel = tablaServiciosData.getSelectionModel().getSelectedItems();
        tablaServiciosDataSel.addListener(selectorTablaServiciosDatas);
        cc.desconectar();
    }    
    public void GuardarCliente() {
        int bandera24 = 1;
        String consul = (TXcedulaP.getText());
        bandera24 = validacion.validacion_de_cliente(consul);
        if (bandera24 == 0) {
            try {
                String cadena = "insert into personas(id_persona,cedula,prinombre,segnombre,priapellido,segapellido,"
                        + "numerodetelefono,email,direccion,estado_persona)values "
                        + "(" + TXidP.getText() + ",'" + TXcedulaP.getText() + "','"
                        + TXnombre1P.getText() + "','" + TXnombre2P.getText() + "','"
                        + TXapellido1P.getText() + "','" + TXapellido2P.getText() + "','"
                        + TXtelefonoP.getText() + "','" + TXemailP.getText() + "','"
                        + TXdireccionP.getText() + "','" + TXestadoP.getText() + "')";

                ConexiOnBD Base = new ConexiOnBD();
                resultado = Base.EjecutarSql(cadena);
                JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.....!", "Registro Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Error al grabar.....!", "Peligro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (bandera24 == bandera24) {
                JOptionPane.showMessageDialog(null, "cliente ya ha sido registrado anteriormente.....!", "Peligro",
                        JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(null, "Digite cedula y pulse Consultar", "Recomendacion",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    public void BuscarCliente() {                                          
      
        ConexiOnBD cc = new ConexiOnBD();
        Connection con = null;
        PreparedStatement ps = null;
        try{    
            con = cc.getConnection();
            ps = con.prepareStatement("SELECT * FROM personas WHERE cedula = ?");
            ps.setString(1, TXcedulaP.getText());
            
            resultado = ps.executeQuery();

            if(resultado.next()){                
                TXidP.setText(resultado.getString("id_persona"));
                TXcedulaP.setText(resultado.getString("cedula"));
                TXestadoP.setText(resultado.getString("estado_persona"));
                TXdireccionP.setText(resultado.getString("direccion"));
                TXnombre1P.setText(resultado.getString("prinombre"));
                TXnombre2P.setText(resultado.getString("segnombre"));
                TXapellido1P.setText(resultado.getString("priapellido"));
                TXapellido2P.setText(resultado.getString("segapellido"));
                TXtelefonoP.setText(resultado.getString("numerodetelefono"));
                TXemailP.setText(resultado.getString("email"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe un cliente con esa cedula ");
            }   
        } catch(HeadlessException | SQLException e){
            System.err.println(e);
        }
    }
    public void Buscar_cliente(ActionEvent event) throws SQLException{  
        int bandera=1;    
            ConexiOnBD cc = new ConexiOnBD();//SOLO INSTANCIA EL OBJETO, PERO NO CONECTA  ALA BD
            Connection cn = cc.getConnection();//Establece la conexion 
            PreparedStatement pst = null;//LLamado a la base de datos (tabla Empleado)
            String sql =  "SELECT * FROM personas";         
            try{
                pst= cn.prepareStatement(sql);
                ResultSet res = pst.executeQuery();
                
                System.out.println("Acceso correcto busca los atos");
                //0928141111
                String d=cedulaTF.getText();
                lstadoCliente = FXCollections.observableArrayList();
                Cliente.llenarinfoCliente(cn, lstadoCliente,d);
                datosdepersonas.setItems(lstadoCliente);
                cedulaCL.setCellValueFactory(new PropertyValueFactory<>("cedula"));
                nombresCL.setCellValueFactory(new PropertyValueFactory<>("prinombre"));
                nombres2CL.setCellValueFactory(new PropertyValueFactory<>("segnombre"));
                apellidosCL.setCellValueFactory(new PropertyValueFactory<>("priapellido"));
                apellidos2CL.setCellValueFactory(new PropertyValueFactory<>("segapellido"));
                telefonoCL.setCellValueFactory(new PropertyValueFactory<>("numerodetelefono"));
                estadoCL.setCellValueFactory(new PropertyValueFactory<>("estado_persona"));
                
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
    }
    public void limpiarCajas(){

        //CAJAS DE CLIENTES
        TXidP.setText(null);
        TXcedulaP.setText(null);
        TXnombre1P.setText(null);
        TXnombre2P.setText(null);
        TXapellido1P.setText(null);
        TXapellido2P.setText(null);
        TXtelefonoP.setText(null);
        TXemailP.setText(null);
        TXestadoP.setText(null);
        TXdireccionP.setText(null);
 }

    /**
     * Listener de la tabla personas
     */
    private final ListChangeListener<ServiciosData> selectorTablaServiciosDatas =
            new ListChangeListener<ServiciosData>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends ServiciosData> c) {
                    ponerServiciosDataSeleccionado();
                }
    };
    
    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void ponerServiciosDataSeleccionado() {
        final ServiciosData serviciosData = getTablaServiciosDatasSeleccionado();
        posicionServiciosDataEnTabla = serviciosDatas.indexOf(serviciosData);
        if (serviciosData != null) {
            //System.out.println("posicion    "+posicionServiciosDataEnTabla+"");
            // Pongo los textFields con los datos correspondientes
            servicioTF.setText(serviciosData.getDescripcion_servicios());
            cantidadTF.setText(serviciosData.getCantidad().toString());
        }
    }
    
    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    public ServiciosData getTablaServiciosDatasSeleccionado() {
        if (tablaServiciosData != null) {
            List<ServiciosData> tabla = tablaServiciosData.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final ServiciosData ServiciosDataSeleccionado = tabla.get(0);
                return ServiciosDataSeleccionado;
            }
        }
        return null;
    }

    private void inicializarTablaServiciosDatas() {
        
        cantidadCL.setCellValueFactory(new PropertyValueFactory<ServiciosData, Integer>("cantidad"));
        servicioCL.setCellValueFactory(new PropertyValueFactory<ServiciosData, String>("descripcion_servicios"));
        preciounitarioCL.setCellValueFactory(new PropertyValueFactory<ServiciosData, Double>("ser_precio"));
        precioporcantidadCL.setCellValueFactory(new PropertyValueFactory<ServiciosData, Double>("cant_por_precio"));

        serviciosDatas = FXCollections.observableArrayList();
        tablaServiciosData.setItems(serviciosDatas);
    }

    public void Nuevo(){
        servicioTF.setText("");
        cantidadTF.setText("");
    }
    
    
    /**
    * Método que realiza las acciones tras pulsar el boton "Buscar_servicio"
    *
    * @param event
    */
    public void Buscar_servicio(ActionEvent event) throws SQLException{  
        int bandera=1;
            ConexiOnBD cc = new ConexiOnBD();//SOLO INSTANCIA EL OBJETO, PERO NO CONECTA  ALA BD
            Connection cn = cc.getConnection();//Establece la conexion 
            PreparedStatement pst = null;//LLamado a la base de datos (tabla Empleado)
            String sql =  "SELECT * FROM servicios";
            try{
                pst= cn.prepareStatement(sql);
                ResultSet res = pst.executeQuery();
                
                if(!validacion.validarVacios(servicioTF.getText() , "servicios")){
                    return;
                }
                if(!validacion.validarVacios(cantidadTF.getText() , "cantidad")){
                    return;   
                }
                Double band_ser=(validacion.validacion_de_servicio(servicioTF.getText()));
                if((band_ser)!=0.0){
                    ServiciosData serviciosData = new ServiciosData();
                   
                    boolean banddeval=validacion.validar_dato_repedido_en_descripcion(tablaServiciosData, servicioTF.getText());
                    if(banddeval==false){
                        serviciosData.descripcion_servicios.set(servicioTF.getText());
                        serviciosData.cantidad.set(Integer.parseInt(cantidadTF.getText()));
                        serviciosData.ser_precio.set(band_ser);
                        serviciosData.cant_por_precio.set(((Integer.parseInt(cantidadTF.getText())))*band_ser);
                        serviciosDatas.add(serviciosData);
                        
                        
                        
                        //---NOTA
                        // creo que esto no debe ir aqui ponerla en otra funcion 
                        // o si no ponerlo en el guardar y en el modificar
                        // O simplemente dejarla aqui y cuando modifique presione añadir
                        // y no tendra problema con este cod
                        
                        Double subt=0.0;
                        for(int i=0;i<tablaServiciosData.getItems().size();i++){
                            subt=(tablaServiciosData.getItems().get(i).getCant_por_precio())+subt;
                        }
                        
                        
                        String  cadena = String.valueOf(subt);
                        subtotalLB.setText(cadena);
                        
                        Double iva=(0.12*subt);
                        String  cadena_iva = String.valueOf(iva);
                        subtotalivaLB.setText(cadena_iva);
                        
                        String  cadena_total = String.valueOf((subt*(12/100))+subt);
                        totalLB.setText(cadena_total);
                    }
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
    }
}


