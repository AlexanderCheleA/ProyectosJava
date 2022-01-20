
package Empleado;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.ObservableList;

/**
 *
 * @author C40S
 */

public class Empleado implements Serializable{
    
    private String cedula;
    private String prinombre;
    private String segnombre;
    private String priapellido;
    private String segapellido;
    private String telefono;
    private String descripcion_cargos;

    public Empleado(String cedula, String prinombre, String segnombre, String priapellido, String segapellido, String numerodetelefono, String descripcion_cargos) {
        this.cedula = cedula;
        this.prinombre = prinombre;
        this.segnombre = segnombre;
        this.priapellido = priapellido;
        this.segapellido = segapellido;
        this.telefono = numerodetelefono;
        this.descripcion_cargos = descripcion_cargos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPrinombre() {
        return prinombre;
    }

    public void setPrinombre(String prinombre) {
        this.prinombre = prinombre;
    }

    public String getSegnombre() {
        return segnombre;
    }

    public void setSegnombre(String segnombre) {
        this.segnombre = segnombre;
    }

    public String getPriapellido() {
        return priapellido;
    }

    public void setPriapellido(String priapellido) {
        this.priapellido = priapellido;
    }

    public String getSegapellido() {
        return segapellido;
    }

    public void setSegapellido(String segapellido) {
        this.segapellido = segapellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String numerodetelefono) {
        this.telefono = numerodetelefono;
    }

    public String getDescripcion_cargos() {
        return descripcion_cargos;
    }

    public void setDescripcion_cargos(String descripcion_cargos) {
        this.descripcion_cargos = descripcion_cargos;
    }
    
    
    @Override
    public String toString(){
        return cedula;
    }
     
    //static -> accedo al metodo sin crear una instancia.->> no permite crear estancia
    public static void llenarinfoEmpleado(Connection connection, ObservableList<Empleado> listadoEmpleado){
        try {
            Statement st = connection.createStatement();
            //querty de la base
            /*ResultSet result =st.executeQuery("SELECT id_empleado,usuario,contraseña,fecha,estado_empleao,id_cargos,"
                    + "cedula,prinombre,segnombre,priapellido,segapellido,telefono,email,direccion"
                    + "FROM empleado ");
                    //+ "WHERE e.id_cargos=c.id_cargos");
*/
            ResultSet result =st.executeQuery("SELECT e.cedula,e.prinombre,e.segnombre,e.priapellido,e.segapellido,e.telefono,ca.descripcion_cargos "
                    + "FROM empleado e,cargos ca "
                    +"WHERE ca.id_cargos=e.id_cargos");
            
            while(result.next()){
                
                listadoEmpleado.add(new Empleado(result.getString("cedula"),
                                                result.getString("prinombre"),
                                                result.getString("segnombre"),
                                                result.getString("priapellido"),
                                                result.getString("segapellido"),
                                                result.getString("telefono"),
                                                result.getString("descripcion_cargos")));
            }
        } catch (Exception e) {
            System.out.println("ERRORhhhhhhhhhhhhhhhhhhhh: "+ e.getMessage());
        }
    }
}
