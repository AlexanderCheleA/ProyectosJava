/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package comlejo_pr;
/// lo movi asi
package complejo_pr;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.ObservableList;
/**
 *
 * @author C40S
 */
public class Cliente implements Serializable{
    private Integer id_persona;
    private String cedula;
    private String prinombre;
    private String segnombre;
    private String priapellido;
    private String segapellido;
    private String numerodetelefono;
    private String email;
    private String direccion;
    private String estado_persona;

    public Cliente(Integer id_persona, String cedula, String prinombre, String segnombre, 
            String priapellido, String segapellido, String numerodetelefono,
            String email, String direccion,String estado_persona) {
        this.id_persona = id_persona;
        this.cedula = cedula;
        this.prinombre = prinombre;
        this.segnombre = segnombre;
        this.priapellido = priapellido;
        this.segapellido = segapellido;
        this.numerodetelefono = numerodetelefono;
        this.email = email;
        this.direccion = direccion;
        this.estado_persona = estado_persona;
    }
    
    public Integer getid_persona() {
        return id_persona;
    }

    public void setid_persona(Integer id_persona) {
        this.id_persona = id_persona;
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

    public String getNumerodetelefono() {
        return numerodetelefono;
    }

    public void setNumerodetelefono(String numerodetelefono) {
        this.numerodetelefono = numerodetelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado_persona() {
        return estado_persona;
    }

    public void setEstado_persona(String estado_persona) {
        this.estado_persona = estado_persona;
    }
    
    
    @Override
    public String toString(){
        return cedula;
    }
     
    //static -> accedo al metodo sin crear una instancia.->> no permite crear estancia
    public static void llenarinfoCliente(Connection connection, ObservableList<Cliente> listadoCliente,String d){
        try {
            Statement st = connection.createStatement();
            //querty de la base
            ResultSet result =st.executeQuery("SELECT id_persona, cedula, prinombre, segnombre, priapellido, segapellido, numerodetelefono, email, direccion, estado_persona FROM personas order by id_persona asc");
            while(result.next()){
                
                if (d.equals(result.getString("cedula"))) { /////////PARA VALIDAR QUE DATO SOLO SE DEBE PRESENTAR
                    //SI SE ELIMINA ESTO SE TRESENTARAN TODOS LO DATOS PERSONAS
                    
                listadoCliente.add(new Cliente(result.getInt("id_persona"),
                                                result.getString("cedula"),
                                                result.getString("prinombre"),
                                                result.getString("segnombre"),
                                                result.getString("priapellido"),
                                                result.getString("segapellido"),
                                                result.getString("numerodetelefono"),
                                                result.getString("email"),
                                                result.getString("direccion"),
                                                result.getString("estado_persona")));
                }
            }
        } catch (Exception e) {
            System.out.println("ERRORhhhhhhhhhhhhhhhhhhhh: "+ e.getMessage());
        }
    }
    
    
}
