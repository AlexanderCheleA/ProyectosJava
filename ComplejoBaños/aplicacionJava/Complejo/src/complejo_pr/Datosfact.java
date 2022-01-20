/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complejo_pr;

import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author C40S
 */
public class Datosfact {
       
    public SimpleIntegerProperty cant = new SimpleIntegerProperty();
    
    public Integer getCant (){
        return cant.get();
    }
    

}
    

