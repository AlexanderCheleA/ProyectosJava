//ACCESO SOLO PARA LOS ADMINISTRADORES
package complejo_pr;

import ComboBoox.ClaseObjetoParaComboBox;
import Empleado.Empleado;
import complejo_pr.Cliente;
import complejo_pr.ServiciosData;
import complejo_pr.conexion.ConexiOnBD;
import complejo_pr.validaciones;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
 *
 * @author C40S
 */
public class FXMLDocumentController implements Initializable {
    
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
 private Object listadoCliente;
     
    @FXML private TableColumn<Cliente, String> cedulaCL;
    @FXML private TableColumn<Cliente, String> nombresCL;
    @FXML private TableColumn<Cliente, String> apellidosCL;
    @FXML private TableColumn<Cliente, String> nombres2CL;
    @FXML private TableColumn<Cliente, String> apellidos2CL;
    @FXML private TableColumn<Cliente, String> telefonoCL;
    @FXML private TableColumn<Cliente, Integer> estadoCL;
        
            
    @FXML private ImageView btn_home,btn_user,btn_process,btn_reports,btn_exit;
    @FXML private AnchorPane h_home,h_user,h_process,h_reports;
    @FXML private ImageView btn_rservice,btn_rrecaudacion,btn_rasignacion,btn_rcategoria;
    @FXML private AnchorPane r_service,r_recaudacion,r_asignacion,r_categoria;
    @FXML private ImageView btn_Rusuario,btn_Rservicio,btn_Riva,btn_Rempleado,btn_Rcargo,btn_Rcliente,btn_RLEmpleado;
    @FXML private AnchorPane R_usuario,R_servicio,R_iva,R_empleado,R_clientes,R_cargo,R_listaEmpleado;
     
    
    @FXML private TableView<ServiciosData> tablaServiciosData;
        
    @FXML private TableColumn<ServiciosData, Integer> cantidadCL;
    @FXML private TableColumn<ServiciosData, String> servicioCL;
    @FXML private TableColumn<ServiciosData, Double> preciounitarioCL;
    @FXML private TableColumn<ServiciosData, Double> precioporcantidadCL;
    ObservableList<ServiciosData> serviciosDatas;
    
    private int posicionServiciosDataEnTabla;
    
   
    
    @FXML private TableView<reporte_por_servicio> TBreporte_por_servicio;
    private ObservableList<reporte_por_servicio> listaReporte_por_servicio; 
    @FXML private TableColumn<reporte_por_servicio, String> CLdescripcion_del_servicio;
    @FXML private TableColumn<reporte_por_servicio, Double> CLsuma;
    
    @FXML DatePicker DatePick;
    
    //PRIVATES USADOS PARA LA LISTA DE EMPLEADOS
    @FXML private TableView<Empleado> TBempleado;
    private ObservableList<Empleado> listaEmpleado; 
    @FXML private TableColumn<Empleado, String> CLcedula;
    @FXML private TableColumn<Empleado, String> CLnombre;
    @FXML private TableColumn<Empleado, String> CLapellido;
    @FXML private TableColumn<Empleado, String> CLtelefono;
    @FXML private TableColumn<Empleado, String> CLcargo;
    
    //DECLARAR TODOS LOS COMBOBOX A USAR
    @FXML ComboBox<ClaseObjetoParaComboBox> CBcargoE;//cargo-empleado
    
   
    public ResultSet resultado;
    
    //DECLARAR TODOS LOS TEXFIELD A USAR
    @FXML TextField TXidC,TXdescripcionC,TXestadoC;//TEXFIELD-CARGOS
    @FXML TextField TXidP,TXcedulaP,TXnombre1P,TXnombre2P,TXapellido1P,TXapellido2P,TXtelefonoP,TXemailP,TXdireccionP,TXestadoP;//TEXTFIELD CARGOS
    @FXML TextField TXidE,TXnombre1E,TXnombre2E,TXapellido1E,TXapellido2E,TXemailE,TXdireccionE,TXcedulaE,TXtelefonoE;
    @FXML TextField TXusuarioE,TXclaveE,TXestadoE;//TEXTFIELD-EMPLEADOS
    @FXML DatePicker DTPfechaE;
    
