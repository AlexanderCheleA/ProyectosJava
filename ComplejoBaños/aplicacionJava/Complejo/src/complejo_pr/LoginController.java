package complejo_pr;

import complejo_pr.conexion.ConexiOnBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author C40S
 */
public class LoginController implements Initializable {
    @FXML private TextField txt_usuario;
    @FXML private TextField txt_clave;
    @FXML private PasswordField txt_pass;
    @FXML private Button btn_ingresar;
    @FXML private Label lb_sms;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * 
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void validarUsuarioContrasenia(ActionEvent event){   
        FXMLLoader fxmlLoader=null;
        Parent root=null;
        //Stage stage=null;
    try{
        ConexiOnBD cc = new ConexiOnBD();//SOLO INSTANCIA EL OBJETO, PERO NO CONECTA  ALA BD
        Connection cn = cc.getConnection();//Establece la conexion 
        PreparedStatement pst = null;//LLamado a la base de datos (tabla usuario)
        String sql =  "SELECT * FROM empleado e"; 
        try{
            pst= cn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while(res.next()){
            //if (txt_usuario.getText().equals(res.getString("usuario"))&& txt_clave.getText().equals(res.getString("contraseña"))) {
            if (txt_usuario.getText().equals(res.getString("usuario"))&& txt_pass.getText().equals(res.getString("contraseña"))) {
                lb_sms.setText("Hola "+txt_usuario.getText());//+" "+txt_clave.getText());
               System.out.println("Acceso correcto");
               //****************************CERRAR EL LOGIN PARA ABRIR EL SIGUIENTE PANEL*********************
               Stage staGe = (Stage) btn_ingresar.getScene().getWindow();
               staGe.close();
               //**********************************************************************************************      
               
               //**********************************************************************************************      
               //DEPENDE DEL ID CARGO, ESTA ASIGNADO EL INGRESO ES DECIR :
               // 1. CAJERO, 2.ADMINISTRADOR, 3.GERENTE
               
               switch (res.getInt("id_cargos")) {
                        case 1:
                            System.out.println("Acceso correcto de cajero");
                            fxmlLoader = new FXMLLoader(getClass().getResource("CajeroView.fxml"));
                            root= (Parent) fxmlLoader.load();
                            Stage stage1 = new Stage();
                            stage1.setTitle("CajeroView");
                            stage1.setScene(new Scene(root));
                            stage1.show();
                            break;
                        case 2:
                            System.out.println("Acceso correcto de administrador");
                            fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                            root= (Parent) fxmlLoader.load();
                            Stage stage2 = new Stage();
                            stage2.setTitle("FXMLDocument");
                            stage2.setScene(new Scene(root));
                            stage2.show();
                            break;
                        case 3:
                            System.out.println("Acceso correcto de gerente");
                            fxmlLoader = new FXMLLoader(getClass().getResource("GerenteView.fxml"));
                            root= (Parent) fxmlLoader.load();
                            Stage stage3 = new Stage();
                            stage3.setTitle("GerenteView");
                            stage3.setScene(new Scene(root));
                            stage3.show();
                            break;
                        default:
                            break;
                    }
                    
            }else{
                lb_sms.setText("El usuario o contraseña son incorrectos");
                System.out.println("El usuario o contraseña son incorrectos");
            }
        }
        }catch(SQLException e){
            System.out.println("Error, "+e.getMessage());
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
    }catch(IOException e){
        System.out.println("Error, no se ha podido conectar el Driver postgres");
    }
    } 

    
}
    
