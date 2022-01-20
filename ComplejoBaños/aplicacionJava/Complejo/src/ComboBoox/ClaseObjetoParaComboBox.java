
package ComboBoox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ClaseObjetoParaComboBox {

    private String nombre;
    private int codigo;
    

    public ClaseObjetoParaComboBox(int codigo, String nombreDeEjemplo) {
        this.codigo = codigo;
        this.nombre = nombreDeEjemplo;
    }
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return nombre;
    }

}

    