    @FXML 
    private void handleButtonAction(MouseEvent event){
        //@FXML private ImageView btn_Rusuario,btn_Rservicio,btn_Riva,btn_Rempleado,btn_Rcargo,btn_Rcliente,btn_RLEmpleado;
    //@FXML private AnchorPane R_usuario,R_servicio,R_iva,R_empleado,R_clientes,R_cargo,R_listaEmpleado;
        if(event.getTarget() == btn_home){
            h_home.setVisible(true);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            R_empleado.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            
        }else
            if(event.getTarget() == btn_user){
            h_home.setVisible(false);
            h_user.setVisible(true);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            R_empleado.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_listaEmpleado.setVisible(false);
            
            }
        else 
            if(event.getTarget() == btn_process){
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(true);
            h_reports.setVisible(false);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            R_empleado.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_listaEmpleado.setVisible(false);
            
            }
        else 
            if(event.getTarget() == btn_reports){
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(true);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            R_empleado.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_listaEmpleado.setVisible(false);
            }
        else 
                //Component cmpnt;
            if(event.getTarget() == btn_exit){
                Stage staGe = (Stage) btn_exit.getScene().getWindow();
                staGe.close();
            }
        else 
            if(event.getTarget() == btn_rcategoria){
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(true);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            R_empleado.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_listaEmpleado.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_rasignacion){
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(true);
            r_categoria.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            R_empleado.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_listaEmpleado.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_rrecaudacion){
            r_service.setVisible(false);
            r_recaudacion.setVisible(true);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            R_empleado.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_listaEmpleado.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_rservice){
            R_listaEmpleado.setVisible(false);
            r_service.setVisible(true);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            R_empleado.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_Rempleado){
            R_listaEmpleado.setVisible(false);
            R_empleado.setVisible(true);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_Rcargo){
            R_listaEmpleado.setVisible(false);
            R_empleado.setVisible(false);
            R_cargo.setVisible(true);
            R_clientes.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_Rcliente){
            R_listaEmpleado.setVisible(false);
            R_empleado.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(true);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            }    
            
        else 
            if(event.getTarget() == btn_Rusuario){
            R_listaEmpleado.setVisible(false);
            R_empleado.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_usuario.setVisible(true);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_Rservicio){
            R_listaEmpleado.setVisible(false);
            R_empleado.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(true);
            R_iva.setVisible(false);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_Riva){
            R_listaEmpleado.setVisible(false);
            R_empleado.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(true);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
            h_home.setVisible(false);
            h_user.setVisible(false);
            h_process.setVisible(false);
            h_reports.setVisible(false);
            }
        else 
            if(event.getTarget() == btn_RLEmpleado){
            R_listaEmpleado.setVisible(true);
            R_empleado.setVisible(false);
            R_cargo.setVisible(false);
            R_clientes.setVisible(false);
            R_usuario.setVisible(false);
            R_servicio.setVisible(false);
            R_iva.setVisible(false);
            r_service.setVisible(false);
            r_recaudacion.setVisible(false);
            r_asignacion.setVisible(false);
            r_categoria.setVisible(false);
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
        //TOMA LOS DATOS EN EL ARRAY
        listaEmpleado = FXCollections.observableArrayList();        
        Empleado.llenarinfoEmpleado(cn, listaEmpleado);
        TBempleado.setItems(listaEmpleado);
       
        try {
            cargar_combos();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CLcedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        CLapellido.setCellValueFactory(new PropertyValueFactory<>("priapellido"));
        CLnombre.setCellValueFactory(new PropertyValueFactory<>("prinombre"));
        CLtelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        CLcargo.setCellValueFactory(new PropertyValueFactory<>("descripcion_cargos"));
        
        cc.desconectar();
        
        
        
        //TOMA LOS DATOS EN EL ARRAY
        listaReporte_por_servicio = FXCollections.observableArrayList();        
        reporte_por_servicio.llenarinfoReporte_por_servicio(cn, listaReporte_por_servicio);
        TBreporte_por_servicio.setItems(listaReporte_por_servicio);
       
        try {
            cargar_combos();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CLdescripcion_del_servicio.setCellValueFactory(new PropertyValueFactory<>("descripcion_servicios"));
        CLsuma.setCellValueFactory(new PropertyValueFactory<>("suma"));
        cc.desconectar();
        
    
        this.inicializarTablaServiciosDatas();
        
        // Seleccionar las tuplas de la tabla de las personas
        final ObservableList<ServiciosData> tablaServiciosDataSel = tablaServiciosData.getSelectionModel().getSelectedItems();
        tablaServiciosDataSel.addListener(selectorTablaServiciosDatas);
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
    
    public void cargar_combos() throws SQLException {

    String cadena = "SELECT id_cargos,descripcion_cargos FROM cargos";
    ConexiOnBD Base = new ConexiOnBD();
        ResultSet res = Base.EjecutarSql(cadena);
        while(res.next()){
            ClaseObjetoParaComboBox cbo = new ClaseObjetoParaComboBox((res.getInt(1)), res.getString(2));
            CBcargoE.getItems().add(cbo);
        }
    
    }
    
    public void GuardarCargo() throws SQLException {
        int bandera24 = 1;
        String consul = (TXdescripcionC.getText());
        bandera24 = validacion.validacion_de_cargos(consul);
        if (bandera24 == 0) {
            try {
                String cadena = "insert into cargos(id_cargos,descripcion_cargos,estado_cargos)values "
                        + "(" + TXidC.getText() + ",'" + TXdescripcionC.getText() + "','"
                        + TXestadoC.getText() + "')";

                ConexiOnBD Base = new ConexiOnBD();
                resultado = Base.EjecutarSql(cadena);
                JOptionPane.showMessageDialog(null, "Cargo registrado exitosamente.....!", "Registro Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Error al grabar.....!", "Peligro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (bandera24 == bandera24) {
                JOptionPane.showMessageDialog(null, "cargo ya ha sido registrado anteriormente.....!", "Peligro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //validado cliente
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
//validado empleado
    public void GuardarEmpleado() throws SQLException {
        int bandera24 = 1;
        String consul = (TXcedulaE.getText());
        bandera24 = validacion.validacion_de_empleado(consul);
        if (bandera24 == 0) {
            try {
                String cadena = "insert into empleado(id_empleado,usuario,contraseña,fecha,estado_empleao,id_cargos,"
                        + "cedula,prinombre,segnombre,priapellido,segapellido,telefono,email,direccion)values "
                        + "(" + TXidE.getText() + ",'" + TXusuarioE.getText() + "','"
                        + TXclaveE.getText() + "','" + DTPfechaE.getValue() + "','"
                        + TXestadoE.getText() + "'," + CBcargoE.getValue().getCodigo() + ",'"
                        + TXcedulaE.getText() + "','" + TXnombre1E.getText() + "','"
                        + TXnombre2E.getText() + "','" + TXapellido1E.getText() + "','"
                        + TXapellido2E.getText() + "','" + TXtelefonoE.getText() + "','"
                        + TXemailE.getText() + "','" + TXdireccionE.getText() + "')";

                ConexiOnBD Base = new ConexiOnBD();
                resultado = Base.EjecutarSql(cadena);
                JOptionPane.showMessageDialog(null, "Empleado registrado exitosamente.....!", "Registro Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Error al grabar.....!", "Peligro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (bandera24 == bandera24) {
                JOptionPane.showMessageDialog(null, "empleado ya ha sido registrado anteriormente.....!", "Peligro",
                        JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(null, "Digite cedula y pulse Consultar", "Recomendacion",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void BuscarEmpleado() {                                          
      
        ConexiOnBD cc = new ConexiOnBD();
        Connection con = null;
        PreparedStatement ps = null;
        try{    
            con = cc.getConnection();
            ps = con.prepareStatement("SELECT * FROM empleado WHERE cedula = ?");
            ps.setString(1, TXcedulaE.getText());
            
            resultado = ps.executeQuery();
            if(resultado.next()){                
                TXidE.setText(resultado.getString("id_empleado"));
                TXusuarioE.setText(resultado.getString("usuario"));
                TXclaveE.setText(resultado.getString("contraseña"));
                //DTPfechaE.setValue(resultado.getValue("fecha"));
                TXestadoE.setText(resultado.getString("estado_empleao"));
                //CBcargoE.setValue().getCodigo();
                TXcedulaE.setText(resultado.getString("cedula"));
                TXnombre1E.setText(resultado.getString("prinombre"));
                TXnombre2E.setText(resultado.getString("segnombre"));
                TXapellido1E.setText(resultado.getString("priapellido"));
                TXapellido2E.setText(resultado.getString("segapellido"));
                TXtelefonoE.setText(resultado.getString("telefono"));
                TXemailE.setText(resultado.getString("email"));
                TXdireccionE.setText(resultado.getString("direccion"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe un empleado con esa cedula ");
            }   
        } catch(HeadlessException | SQLException e){
            System.err.println(e);
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
    public void limpiarCajas(){
        //CAJAS DE EMPLEADOS
        TXidE.setText(null);
        TXusuarioE.setText(null);
        TXclaveE.setText(null);
        DTPfechaE.setValue(null);
        TXestadoE.setText(null);
        CBcargoE.setValue(null);
        TXcedulaE.setText(null);
        TXnombre1E.setText(null);
        TXnombre2E.setText(null);
        TXapellido1E.setText(null);
        TXapellido2E.setText(null);
        TXtelefonoE.setText(null);
        TXemailE.setText(null);
        TXdireccionE.setText(null);
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
        //CAJAS DE CARGO
        TXidC.setText(null);
        TXdescripcionC.setText(null);
        TXestadoC.setText(null);
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
    
    
    
    public void ListarTodosLosVehiculos() throws SQLException{
        ConexiOnBD cc = new ConexiOnBD();
        Connection cn = cc.getConnection(); 
        
        PreparedStatement pst = null;
        
        listaReporte_por_servicio = FXCollections.observableArrayList();
        reporte_por_servicio.llenarinfoReporte_por_servicio(cn, listaReporte_por_servicio);
        TBreporte_por_servicio.setItems(listaReporte_por_servicio);
        
        CLdescripcion_del_servicio.setCellValueFactory(new PropertyValueFactory<>("descripcion_servicios"));
        CLsuma.setCellValueFactory(new PropertyValueFactory<>("suma"));
        
    
        
        
    }
    
}
