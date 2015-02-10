/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciociocupvero;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Veronica C
 */
public class Main {

    public static void generarLexer(String path) {
        File file = new File(path);
        JFlex.Main.generate(file);
    }

    /**
     * Es un menu para elegir entre generar el analizador lexico y sintactico, o
     * ejecutarlos sobre un archivo de pruebas.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        java.util.Scanner in = new Scanner(System.in);
        int valor = 0;
        do {
            System.out.println("..........COMPILADOR DE FÓRMULA DE VELOCIDAD.........");
            System.out.println("..........Verónica Paulina Chimbo Coronel.........");
            System.out.println("1) Generar Archivos");
            System.out.println("2) Ejecutar Analizador");
            System.out.println("3) Salir");
            System.out.print("Opcion: ");
            try {
                valor = in.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("UDS HA INGRESADO DATOS INCORRECTOS A LAS OPCIONES ESTABLECIDAS!");
                valor = 3;
            }

            if (valor > 0) {
                switch (valor) {

                    case 1: {
                        System.out.println("\n*** Generando ***\n");
                        String archLexico = "";
                        String archSintactico = "";
                        if (args.length > 0) {
                            System.out.println("\n*** Procesando archivos custom ***\n");
                            archLexico = args[0];
                            archSintactico = args[1];
                        } else {
                            System.out.println("\n*** Procesando archivo default ***\n");
                            archLexico = "ejemplo.lex";
                            archSintactico = "ejemplo.cup";
                        }
                        String[] alexico = {archLexico};
                        String[] asintactico = {"-parser", "AnalizadorSintactico", archSintactico};
                        jflex.Main.main(alexico);
                        try {
                            java_cup.Main.main(asintactico);
                        } catch (Exception ex) {
                            Logger.getLogger(EjerciocioCupVero.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //movemos los archivos generados
                        boolean mvAL = moverArch("Lexer.java");
                        boolean mvAS = moverArch("AnalizadorSintactico.java");
                        boolean mvSym = moverArch("sym.java");
                        if (mvAL && mvAS && mvSym) {
                            System.exit(0);
                        }
                        System.out.println("Generado!");
                        break;
                    }
                    case 2: {
                        /*  Ejecutamos el analizador lexico y sintactico
                         sobre un archivo de pruebas. 
                         */
                        String[] archivoPrueba = {"test.txt"};
                        AnalizadorSintactico.main(archivoPrueba);
                        System.out.println("Ejecutado!");

                        break;
                    }
                    case 3: {
                        System.out.println("GRACIAS!");
                        break;
                    }
                    default: {
                        System.out.println("Opcion no valida!");
                        break;
                    }
                }
            }
        } while (valor != 3);

    }

    public static boolean moverArch(String archNombre) {
        boolean efectuado = false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            System.out.println("\n*** Moviendo " + arch + " \n***");
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "src" + File.separator
                    + "ejerciociocupvero" + File.separator + arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                System.out.println("\n*** Generado " + archNombre + "***\n");
                efectuado = true;
            } else {
                System.out.println("\n*** No movido " + archNombre + " ***\n");
            }

        } else {
            System.out.println("\n*** Codigo no existente ***\n");
        }
        return efectuado;
    }
}
