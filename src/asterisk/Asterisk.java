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
    

    
    public void menu(){
    
        System.out.println("\t\tMenu");
        System.out.println("1.Crear Tema");
        System.out.println("2.Crear Titulo");
        System.out.println("3.Crear Termino");
        System.out.println("4.Crear Relacion");
        System.out.println("5.Ver Temas");
        System.out.println("6.Ver ");
        System.out.println("1.Crear Tema");
    }
    public static void main(String[] args) {
        
    
    }

}
