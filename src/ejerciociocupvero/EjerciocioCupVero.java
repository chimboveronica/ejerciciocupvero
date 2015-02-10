/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciociocupvero;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Veronica C
 */
public class EjerciocioCupVero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         String archSintactico = "ejemplo.cup";
        String[] asintactico = {"-parser", "AnalizadorSintac", archSintactico};
        try {
            java_cup.Main.main(asintactico);
        } catch (Exception ex) {
            Logger.getLogger(EjerciocioCupVero.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Generado!");
    }
    
}
