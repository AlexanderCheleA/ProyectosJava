package complejo_pr;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.ObservableList;


/**
 *
 * @author C40S
 */

public class reporte_por_servicio implements Serializable{
    
    private String descripcion_servicios;
    private Double suma;
    
    public reporte_por_servicio(String descripcion_servicios,Double suma) {
        this.descripcion_servicios=descripcion_servicios;
        this.suma=suma;
    }

    public String getDescripcion_servicios1() {
        return descripcion_servicios;
    }

    public void setDescripcion_servicios1(String descripcion_servicios) {
        this.descripcion_servicios = descripcion_servicios;
    }

    public Double getSuma1() {
        return suma;
    }

    public void setSuma1(Double suma) {
        this.suma = suma;
    }
    
    @Override
    public String toString(){
        return descripcion_servicios;
    }
     
    //static -> accedo al metodo sin crear una instancia.->> no permite crear estancia
    
public static void llenarinfoReporte_por_servicio(Connection connection, ObservableList<reporte_por_servicio> listadoReporte_por_servicio){
        try {
            Statement st = connection.createStatement();
            //querty de la base
            ResultSet result =st.executeQuery("select (servi.descripcion_servicios) as descripcion_servicios, (sum(sf.ser_precios_servicios * sf.cantidad_de_servicios_factura)) as suma from servicios_factura sf, servicios servi, (select	(g.id_de_fact) as todas_las_id_de_Fact from	(select		f.id_factura as id_de_fact	from		factura f,servicios_factura sf	where	f.id_factura=sf.id_factura and f.fecha_de_factura='2019-08-25') as g	group by id_de_fact	having count(*)>=1) as id_fact where sf.id_factura=id_fact.todas_las_id_de_Fact and servi.id_servicios=sf.id_servicios  group by descripcion_servicios having count(*)>=1; ");
            
            while(result.next()){
                
                listadoReporte_por_servicio.add(new reporte_por_servicio(result.getString("descripcion_servicios"),
                                                result.getDouble("suma")));
                
            }
        } catch (Exception e) {
            System.out.println("ERRORhhhhhhhhhhhhhhhhhhhh: "+ e.getMessage());
        }
    }
    
    
}
