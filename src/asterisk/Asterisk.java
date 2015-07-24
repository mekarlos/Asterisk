/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asterisk;

import Datos.DAO;
import Modelo.Tema;
import Modelo.Termino;
import java.io.Console;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Carlos
 */
public class Asterisk {

    /**
     * Parses a string with ANSI color codes based on the input
     *
     * @param input the input string
     * @return the parsed ANSI string
     */
    /**
     * @param args the command line arguments
     */
    public final static void clearConsole() {
        try {

            for (int i = 0; i < 80; i++) {
                System.out.println("");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }
    
    public void CrearTermino(){
        
    }

    public static void main(String[] args) {

        /*      Scanner scan = new Scanner(System.in);
         AnsiConsole.systemInstall();

         System.out.println(ansi().fg(Color.YELLOW).a("Hello World").reset());
         System.out.println(ansi().fg(Color.BLUE).a("Hello World").reset());

         System.out.println(ansi().fg(Color.GREEN).a("Hello World").reset());
         */
// System.out.println(ansi().eraseScreen().render("@|red Hello|@ @|green World|@"));
        //System.err.println(ansi().eraseScreen());
        //  AnsiConsole.systemUninstall();
        //scan.next();
        //System.out.println("");
//        DAO dao = new DAO();
//        String termino;
//        int temaId;
//        int tituloId;
//        int option;
//
//        ArrayList<Tema> temas = dao.ListaTemas();
//        clearConsole();
//        System.out.println("Lista Temas: ");
//        for (Tema tema : temas) {
//            System.out.println(tema.toString());
//        }
//        
//        
//
//        System.out.println("----------------------------");
//        System.out.println("Tema ID:");
//        temaId = new Integer(scan.nextLine());
//        while (true) {
//            System.out.println("1. Ingresar\n2. Leer\n3. Temas\n4. Terminos Tema");
//            option = new Integer(scan.nextLine());
//
//            switch (option) {
//                case 1:
//                    while (true) {
//                        Termino t = new Termino();
//                        Tema tema = new Tema();
//                        System.out.println("Termino: ");
//                        termino = scan.nextLine();
//                        if (termino.equals("")) {
//                            break;
//                        }
//                        t.setTermino(termino);
//                        tema.setId(temaId);
//                        dao.create(t);
//                        clearConsole();
//                    }
//                    break;
//                case 2:
//                    while (true) {
//                        Termino tt = dao.darTermino();
//                        System.out.println(tt.toString());
//                        String k = scan.nextLine();
//                        if (!k.equals("")) {
//                            break;
//                        }
//                    }
//                    break;
//                case 3:
//                    System.out.println("Lista Temas: ");
//                    for (Tema tema : temas) {
//                        System.out.println(tema.toString());
//                    }
//                    break;
//
//                case 4:
//                    System.out.println("Lista Temas: ");
//                    for (Tema tema : temas) {
//                        System.out.println(tema.toString());
//                    }
//                    temaId = new Integer(scan.nextLine());
//                    ArrayList<Termino> teminos = dao.ListaTerminosTema(temaId);
//                    clearConsole();
//                    int i = 1;
//                    for (Termino t : teminos) {
//                        System.out.println(" -" + (i < 10 ? "  " : " ") + i + ". " + t.toString());
//                        i++;
//                    }
//
//                    break;
//            }
//
//        }
    }

}
