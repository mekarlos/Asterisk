/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asterisk;

import Datos.DAO;
import Modelo.Tema;
import Modelo.Termino;
import Modelo.Titulo;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

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
    private static Scanner scan = new Scanner(System.in);
    private static DAO dao = new DAO();
    private static Tema tema;
    private static Titulo titulo;

    public final static void clearConsole() {
        try {

            for (int i = 0; i < 80; i++) {
                System.out.println("");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    public static void crearTema() {
        System.out.println("Ingrese Nombre del Tema: ");
        String t = scan.nextLine();
        Tema tema = new Tema(t);
        dao.create(tema);
    }

    public static void crearTitulo() {
        System.out.println("Ingrese Titulo:");
        String tit = scan.nextLine();
        String desc = scan.nextLine();
        Titulo titulo = new Titulo();
        titulo.setDescripcion(desc);
        titulo.setTitulo(tit);
        Tema tema = seleccionarTema();
        titulo.setTema(tema);
        Titulo padre = seleccionarTituloTema(tema);
        titulo.setPrincipal(padre);
        if (padre == null) {
            titulo.setNivel(0);
        } else {
            titulo.setNivel(padre.getNivel() + 1);
        }
        dao.create(titulo);
    }

    public static Titulo seleccionarTitulo() {
        ArrayList<Titulo> titulos = dao.obtenerListaTitulos();
        System.out.println("0 Sin titulo");
        for (int i = 0; i < titulos.size(); i++) {
            Titulo titulo = titulos.get(i);
            System.out.println((i + 1) + " " + titulo.toString());

        }
        int opt;
        System.out.println("Seleccione Titulo:");
        opt = new Integer(scan.nextLine());
        try {
            return titulos.get(opt - 1);
        } catch (Exception e) {
            return null;
        }
    }

    public static Titulo seleccionarTituloTema(Tema tema) {
        ArrayList<Titulo> titulos = dao.obtenerListaTitulosTema(tema);
        System.out.println("0 Sin titulo");
        for (int i = 0; i < titulos.size(); i++) {
            Titulo titulo = titulos.get(i);
            System.out.println(titulo.toString());

        }
        int opt;
        System.out.println("Seleccione Titulo:");
        opt = new Integer(scan.nextLine());
        try {
            return titulos.get(opt - 1);
        } catch (Exception e) {
            return null;
        }
    }

    public static Tema seleccionarTema() {
        ArrayList<Tema> temas = dao.obtenerListaTemas();
        System.out.println("0 Sin Tema");
        for (int i = 0; i < temas.size(); i++) {
            Tema tema = temas.get(i);
            System.out.println((i + 1) + " " + tema.toString());
        }
        System.out.println("Seleccione el tema");
        int opt;
        opt = new Integer(scan.nextLine());
        try {
            return temas.get(opt - 1);
        } catch (Exception e) {
            return null;
        }
    }

    public static void ejecutar() {
        int opt;
        System.out.println("Ingrese una opción: ");
//        scan.nextLine();
        opt = new Integer(scan.nextLine());
        switch (opt) {
            case 1:
                crearTema();
                break;
            case 2:
                crearTitulo();
                break;
            case 3:

                break;
        }
    }

    public static void mostrarMenu() {

        System.out.println("\t\tMenu");
        System.out.println("1.Crear Tema");
        System.out.println("2.Crear Titulo");
        System.out.println("3.Crear Termino");
        System.out.println("4.Crear Relacion");
        System.out.println("5.Ver Temas");
        System.out.println("6.Ver Tema Titulo Termino");
        System.out.println("7.Ver Titulos");
        System.out.println("8.Ver Titulos Tema");
        System.out.println("9.Ver Titulo Termino");
        System.out.println("10.Ver Terminos Tema");
        System.out.println("11.Seleccionar Titulo");
        System.out.println("12.Seleccionar Tema");

    }

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            ejecutar();

        }
    }

}
