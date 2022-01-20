package complejo_pr.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author C40S
 */

//USAREMOS ESTA CONEXION PARA NO ESTAR REPITIENDO EL CODIGO Y SIMPLEMENTE LLAMAREMOS LAS OTRAS CLASES, ACÁ
public class ConexiOnBD {
    Connection conectar = null;
    private ResultSet resultSet = null;//Para obtener los resultados de las consultas SQL de la base de datos
    private Statement statement = null;//Para enviar comandos SQL a la base de datos

    public ConexiOnBD(){
        //String cadena = "jdbc:postgresql://localhost:5432/bd_PA"   ;
        String cadena = "jdbc:postgresql://localhost:5432/BD_BA" ;
        String user= "postgres";
        String pass= "Speedmind123."; 
        try{
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(cadena, user,pass);  
        }catch(Exception e){
            System.out.println("Error de conexión "+e.getMessage());
        }
    }
    public ResultSet EjecutarSql(String Cadena) {
        try {
            statement = conectar.createStatement();
            resultSet = statement.executeQuery(Cadena);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultSet;
    }
    
    public Connection getConnection(){
        return conectar;
    }
    public void desconectar(){
        conectar = null;
    }    
}
